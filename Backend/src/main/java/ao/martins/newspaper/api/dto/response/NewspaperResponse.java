package ao.martins.newspaper.api.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class NewspaperResponse {

	private Long id;
	private String name;
}
