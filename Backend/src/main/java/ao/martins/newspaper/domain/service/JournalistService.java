package ao.martins.newspaper.domain.service;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ao.martins.newspaper.domain.entity.Journalist;
import ao.martins.newspaper.domain.repository.JournalistRepository;

@Service
public class JournalistService {

	@Autowired private JournalistRepository journalists;
	
	public List <Journalist> findAll(){
		return this.journalists.findAll();
	}
	
	public Journalist findByIdOrThrows(Long id) {
		return this.journalists.findById(id).orElseThrow(
				()-> new EntityNotFoundException("No journalist with id "+id+" was found")
				);
	}
	
//	public void createArticle() {}

//	public Journalist updateJournalist(Journalist newjournalist) {
//		return null;
//	}
	
	
}
