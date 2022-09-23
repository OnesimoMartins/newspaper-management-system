package ao.martins.newspaper.api.v1.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import ao.martins.newspaper.api.dto.input.MagazineInput;
import ao.martins.newspaper.api.dto.mapper.MagazineMapper;
import ao.martins.newspaper.api.dto.response.MagazineEditionResponse;
import ao.martins.newspaper.api.dto.response.MagazineResponse;
import ao.martins.newspaper.api.openapi.controller.MagazineControllerOpenApi;
import ao.martins.newspaper.core.security.api.NewspaperManagementSecutiyProvider;
import ao.martins.newspaper.domain.entity.Magazine;
import ao.martins.newspaper.domain.exception.UserNotAllowedException;
import ao.martins.newspaper.domain.repository.MagazineRepository;
import ao.martins.newspaper.domain.service.MagazineService;
import ao.martins.newspaper.infrastructure.repository.specs.MagazineSpecs;

@RestController
@RequestMapping("v1/magazines")
public class MagazineController  implements MagazineControllerOpenApi {

	@Autowired
	private MagazineMapper magazineMapper;

	@Autowired
	private MagazineService magazineService;

	@Autowired
	private MagazineRepository magazines;

	@Autowired
	private NewspaperManagementSecutiyProvider securityProvider;

	@GetMapping("/{magazineId}")
	public MagazineResponse findMagazine(@PathVariable Long magazineId) {
		return magazineMapper.toMagazineResponse(magazineService.findMagazineByIdOrThrows(magazineId));
	}

	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public MagazineResponse createMagazine(@RequestBody @Valid MagazineInput newMagazine,
			@PageableDefault(direction = Direction.DESC, size = 10) Pageable pageable) throws UserNotAllowedException {

		Magazine magazine = magazineService.createMagazine(magazineMapper.toMagazineObject(newMagazine));

		return magazineMapper.toMagazineResponse(magazine);
	}

	@PutMapping("{id}/editions")
	public MagazineEditionResponse newEdition(@PathVariable Long id) {
		var magazine = magazineService.findMagazineByIdOrThrows(id);

		var response = magazineMapper.toMagazineEditionResponse(magazineService.createNewEdition(magazine));

		response.setMagazine(magazineMapper.toMagazineResponse(magazine));

		return response;
	}

	@GetMapping("/{id}/editions/{edId}")
	public MagazineEditionResponse magazineEdition(@PathVariable Long id, @PathVariable("edId") Long edition) {
		return magazineMapper.toMagazineEditionResponse(
				this.magazineService.getMagazineEdition(magazineService.findMagazineByIdOrThrows(id), edition));
	}

	@PutMapping("{id}/publish")
	public MagazineEditionResponse publishMagazine(@PathVariable Long id) {
		Magazine magazine = magazineService.findMagazineByIdOrThrows(id);

		var response = magazineMapper.toMagazineEditionResponse(magazineService.publishMagazine(magazine));
		
		response.setMagazine(magazineMapper.toMagazineResponse(magazine));

		return response;

	}

	@GetMapping
	public CollectionModel<MagazineResponse> getMagazines(
			@PageableDefault(direction = Direction.DESC, size = 10) Pageable pageable) {

		return magazineMapper.toMagazineCollectionResponse(
				magazines.findAll(MagazineSpecs.withNewspaperId(securityProvider.getNewspaperId()), pageable));
	}

}
