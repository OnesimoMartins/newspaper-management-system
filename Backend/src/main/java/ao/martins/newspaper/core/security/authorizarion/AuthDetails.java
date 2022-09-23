package ao.martins.newspaper.core.security.authorizarion;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import ao.martins.newspaper.domain.entity.Worker;
import ao.martins.newspaper.domain.entity.WorkerDetails;
import lombok.Getter;

public class AuthDetails implements UserDetails{

	private static final long serialVersionUID = 1L;

	@Getter
	private Long id; 

	@Getter
	private Worker details;
	
	private List<GrantedAuthority> authorities;
	
	public AuthDetails(WorkerDetails w,Long id, List<GrantedAuthority> authorities) {
		
		this.details=w.getWorkerDetails();
		this.id=id;
		this.authorities=authorities;
	}
	

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.authorities;
	}

	@Override
	public String getPassword() {
		return this.details.getPassword();
	}

	@Override
	public String getUsername() {
		 return this.details.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
