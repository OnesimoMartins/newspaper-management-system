package ao.martins.newspaper.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ao.martins.newspaper.domain.entity.Newspaper;

public interface NewspaperRepository extends JpaRepository<Newspaper,Long>{

	@Query(value=
			"select count(*) from journalist where newspaper_id=:id",nativeQuery=true)
	Long getJournalistsQuantity(@Param("id") Long newspaperId);
}
