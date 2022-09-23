package ao.martins.newspaper.api.dto.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.PagedModel.PageMetadata;
import org.springframework.stereotype.Component;

import ao.martins.newspaper.api.NewspaperManagementLinks;
import ao.martins.newspaper.api.dto.input.ArticleInput;
import ao.martins.newspaper.api.dto.response.ArticleResponse;
import ao.martins.newspaper.api.dto.response.JournalistResponse;
import ao.martins.newspaper.api.dto.response.MagazineResponse;
import ao.martins.newspaper.domain.entity.Article;
import ao.martins.newspaper.domain.entity.Category;
import ao.martins.newspaper.domain.entity.Journalist;

@Component
public class ArticleMapper {

	@Autowired
	private NewspaperManagementLinks links;
	
	public Article toArticleObject(ArticleInput input) {
		var article = new Article();

		article.setCategory(new Category());
		article.getCategory().setId(input.getCategoryId());

		article.setCreator(new Journalist());

		article.setKeywords(input.getKeywords());
		article.setSummary(input.getSummary());
		article.setTittle(input.getTittle());

		return article;
	}


	public ArticleResponse toArticleResponse(Article article) {
		
		var articleResponse= ArticleResponse.builder()
				.category(article.getCategory())
				.id(article.getId())
				.magazineEdition(article.getMagazineEdition())
				.journalist(
            JournalistResponse.builder()
						.id(article.getCreator().getId())
						.email(article.getCreator().getWorkerDetails().getEmail())
						.firstName(article.getCreator().getWorkerDetails().getFirstName())
						.lastName(article.getCreator().getWorkerDetails().getLastName())
						.build()
						)
				
				.keywords(article.getKeywords())
				.tittle(article.getTittle())
				.state(article.getState().name())
				.summary(article.getSummary()).build();
		
		if(article.getMagazine()!=null) {
			articleResponse.setMagazine(
				MagazineResponse.builder()
				.id(article.getMagazine().getId())
				.name(article.getMagazine().getName())
				
				.build());
		}else
			articleResponse.setMagazine(new MagazineResponse());
		
		articleResponse.add(links.linkToArticle(articleResponse.getId()));
		
		articleResponse.add(links.linkToUploadArticleBody(articleResponse.getId(),"upload_body"));
		
		articleResponse.add(links.linkToArticleAvaliation(articleResponse.getId(),"avaliation"));
		
		 return articleResponse;
	}
	
	public CollectionModel<ArticleResponse> toArticleCollectionResponse(Page<Article> articles){
		
		var content=articles.stream().map(this::toArticleResponse).toList();
				
		PageMetadata metadata=
				new PageMetadata(articles.getSize(), articles.getNumber(), articles.getTotalElements());
		
		
		CollectionModel<ArticleResponse> response=PagedModel.of(content,metadata);		
		
		if(articles.hasNext()) {
			response.add(links.linkToArticles("next_articles",articles.nextPageable()));
		}
		if(!articles.isFirst()) {
			response.add(links.linkToArticles("previous_articles",articles.previousOrFirstPageable()));
		}
		
		return response;
				
	}
	
}
