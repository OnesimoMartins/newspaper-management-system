package ao.martins.newspaper.domain.service;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ao.martins.newspaper.domain.entity.Administrator;
import ao.martins.newspaper.domain.repository.AdministratorRepository;

@Service
public class AdministratorService {
	
@Autowired private AdministratorRepository administrators;
	
//	public List <Journalist> findAll(){
//		return this.administrators.findAll();
//	}
	
	public Administrator findByIdOrThrows(Long id) {
		return this.administrators.findById(id).orElseThrow(
				()-> new EntityNotFoundException("No journalist with id "+id+" was found")
				);
        }

}