package ao.martins.newspaper.infrastructure.repository.specs;

import javax.persistence.criteria.Join;

import org.springframework.data.jpa.domain.Specification;

import ao.martins.newspaper.domain.entity.Article;
import ao.martins.newspaper.domain.entity.Journalist;
import ao.martins.newspaper.domain.entity.enums.State;

public class ArticleSpecs {

	 public static Specification<Article> withNewspaperId(Long id){

		 return (root,query,builder)->{
			 
			 Join<Article,Journalist>articleMagazine=root.join("creator");
			 
			 return builder.equal(articleMagazine.get("workerDetails")
					 .get("newspaper").get("id"), id);
		 };
	 }
	 
	 
	 public static Specification<Article> withState(State state){
		 return (root,query,builder)->builder.equal( root.get("state"),state);
	 }
	 
}
