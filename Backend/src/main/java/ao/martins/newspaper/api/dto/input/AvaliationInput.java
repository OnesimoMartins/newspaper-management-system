package ao.martins.newspaper.api.dto.input;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@AllArgsConstructor
@Getter
@ToString
public class AvaliationInput {

	@Min(1)
	@Max(10)
	@NotNull
	private Byte mark;

	@JsonProperty("article_id")
	private Long articleId; 

	private String comment;

}
