package ao.martins.newspaper.domain.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

import ao.martins.newspaper.domain.entity.Article;
import ao.martins.newspaper.domain.entity.enums.State;
import ao.martins.newspaper.domain.event.AvaliationCreatedEvent;
import ao.martins.newspaper.domain.repository.ArticleRepository;
import ao.martins.newspaper.domain.repository.AvaliationRepository;
import ao.martins.newspaper.domain.repository.NewspaperRepository;

@Component
public class AvaliationCreationListener {

	@Autowired
	private AvaliationRepository avaliations;

	@Autowired
	private NewspaperRepository newspapers;

	@Autowired
	private ArticleRepository articles;

	@TransactionalEventListener
	void onAvaliationCrated(AvaliationCreatedEvent e) {

		System.out.println("UMA NOVA AVALIACAO FOI PUBLICADA");// log
		Long articleId = e.getAvaliation().getArticle().getId();

		Long totalAvalations = avaliations.getAvaliationsCount(articleId,
				e.getAvaliation().getJournalist().getWorkerDetails().getNewspaper().getId());

		// -1 becouse journalist that has submited cannot be counted
		Long totalJounalists = newspapers.getJournalistsQuantity(
				e.getAvaliation().getJournalist().getWorkerDetails().getNewspaper().getId()) - 1;

		Integer percentage = Math.round(((float) totalAvalations / totalJounalists) * 100);

		// if 75 percent of journalists has avaliated this article and it has a good
		// media
		// set it as approved else set as refused

		if (percentage >= 75) {

			Long avg = Math.round(this.avaliations.getArticleAVG(articleId));
			Article article = e.getAvaliation().getArticle();

			if (article.getState().equals(State.APPLIED)) {

				if (avg >= 7)
					article.setState(State.APPROVED);
				else
					article.setState(State.REFUSED);

				articles.updateSate(article.getId(), article.getState().toString());

			}
		}
	
	

	}
}
