package ao.newspaper.service;

import static org.junit.jupiter.api.Assertions.*;

import javax.persistence.EntityNotFoundException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ao.martins.newspaper.NewsPaperManagementApplication;
import ao.martins.newspaper.domain.entity.Journalist;
import ao.martins.newspaper.domain.service.JournalistService;

@SpringBootTest(classes = NewsPaperManagementApplication.class)
public class JounalistServiceTest {


	@Autowired
	private JournalistService service;
	
	@Test
	void shouldReturnAllJournalits() {
		service.findAll().forEach(it->System.out.println(
				it.getWorkerDetails().getExperienceLevel()
				));
		assertNotNull(service.findAll());
	}
	
	@Test
	void shouldReturnJournalitsWithId1() {
		Journalist j=service.findByIdOrThrows(1L);
          assertAll(
        		  ()->assertNotNull(j.getWorkerDetails()),
				 ()->assertEquals(j.getId(), 1L)
				);
	}
	
	@Test
	void shouldThrowEntityNotFoundExceptionJournalitsWhenSearchForId100() {
	     assertThrows(EntityNotFoundException.class, 
	    		 ()->service.findByIdOrThrows(100L));
	}

}
