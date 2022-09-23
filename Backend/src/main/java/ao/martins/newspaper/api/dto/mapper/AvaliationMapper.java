package ao.martins.newspaper.api.dto.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ao.martins.newspaper.api.dto.input.AvaliationInput;
import ao.martins.newspaper.api.dto.response.ArticleResponse;
import ao.martins.newspaper.api.dto.response.AvaliationResponse;
import ao.martins.newspaper.api.dto.response.JournalistResponse;
import ao.martins.newspaper.core.security.api.NewspaperManagementSecutiyProvider;
import ao.martins.newspaper.domain.entity.Article;
import ao.martins.newspaper.domain.entity.Avaliation;
import ao.martins.newspaper.domain.entity.Journalist;

@Component
public class AvaliationMapper {
	
	@Autowired
	private NewspaperManagementSecutiyProvider secutiyProvider;

	public Avaliation toAvaliationObject(AvaliationInput input) {
		
		Avaliation avaliation=new Avaliation();
		avaliation.setArticle(new Article());
		avaliation.getArticle().setId(input.getArticleId());
		
		avaliation.setJournalist(new Journalist());
		avaliation.getJournalist().setId(secutiyProvider.getUserId());
		
		avaliation.setComment(input.getComment());
		avaliation.setMark(input.getMark());
	
		return avaliation;
	}
	
	public AvaliationResponse toAvaliationResponse(Avaliation avaliation) {
		var article= ArticleResponse.builder().tittle(avaliation.getArticle().getTittle())
				.id(avaliation.getArticle().getId())
//				.summary(summary)
		.build();
		
		
		var journalist= JournalistResponse.builder().id(avaliation.getJournalist().getId())
				.firstName(avaliation.getJournalist().getWorkerDetails().getFirstName())
				.email(avaliation.getJournalist().getWorkerDetails().getEmail()).build();
		
	return 	AvaliationResponse.builder().article(article).comment(avaliation.getComment())
		.date(avaliation.getDate()).id(avaliation.getId()).mark(avaliation.getMark())
		.journalist(journalist).build();
	}
}
