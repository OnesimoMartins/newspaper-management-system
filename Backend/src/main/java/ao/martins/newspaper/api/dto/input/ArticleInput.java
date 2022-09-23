package ao.martins.newspaper.api.dto.input;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@AllArgsConstructor
@ToString
@ApiModel(description = "representa a entrada de um artigo")
public class ArticleInput {

	@NotBlank
//	@Min(5)
	@ApiModelProperty(example = "Nova fábrica inaugurada",dataType = "java.lang.String"
	,required = true)
	private final String tittle;

	@NotNull
	@JsonProperty("category_id")
	@ApiModelProperty(example="1", required = true,
	dataType = "java.lang.Long")
	private final Long categoryId;

	@ApiModelProperty(required = false,dataType = "java.lang.String")
	private final String summary;

	@NotBlank
	@ApiModelProperty(required = true,example = "#Politica#Religiao#Economia",
	dataType = "java.lang.String")
	private String keywords;

}
