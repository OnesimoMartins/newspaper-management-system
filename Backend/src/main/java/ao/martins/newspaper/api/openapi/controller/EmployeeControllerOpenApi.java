package ao.martins.newspaper.api.openapi.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.CollectionModel;

import ao.martins.newspaper.api.dto.response.AdministratorResponse;
import ao.martins.newspaper.api.dto.response.JournalistResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags="Employees")
public interface EmployeeControllerOpenApi {

	@ApiOperation("Busca uma página de empregados jornalistas")
	public CollectionModel<JournalistResponse> getJournalists( Pageable pageable) ;


	@ApiOperation("Busca uma página de empregados Administradores")
	public CollectionModel<AdministratorResponse> getAdministrators( Pageable pageable) ;
	
	
}
