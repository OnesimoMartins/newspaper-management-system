package ao.martins.newspaper.api.dto.response;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MagazineEditionResponse {


//	@JsonProperty("magazine_id")
//	private Long magazineId;
//	
	@Setter
	private MagazineResponse magazine;
	
	private Long edition;
	
	@JsonProperty("publication_date")
	private LocalDate publicationDate;
	
	private List<ArticleResponse> articles;
	
	@Builder.Default
	private String status="PUBLISHED";
	
	
}
