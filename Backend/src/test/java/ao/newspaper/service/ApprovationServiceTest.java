package ao.newspaper.service;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ao.martins.newspaper.NewsPaperManagementApplication;
import ao.martins.newspaper.domain.entity.Avaliation;
import ao.martins.newspaper.domain.exception.ArticleAlreadyApprovedException;
import ao.martins.newspaper.domain.exception.ArticleAlreadyMarkedException;
import ao.martins.newspaper.domain.exception.InvalidAvaliatorException;
import ao.martins.newspaper.domain.repository.ArticleRepository;
import ao.martins.newspaper.domain.repository.AvaliationRepository;
import ao.martins.newspaper.domain.repository.JournalistRepository;
import ao.martins.newspaper.domain.service.AvaliationService;

@SpringBootTest(classes = NewsPaperManagementApplication.class)
public class ApprovationServiceTest {

	@Autowired private AvaliationService service;
	@Autowired private AvaliationRepository repo;
	@Autowired private ArticleRepository articleRepo;
	@Autowired private JournalistRepository journalistRepo;
	
	
	@Test
	public void should_return_all_Approvations() {
            assertNotNull(repo.findAll());
	}
	
	@Test
	public void should_Create_New_Approvation() throws ArticleAlreadyMarkedException, ArticleAlreadyApprovedException, InvalidAvaliatorException {
		var expected=new Avaliation();
		expected.setArticle(articleRepo.findById(3L).get());
		expected.setJournalist(journalistRepo.getById(3L));

		
	var request=new Avaliation();
		request.setArticle(articleRepo.findById(3L).get());
		request.setJournalist(journalistRepo.getById(3L));
		request.setMark((byte) 8);
		
		var response=service.createAvaliation(request);

				expected.setId(request.getId());

		assertAll(
				()-> assertNotNull(expected),
				()-> assertEquals(response, expected)
				);
	
	}
	
//	@Test
//	public void should_Throw_ArticleAlreadyMarkedException_when_crete_NewApprovation() {
//
//		var aprovation=new Approvation();
//		aprovation.setArticle(articleRepo.getById(1L));
//		aprovation.setJournalist(journalistRepo.getById(1L));
//		aprovation.setMark((byte) 2);
//		
//		assertThrows(ArticleAlreadyMarkedException.class, 
//				()->this.service.createApprovation(aprovation)
//				);
//	
//	}
	
//	public void should_Throw_ArticleAlreadyApproved_when_crete_NewApprovation() {
//		
//		var aprovation=new Approvation();
//		aprovation.setArticle(articleRepo.getById(1L));
//		aprovation.setJournalist(journalistRepo.getById(1L));
//		aprovation.setMark((byte) 2);
//		
//		assertThrows(Art.class, 
//				()->this.service.createApprovation(aprovation)
//				);
//	}
}
