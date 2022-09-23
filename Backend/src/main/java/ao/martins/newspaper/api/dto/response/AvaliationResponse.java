package ao.martins.newspaper.api.dto.response;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AvaliationResponse {
	
	private Long id;

	private Byte mark;

	private JournalistResponse journalist;

	private ArticleResponse article;

	private String comment;

	private LocalDateTime date;

}
