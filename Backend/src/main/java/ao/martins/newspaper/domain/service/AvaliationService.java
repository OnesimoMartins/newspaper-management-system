package ao.martins.newspaper.domain.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import ao.martins.newspaper.domain.entity.Article;
import ao.martins.newspaper.domain.entity.Avaliation;
import ao.martins.newspaper.domain.entity.enums.State;
import ao.martins.newspaper.domain.exception.ArticleAlreadyApprovedException;
import ao.martins.newspaper.domain.exception.ArticleAlreadyMarkedException;
import ao.martins.newspaper.domain.exception.InvalidAvaliatorException;
import ao.martins.newspaper.domain.repository.AvaliationRepository;

@Service
public class AvaliationService {

	private JournalistService journalistService;
	private ArticleService articleService;
	private AvaliationRepository avaliations;

	public AvaliationService(ArticleService articleService, JournalistService journalistService,
			AvaliationRepository approvations) {
		this.articleService = articleService;
		this.journalistService = journalistService;
		this.avaliations = approvations;

	}

	public Avaliation createAvaliation(Avaliation approvation)
			throws ArticleAlreadyMarkedException, ArticleAlreadyApprovedException, InvalidAvaliatorException {

		var article = this.articleService.findArticleByIdOrThrows(approvation.getArticle().getId());

		if (verifyIfArticleWasApproved(article))
			throw new ArticleAlreadyApprovedException("This article was already approved, no avaliation is needed");

		var journalist = this.journalistService.findByIdOrThrows(approvation.getJournalist().getId());
	
		
		approvation.setArticle(article);
		approvation.setJournalist(journalist);
		
		if(verifyIfAvaliatorIsTheCreator(approvation))
			throw new InvalidAvaliatorException(
					"The creator journalist cannot avaliate his own article");	
		
	
		if (verifyIfAvaliationExists(approvation.getArticle().getId(), 
				approvation.getJournalist().getId()))
			
			throw new ArticleAlreadyMarkedException(
					"This article is already marked by" + " journalist with id " + approvation.getJournalist().getId());

//			 the value of mark depends on experience level of journalist
		
		switch (approvation.getJournalist().getWorkerDetails().getExperienceLevel()) {
		case JUNIOR: approvation.setMark((byte) Math.round(approvation.getMark()*0.7) );
			break;
		case MID_LEVEL:approvation.setMark((byte) Math.round(approvation.getMark()*0.85) );
			break;
		case SENIOR:// case the journalist is senior, his mark is 100% 
			break;
		}
		
		approvation.setDate(LocalDateTime.now());

		return this.avaliations.save(approvation);

	}


	// verify if the article was already marked by this journalist
	private Boolean verifyIfAvaliationExists(Long journalistId, Long articleId) {
		return avaliations.findAvaliationByIdAndJournalist(articleId, journalistId).isPresent();
	}

	// verify if the article was already approved
	private Boolean verifyIfArticleWasApproved(Article article) {
		return article.getState().equals(State.APPROVED);
	}
//	private Boolean verifyIfAr(Article article) {
//		return article.getState().equals(State.APPROVED);
//	}

	// verify if the creator is avaliating his own article
	private Boolean verifyIfAvaliatorIsTheCreator(Avaliation approvation) {
		return approvation.getArticle().getCreator().getId() == approvation.getJournalist().getId();
	}

}
