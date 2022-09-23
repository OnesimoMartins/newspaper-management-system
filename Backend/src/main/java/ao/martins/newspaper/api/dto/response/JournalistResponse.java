package ao.martins.newspaper.api.dto.response;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import ao.martins.newspaper.domain.entity.enums.ExperienceLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class JournalistResponse {
	
	private Long id;
	
	@JsonProperty("first_name")
	private String firstName;

	@JsonProperty("last_name")
	private String lastName;
	
	@JsonProperty("recruitment_date")
	private LocalDateTime recruitmentDate;
	
	private String email;
//	private Newspaper newspaper;
	
	@JsonProperty("experience_level")
	private ExperienceLevel experienceLevel;

}
