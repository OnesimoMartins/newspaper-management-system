package ao.martins.newspaper.api.dto.response;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonProperty;

import ao.martins.newspaper.domain.entity.Category;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class ArticleResponse extends RepresentationModel<ArticleResponse>{
	
	private Long id;
	
	private String tittle;
	
	private String summary;
	
	private String keywords;
	
	private String state;
	
	private String body;
	
	@JsonProperty("magazine_edition")
	private Long magazineEdition;
	
	private MagazineResponse magazine; 
	
	@JsonProperty("journalist_creator") 
	private JournalistResponse journalist;
	
	@JsonProperty("category")
	private Category category;
	
	
}
