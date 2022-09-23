package ao.martins.newspaper.api.v1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import ao.martins.newspaper.api.dto.mapper.ArticleMapper;
import ao.martins.newspaper.api.dto.response.ArticleResponse;
import ao.martins.newspaper.api.openapi.controller.MagazineArticleOpenApi;
import ao.martins.newspaper.domain.entity.Article;
import ao.martins.newspaper.domain.entity.Magazine;
import ao.martins.newspaper.domain.exception.BusinessException;
import ao.martins.newspaper.domain.service.ArticleService;
import ao.martins.newspaper.domain.service.MagazineService;

@RestController
@RequestMapping("v1/magazines")
public class MaganizeArticleController implements MagazineArticleOpenApi{

	@Autowired 
	private MagazineService magazineService;
	
	@Autowired
	private ArticleService articleService;
	
	@Autowired 
	private ArticleMapper articleMapper;



	@GetMapping("{magazineId}/editions/{edition}/articles")
	public List<ArticleResponse> getArticles(@PathVariable Long magazineId,
			@PathVariable Long edition){
		
	return magazineService.
			   findArticlesByMagazineEdition(magazineId, edition).stream()
			   .map(articleMapper::toArticleResponse).toList();
	}

	@PutMapping("{magazineId}/articles/{articleId}")
	public ArticleResponse addArticle(@PathVariable Long magazineId,
			@PathVariable Long articleId) throws BusinessException {
		
		Magazine magazine= magazineService.findMagazineByIdOrThrows(magazineId);
		Article article= articleService.findArticleByIdOrThrows(articleId);
		
		return articleMapper.toArticleResponse(
				articleService.addArticleToMagazine(article,magazine)
				);
	}
	
	
	@DeleteMapping("{magazineId}/articles/{articleId}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void removeArticle(@PathVariable Long magazineId,
			@PathVariable Long articleId)throws BusinessException {
		
		Magazine magazine= magazineService.findMagazineByIdOrThrows(magazineId);
		Article article= articleService.findArticleByIdOrThrows(articleId);
		articleService.removeArticleFromMagazine(article,magazine);
	
	}
	
}
