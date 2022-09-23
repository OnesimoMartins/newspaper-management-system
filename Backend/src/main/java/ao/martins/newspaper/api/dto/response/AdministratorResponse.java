package ao.martins.newspaper.api.dto.response;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import ao.martins.newspaper.domain.entity.enums.ExperienceLevel;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class AdministratorResponse {

	private Long id;

	@JsonProperty("first_name")
	private String firstName;
	
	@JsonProperty("last_name")
	private String lastName;
	
	@JsonProperty("recruitment_date")
	private LocalDateTime recruitmentDate;

	private String email;

	private String role;

	@JsonProperty("experience_level")
	private ExperienceLevel experienceLevel;

}
