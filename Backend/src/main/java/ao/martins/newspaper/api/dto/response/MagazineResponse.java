package ao.martins.newspaper.api.dto.response;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class MagazineResponse  extends RepresentationModel<ArticleResponse> {

	private Long id;
	private String name;
	
	@JsonProperty("last_edition")
	private Long lastEdition;
	private NewspaperResponse newspaper;
	private AdministratorResponse administrator;
	private AdministratorResponse publisher;
	
	@JsonProperty("is_last_edition_published")
	private Boolean isLastEditionPublished;
	
}
