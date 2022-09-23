package ao.martins.newspaper.api.dto.mapper;

import org.springframework.data.domain.Page;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.PagedModel.PageMetadata;
import org.springframework.stereotype.Component;

import ao.martins.newspaper.api.dto.response.JournalistResponse;
import ao.martins.newspaper.domain.entity.Journalist;

@Component
public class JournalistMapper {

	public JournalistResponse toJournalistResponse(Journalist journalist) {
		return JournalistResponse.builder().email(journalist.getWorkerDetails().getEmail())
				.firstName(journalist.getWorkerDetails().getFirstName())
				.experienceLevel(journalist.getWorkerDetails().getExperienceLevel())
				.lastName(journalist.getWorkerDetails().getLastName())
				.id(journalist.getId())
				.recruitmentDate(journalist.getWorkerDetails().getRecruitmentDate())
				
				.build();
	}
	
	public CollectionModel<JournalistResponse> toJournalistCollectionResponse(Page<Journalist> journalists){
		
		var content=journalists.stream().map(this::toJournalistResponse).toList();
		
				
		PageMetadata metadata=
				new PageMetadata(journalists.getSize(), journalists.getNumber(), journalists.getTotalElements());
		
		CollectionModel<JournalistResponse> response=PagedModel.of(content,metadata);		
	
		return response;
				
	}
}
