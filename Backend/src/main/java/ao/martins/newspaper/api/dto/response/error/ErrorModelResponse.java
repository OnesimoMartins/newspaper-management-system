package ao.martins.newspaper.api.dto.response.error;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@JsonInclude(content = Include.NON_NULL)
public class ErrorModelResponse {

	private int status;
	
	private String message;
	
	@Builder.Default
	private LocalDateTime timestamp=LocalDateTime.now();
}
