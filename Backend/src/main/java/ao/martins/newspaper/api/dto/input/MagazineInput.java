package ao.martins.newspaper.api.dto.input;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class MagazineInput {

	@NotBlank
	private String name;
	
	@NotNull(message = "The newspaper_id must not be null")
	@JsonProperty("newspaper_id")
	private Long newspaperId;
	
	@NotNull(message = "The publisher_id must not be null")
	@JsonProperty("publisher_id")
	private Long publisherId;
}
