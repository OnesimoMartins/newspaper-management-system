package ao.martins.newspaper.api.openapi.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import ao.martins.newspaper.api.dto.input.ArticleInput;
import ao.martins.newspaper.api.dto.response.ArticleResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Articles")
public interface ArticleControllerOpenApi {

	@ApiOperation("Busca um artigo dado um id")
	@ApiResponses({ @ApiResponse(code = 404, message = "Artigo com o referente id não encontrado") })
	public ArticleResponse findOne(@ApiParam(value = "") Long id);

	@ApiOperation("Faz upload do corpo de texto de um artigo")
	public ResponseEntity<Void> uploadBody(Long id, MultipartFile file);

	@ApiOperation("Cria um novo artigo")
	public ArticleResponse create(ArticleInput input);
}
