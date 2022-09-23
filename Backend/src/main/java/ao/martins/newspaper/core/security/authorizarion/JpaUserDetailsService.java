package ao.martins.newspaper.core.security.authorizarion;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ao.martins.newspaper.domain.entity.Administrator;
import ao.martins.newspaper.domain.entity.Journalist;
import ao.martins.newspaper.domain.entity.WorkerDetails;
import ao.martins.newspaper.domain.entity.enums.AdministrationRole;
import ao.martins.newspaper.domain.repository.AdministratorRepository;
import ao.martins.newspaper.domain.repository.JournalistRepository;

@Service
public class JpaUserDetailsService implements UserDetailsService {
	
	@Autowired
	private JournalistRepository journalistRepository;
	
	@Autowired
	private AdministratorRepository admRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
			
		var journalist=this.journalistRepository.findJournalistByEmail(username);

			if(journalist.isEmpty()) {
				var adm= admRepository.findAdministratorByEmail(username).orElseThrow(
						()-> new UsernameNotFoundException("No user with email '"+username+
								"' was found.")
						);
				return new AuthDetails(adm,adm.getId(),getAuthorities(adm));
			}

			return new AuthDetails(journalist.get()
					,journalist.get().getId()
					,getAuthorities(journalist.get()));
	}
	
	private List<GrantedAuthority> getAuthorities( WorkerDetails worker) {
		
		List<GrantedAuthority> authorities=new ArrayList<>();
		String type=null;
		
		if(worker instanceof Journalist) {
		 type="JRT_";	
		 
		 authorities.add(new SimpleGrantedAuthority("JRT"));
		 
		
		}

		if(worker instanceof Administrator) {
		 type="ADM_";

		 authorities.add(new SimpleGrantedAuthority("ADM"));
		 
		 authorities.add(new SimpleGrantedAuthority(
		 type.concat(((Administrator) worker).getAdministrativeRole().name()
			)));
		 
		 if(((Administrator) worker).getAdministrativeRole().equals(AdministrationRole.PUBLISHER))
			 authorities.add(new SimpleGrantedAuthority( type.concat("PUBLISHER")));
		}
		
		authorities.add(new SimpleGrantedAuthority(
			type.concat(worker.getWorkerDetails().getExperienceLevel().name()).toUpperCase()
			));
		
		return  authorities;
	}

}
