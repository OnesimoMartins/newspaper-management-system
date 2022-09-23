package ao.martins.newspaper.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ao.martins.newspaper.domain.entity.Avaliation;

public interface AvaliationRepository extends JpaRepository<Avaliation, Long> {

	public List<Avaliation> findAllByArticleId(Long id);
	
	
	
	@Query(" from Avaliation where journalist_id=:id and article_id=:journalistId ")
	public Optional<Avaliation> findAvaliationByIdAndJournalist(@Param("id")Long id,
			@Param("journalistId") Long journalistId);

	
	@Query( value=
			"select count(*) from avaliation av inner join journalist jo on jo.newspaper_id=:newspaperId \r\n" + 
			" where av.journalist_id=jo.id and av.article_id=:articleId",
			nativeQuery=true
		)
	public Long getAvaliationsCount(@Param("articleId") Long article
			, @Param("newspaperId") Long newspaperId);
	
	
	@Query(value="select AVG(mark) from avaliation where article_id=:articleId",nativeQuery=true)
	public Double getArticleAVG(@Param("articleId") Long article);
}
