package ao.martins.newspaper.domain.service;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import ao.martins.newspaper.domain.entity.Article;
import ao.martins.newspaper.domain.entity.Magazine;
import ao.martins.newspaper.domain.entity.enums.State;
import ao.martins.newspaper.domain.exception.ActionNotAllowedException;
import ao.martins.newspaper.domain.exception.AticleNotPresentException;
import ao.martins.newspaper.domain.exception.InexistentMagazineEditionException;
import ao.martins.newspaper.domain.exception.MagazineEditionAlreadyPublishedException;
import ao.martins.newspaper.domain.projection.MagazineEdition;
import ao.martins.newspaper.domain.repository.ArticleRepository;

@Service
public class ArticleService {

	@Autowired
	private ArticleRepository articles;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private JournalistService JournalistService;
	@Autowired
	private FileService fileService;
	
	@Lazy
	@Autowired
	private MagazineService magazineService;
	
	public List<Article> findAll(){
		return this.articles.findAll();
	}

	public Article saveArticle(Article article) {

		article.setCategory(this.categoryService.findCategoryById(article.getCategory().getId()));

		article.setCreator(this.JournalistService.findByIdOrThrows(article.getCreator().getId()));

		article.setPath(fileService.buildArticleFilePath());

		article.setState(State.APPLIED);

		return this.articles.save(article);
	}


	public Article findArticleByIdOrThrows(Long id) {
		return articles.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("No Article with id " 
		+ id + " was found"));
	}

	public Article addArticleToMagazine(Article article, Magazine magazine) throws InexistentMagazineEditionException,
			MagazineEditionAlreadyPublishedException, ActionNotAllowedException {

		
		MagazineEdition lastPub = magazineService.getMagazineLastPublication(magazine.getId());

		
		if(!article.getState().equals(State.APPROVED)) {
			throw new ActionNotAllowedException("only articles with satatus 'APPROVED' can"
					+" be added to magazine.");
		}
		
		// verirfy if the current edition was already published
		if (lastPub.getEdition() == magazine.getLastEdition()) {
			throw new MagazineEditionAlreadyPublishedException(
					"could not add article to magazine becouse the current edition is already"
			+ " published"); 
		}
		if(article.getMagazine()!=null) {
			throw new ActionNotAllowedException("this article is already chained to"
					+ " magazine '"+article.getId()+"', edition '"+
					article.getMagazineEdition()+"'");
		}

	    article.setMagazine(magazine);
	    article.setMagazineEdition(magazine.getLastEdition());
		return this.articles.save(article);

	}

	public void removeArticleFromMagazine(Article article, Magazine magazine) 
			throws InexistentMagazineEditionException, MagazineEditionAlreadyPublishedException 
	,AticleNotPresentException{
		
		MagazineEdition lastPub = magazineService.getMagazineLastPublication(magazine.getId());

		// verirfy if the current edition was already published
		if (lastPub.getEdition() == magazine.getLastEdition()) {
			throw new MagazineEditionAlreadyPublishedException(
					"could not add article to magazine becouse the current edition is already"
			+ " published"); 
		}
		
		MagazineEdition lastEdition = magazineService.getMagazineEdition(magazine,
				magazine.getLastEdition());
	
		
		AtomicBoolean notPresent=new AtomicBoolean();
		notPresent.set(true);
		
		lastEdition.getArticles().stream().filter(it->
		it.equals(article)).findFirst().ifPresent(
			it->{
			
			article.setMagazine(null);
			article.setMagazineEdition(null);
			articles.save(article);
			notPresent.set(false);	
			});
	
		if(notPresent.get()){
			throw new AticleNotPresentException(
			String.format(("The article with id '%s' isnt on magazine  with id'%s' "
					+ ", edition '%s' "),article.getId()
					,magazine.getId(),magazine.getLastEdition()));	
		}	
	}

}
