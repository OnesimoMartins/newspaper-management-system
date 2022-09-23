package ao.martins.newspaper.api;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import ao.martins.newspaper.api.v1.controller.ArticleController;
import ao.martins.newspaper.api.v1.controller.AvaliationController;
import ao.martins.newspaper.api.v1.controller.MagazineController;

@Component
public class NewspaperManagementLinks {

	public Link linkToArticle(Long id) {
		return linkTo(methodOn(ArticleController.class)
							.findOne(id)).withRel(IanaLinkRelations.SELF);
	}
	
	public Link linkToArticles(String rel,Pageable pageable) {
		
		String str="";//linkTo(methodOn(ArticleController.class).getArticles(pageable)).toString();
		
		return Link.of(str+transformPageToUrl(pageable)).withRel(rel);
	}
	
    public Link linkToUploadArticleBody(Long id, String rel) {
		return linkTo(methodOn(ArticleController.class).uploadBody(id, null)).withRel(rel);
	}
		
	private String transformPageToUrl(Pageable pageable) {
		var result= new StringBuilder();
		
		result.append("?size=").append(pageable.getPageSize())
		.append("&page=").append(pageable.getPageNumber());
		
		return result.toString();
		
	}

	public Link linkToMagazinePublication(Long id) {
		return linkTo(methodOn(MagazineController.class).publishMagazine(id)).withRel("publish");
	}
	public Link linkToMagazineNewEdition(Long id) {
		return linkTo(methodOn(MagazineController.class).newEdition(id)).withRel("new_edition");
	}
	public Link linkToArticleAvaliation(Long id, String rel) {
		return linkTo(methodOn(AvaliationController.class).avaliate(id, null)).withRel(rel);
	}
}
