package ao.martins.newspaper.api.openapi.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.CollectionModel;

import ao.martins.newspaper.api.dto.input.MagazineInput;
import ao.martins.newspaper.api.dto.response.MagazineEditionResponse;
import ao.martins.newspaper.api.dto.response.MagazineResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags="Magazines")
public interface MagazineControllerOpenApi {

	@ApiOperation("Busca um magazine dado um id")
	@ApiResponses({
		@ApiResponse(code=404 ,message="Magazine com o referente id não encontrado")
	})
	public MagazineResponse findMagazine(Long magazineId) ;

	@ApiOperation("Cria um novo Magazine")
	public MagazineResponse createMagazine(MagazineInput newMagazine, Pageable pageable);

	@ApiOperation("Cria uma nova edição de um magazine")
	@ApiResponses({
		@ApiResponse(code=404 ,message="Magazine com o referente id não encontrado")
	})
	public MagazineEditionResponse newEdition( Long id);
	
	@ApiOperation("Busca determinada edição de um magazine")
	@ApiResponses({
		@ApiResponse(code=404 ,message="Magazine com o referente id não encontrado")
	})
	public MagazineEditionResponse magazineEdition( Long id, Long edition);

	@ApiOperation("Publica a última edição de um magazine")
	@ApiResponses({
		@ApiResponse(code=404 ,message="Magazine com o referente id não encontrado")
	})
	public MagazineEditionResponse publishMagazine(Long id) ;

	@ApiOperation("Busca uma página de magazines")
	public CollectionModel<MagazineResponse> getMagazines(Pageable pageable) ;
	
}
