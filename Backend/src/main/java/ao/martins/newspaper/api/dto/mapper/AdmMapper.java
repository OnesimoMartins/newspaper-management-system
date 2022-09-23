package ao.martins.newspaper.api.dto.mapper;

import org.springframework.data.domain.Page;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.PagedModel.PageMetadata;
import org.springframework.stereotype.Component;

import ao.martins.newspaper.api.dto.response.AdministratorResponse;
import ao.martins.newspaper.api.dto.response.JournalistResponse;
import ao.martins.newspaper.domain.entity.Administrator;
import ao.martins.newspaper.domain.entity.Journalist;

@Component
public class AdmMapper {

	public AdministratorResponse toAdmResponse(Administrator adm) {
		return  AdministratorResponse .builder().email(adm.getWorkerDetails().getEmail())
				.firstName(adm.getWorkerDetails().getFirstName())
				.experienceLevel(adm.getWorkerDetails().getExperienceLevel())
				.lastName(adm.getWorkerDetails().getLastName())
				.id(adm.getId())
				.role(adm.getAdministrativeRole().name())
				.recruitmentDate(adm.getWorkerDetails().getRecruitmentDate())
				
				.build();
				
	}
	
public CollectionModel<AdministratorResponse> toAdministratorCollectionResponse(Page<Administrator> adms){
		
		var content=adms.stream().map(this::toAdmResponse).toList();
		
				
		PageMetadata metadata=
				new PageMetadata(adms.getSize(), adms.getNumber(), adms.getTotalElements());
		
		CollectionModel<AdministratorResponse> response=PagedModel.of(content,metadata);		
	
		return response;
				
	}
}
