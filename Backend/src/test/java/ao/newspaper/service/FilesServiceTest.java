package ao.newspaper.service;

import java.time.LocalDate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ao.martins.newspaper.NewsPaperManagementApplication;
import ao.martins.newspaper.domain.service.FileService;

@SpringBootTest(classes = NewsPaperManagementApplication.class)
public class FilesServiceTest {

	@Autowired
	private FileService service;

	@Test
	public void should_return_new_article_source() {
		String path=service.buildArticleFilePath();
		Assertions.assertTrue(path.contains(LocalDate.now().toString()));
		Assertions.assertTrue(path.endsWith(".txt"));
	}
	@Test
	public void should_save_new_txtFile_given_Source() {
		String path=service.buildArticleFilePath();
		Assertions.assertTrue(
				service.SaveFile(path, "texto para teste".getBytes())
				);
	}


}
