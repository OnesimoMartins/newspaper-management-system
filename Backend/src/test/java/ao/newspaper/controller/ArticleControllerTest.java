//package ao.newspaper.controller;
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.request;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.MediaType;
//import org.springframework.mock.web.MockMultipartFile;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//
//import ao.martins.newspaper.NewsPaperManagementApplication;
//import ao.martins.newspaper.api.dto.input.ArticleInput;
//
//@AutoConfigureMockMvc
//@SpringBootTest(classes = NewsPaperManagementApplication.class)
//public class ArticleControllerTest {
//
//	@Autowired private MockMvc mvc;
//	
//	private ArticleInput generateArticleInput() {
//		return ArticleInput.builder().journalistId(1L)
//		.categoryId(2L).keywords("#Desporto#Economy#ControllerTest")
//		.tittle("Controller test").summary("summary controllerTest")
//		.file(new MockMultipartFile("texto", "este texto provemm do controlador teste".getBytes()))
//		.build();
//	}
//
//	@Test
//	void should_return_status201_when_create_NewArticle() throws Exception {
//		var article = generateArticleInput();
//		MockMultipartFile file= new MockMultipartFile("file", "this is mock test".getBytes());
//		
////		mvc.perform(request(HttpMethod.POST, "http://localhost:80/articles")
////				.contentType(MediaType.APPLICATION_JSON)
////		.content(" { \" \" }")		).andExpect(status().isCreated());
//		mvc.perform(MockMvcRequestBuilders.multipart("articles").file(file));
//	}
//	
//}
