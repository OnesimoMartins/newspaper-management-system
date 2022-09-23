package ao.martins.newspaper.api.v1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ao.martins.newspaper.api.dto.mapper.AdmMapper;
import ao.martins.newspaper.api.dto.mapper.JournalistMapper;
import ao.martins.newspaper.api.dto.response.AdministratorResponse;
import ao.martins.newspaper.api.dto.response.JournalistResponse;
import ao.martins.newspaper.api.openapi.controller.EmployeeControllerOpenApi;
import ao.martins.newspaper.core.security.api.NewspaperManagementSecutiyProvider;
import ao.martins.newspaper.core.security.api.Security;
import ao.martins.newspaper.domain.repository.AdministratorRepository;
import ao.martins.newspaper.domain.repository.JournalistRepository;
import ao.martins.newspaper.infrastructure.repository.specs.EmployeeSpecs;

@RestController
@RequestMapping("v1/employees/")
public class EmployeeController implements EmployeeControllerOpenApi{

	@Autowired
	private JournalistMapper journalistmapper;

	@Autowired
	private AdmMapper admMapper;

	@Autowired
	private JournalistRepository journalists;

	@Autowired
	private AdministratorRepository administrators;

	@Autowired
	private NewspaperManagementSecutiyProvider securityProvider;

	@GetMapping("journalists")
	@Security.Employees.CanAccessEmployees
	public CollectionModel<JournalistResponse> getJournalists(
			@PageableDefault(direction = Direction.DESC, size = 10) Pageable pageable) {

		return this.journalistmapper.toJournalistCollectionResponse(journalists
				.findAll(EmployeeSpecs.journalistWithNewspaperId(securityProvider.getNewspaperId()), pageable));
	}

	@GetMapping("administrators")
	@Security.Employees.CanAccessEmployees
	public CollectionModel<AdministratorResponse> getAdministrators(
			@PageableDefault(direction = Direction.DESC, size = 10) Pageable pageable) {

		return this.admMapper.toAdministratorCollectionResponse(administrators
				.findAll(EmployeeSpecs.administratorWithNewspaperId(securityProvider.getNewspaperId()), pageable));
	}

}
