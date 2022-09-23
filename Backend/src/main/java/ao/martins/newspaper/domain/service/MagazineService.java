package ao.martins.newspaper.domain.service;

import java.sql.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ao.martins.newspaper.domain.entity.Administrator;
import ao.martins.newspaper.domain.entity.Article;
import ao.martins.newspaper.domain.entity.Magazine;
import ao.martins.newspaper.domain.entity.enums.AdministrationRole;
import ao.martins.newspaper.domain.exception.BusinessException;
import ao.martins.newspaper.domain.exception.InexistentMagazineEditionException;
import ao.martins.newspaper.domain.exception.NotSufficientArticlesException;
import ao.martins.newspaper.domain.exception.UserNotAllowedException;
import ao.martins.newspaper.domain.projection.MagazineEdition;
import ao.martins.newspaper.domain.repository.MagazineRepository;

@Service
public class MagazineService {

	@Autowired
	private EntityManager manager;
	@Autowired
	private NewspaperService newspaperService;
//	@Autowired
//	private ArticleService artcileService;
	@Autowired
	private MagazineRepository magazines;
	@Autowired
	private AdministratorService admService;

	@Transactional
	public MagazineEdition createNewEdition(Magazine magazine) throws BusinessException {
		var lastPublication = this.getMagazineLastPublication(magazine.getId()).getEdition();

		// cannot create a new edition if the last one wasnt publicated
		if (magazine.getLastEdition() != lastPublication) {
			throw new BusinessException("cannot create new edition while the last one was not published.");
		}

		magazine.setLastEdition(magazine.getLastEdition() + 1);
		magazine = this.magazines.save(magazine);

		return MagazineEdition.builder().edition(magazine.getLastEdition()).magazineId(magazine.getId()).build();
	}

	public MagazineEdition getMagazineEdition(Magazine magazine, Long edition)
			throws InexistentMagazineEditionException {

		if (edition > magazine.getLastEdition())
			throw new InexistentMagazineEditionException(
					"This magazine edition wasnt created yet. last edition is " + magazine.getLastEdition());

		Query query = manager
				.createNativeQuery("select date from magazine_publication where magazine_id=?" + " and edition=?");

		query.setParameter(1, magazine.getId());
		query.setParameter(2, edition);

		Date date = null;

		try {
			date = (Date) query.getSingleResult();
		} catch (NoResultException e) {
			// TODO handle it
//			System.out.println("NAO PUBLICADO");
		}

		MagazineEdition magazineEdititon = MagazineEdition.builder().date(date != null ? date.toLocalDate() : null)
				.articles(this.findArticlesByMagazineEdition(magazine.getId(), edition)).edition(edition)
				.magazineId(magazine.getId()).build();

		return magazineEdititon;

	};

	public Magazine updateMagazine(Magazine magazine) {
		return this.magazines.save(magazine);
	}

	public Magazine findMagazineByIdOrThrows(Long id) {
		return this.magazines.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("No Magazine with id " + id + " was found."));
	}

	public List<Article> findArticlesByMagazineEdition(Long magazineId, Long edition) {
		this.findMagazineByIdOrThrows(magazineId);

		Query query = manager.createNativeQuery("select *from article a where a.magazine_edition=? and a.magazine_id=?",
				Article.class);

		query.setParameter(1, edition);
		query.setParameter(2, magazineId);

		return (List<Article>) query.getResultList();
	}

	// TODO add the rest of logic on security
	public Magazine createMagazine(Magazine magazine) throws UserNotAllowedException {

		magazine.setNewspaper(newspaperService.findNewspaperOrTrhows(magazine.getNewspaper().getId()));

		var adm = magazine.getNewspaper().getCeo();

		Administrator publisher = admService.findByIdOrThrows(magazine.getPublisher().getId());

		if (!publisher.getAdministrativeRole().equals(AdministrationRole.PUBLISHER)) {
			throw new UserNotAllowedException("Only Administrator with role Publisher is allowed in this position.");
		}

		if (publisher.getWorkerDetails().getNewspaper().getId() != adm.getWorkerDetails().getNewspaper().getId()) {
			throw new UserNotAllowedException("Only publisher in the same newspaper can be the publisher of magazine.");
		}

		magazine.setPublisher(publisher);

		return magazines.save(magazine);
	}

//	public void addArticle(Long magazineId, Long articleId) {
//
//		var magazine = this.findMagazineByIdOrThrows(magazineId);
//
//		var article = artcileService.findArticleByIdOrThrows(articleId);
//
//		// add the this article to the
//	}

	@Transactional
	public MagazineEdition publishMagazine(Magazine magazine) throws BusinessException {

		MagazineEdition lastEdition = getMagazineEdition(magazine, magazine.getLastEdition());
		MagazineEdition lastPublication = getMagazineLastPublication(magazine.getId());

		if (lastEdition.getEdition() == lastPublication.getEdition()) {
			throw new BusinessException(
					"The last edition (" + lastEdition.getEdition() + ") created was already publised");
		}

		if (lastEdition.getArticles().size() < 1) {
			throw new NotSufficientArticlesException("magazine must have at least 1 article to be able to published");
		}

		Query query = manager
				.createNativeQuery("insert into magazine_publication(magazine_id,edition,date) values(?,?,now());");

		query.setParameter(1, magazine.getId());
		query.setParameter(2, magazine.getLastEdition());
		query.executeUpdate();

		return this.getMagazineLastPublication(magazine.getId());
	}

//	public Magazine updateMagazene() {
//	}

	public MagazineEdition getMagazineLastPublication(Long id) throws InexistentMagazineEditionException {

		Query query = manager.createNativeQuery(
				"select edition from magazine_publication where magazine_id=?  order by edition desc LIMIT 1");

		query.setParameter(1, id);

		Magazine magazine = this.findMagazineByIdOrThrows(id);
		Integer edition = (Integer) query.getSingleResult();

		return this.getMagazineEdition(magazine, edition.longValue());
	}

//	void removeArticle()

}
