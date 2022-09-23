package ao.martins.newspaper.domain.projection;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import ao.martins.newspaper.domain.entity.Article;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MagazineEdition {

	private Long magazineId;
	private Long edition;
	
	@Builder.Default
	private LocalDate date=null;
	
	@Builder.Default
	private List<Article> articles=new ArrayList<Article>();
}
