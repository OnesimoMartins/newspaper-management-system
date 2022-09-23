package ao.martins.newspaper.api.openapi.controller;

import java.util.List;

import ao.martins.newspaper.api.dto.response.ArticleResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Magazine")
public interface MagazineArticleOpenApi {


	@ApiOperation("Busca os artigos de uma determinada edição de um magazine")
	@ApiResponses({
		@ApiResponse(code=404 ,message="Magazine com o referente id não encontrado")
	})
	public List<ArticleResponse> getArticles(Long magazineId, Long edition);		

	@ApiOperation("Adiciona um artigo a um magazine")
	@ApiResponses({
		@ApiResponse(code=404 ,message="Magazine com o referente id não encontrado")
	})
	public ArticleResponse addArticle( Long magazineId, Long articleId) ;
	
	
	@ApiOperation("remove um artigo de um magazine")
	@ApiResponses({
		@ApiResponse(code=404 ,message="Magazine com o referente id não encontrado")
	})
	public void removeArticle( Long magazineId, Long articleId);
	
}
