package ao.martins.newspaper.api.openapi.controller;

import ao.martins.newspaper.api.dto.input.AvaliationInput;
import ao.martins.newspaper.api.dto.response.AvaliationResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags="Avaliations")
public interface AvaliationControllerOpenApi {

	@ApiOperation("avalia um determinado artigo")
	@ApiResponses({
		@ApiResponse(code=404 ,message="Artigo com o referente id não encontrado")
	})
	public AvaliationResponse avaliate (Long articleId, AvaliationInput input);
}
