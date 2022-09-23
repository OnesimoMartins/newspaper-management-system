package ao.martins.newspaper.domain.service;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ao.martins.newspaper.domain.entity.Newspaper;
import ao.martins.newspaper.domain.repository.NewspaperRepository;

@Service
public class NewspaperService {

	@Autowired
	private NewspaperRepository newspapers;
	
	public Newspaper findNewspaperOrTrhows(Long id){
		return newspapers.findById(id).orElseThrow(
				()->new EntityNotFoundException("No newspaper with id "+id+" was found.")
				);
	}
	
}
