package ao.newspaper.service;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.persistence.EntityNotFoundException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ao.martins.newspaper.NewsPaperManagementApplication;
import ao.martins.newspaper.domain.entity.Article;
import ao.martins.newspaper.domain.entity.Category;
import ao.martins.newspaper.domain.entity.Journalist;
import ao.martins.newspaper.domain.service.ArticleService;

@SpringBootTest(classes = NewsPaperManagementApplication.class)
public class ArticleServiceTest {

	@Autowired
	private ArticleService service;

	private Article generateArticle() {
		var article = new Article();
		article.setCategory(new Category());
		article.setCreator(new Journalist());
		article.setKeywords("#Desporto#Economy");
		article.setTittle("teste");
		article.setSummary("summary testing");
		article.getCategory().setId(1L);
		article.getCreator().setId(1L);
		return article;
	}

	@Test
	void shouldCreateNewArticle() {
		var article = generateArticle();
		article = service.saveArticle(article);
		assertNotNull(article);
		assertTrue(article.getCategory() != null);
	}
	
	@Test
	void should_Throw_EntityNotFounEx_When_create_New_Article_with_nonValid_category() {
		final var article = generateArticle();
		article.getCategory().setId(100L);
		assertThrows(EntityNotFoundException.class,()-> service.saveArticle(article));
	}
	
	@Test
	void should_Throw_EntityNotFounEx_When_create_New_Article_with_nonValid_Journalist() {
		final var article = generateArticle();
		article.getCreator().setId(100L);
		assertThrows(EntityNotFoundException.class,()-> service.saveArticle(article));
	}

//	@Test
//	void shouldReturnAllArticles() {
//		var articles = service.findArticles();
//
//		assertAll(() -> assertNotNull(articles), () -> assertTrue(articles.size() > 1));
//	}

//	@Test
	void shouldSaveArticleInDB() {
		var article = new Article();
		assertAll(() -> assertNotNull(service.saveArticle(article)));
	}

//	@Test
	void shouldNotSaveArticleInDB() {
		var article = new Article();
		assertAll(() -> assertNotNull(service.saveArticle(article)));
	}

//	@Test
//	void shouldReturnArticleWithId1() {
//		var articles = service.findArticles();
//
//		assertAll(() -> assertNotNull(articles), () -> assertTrue(articles.size() > 1));
//	}
//
////	@Test
//	void shouldThrowEntityNotFoundExceptionWhenSearchForArticleId100() {
//		var articles = service.findArticles();
//
//		assertAll(() -> assertNotNull(articles), () -> assertTrue(articles.size() > 1));
//	}
}
