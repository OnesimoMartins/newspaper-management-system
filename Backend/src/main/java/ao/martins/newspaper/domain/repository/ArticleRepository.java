package ao.martins.newspaper.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ao.martins.newspaper.domain.entity.Article;


public interface ArticleRepository extends JpaRepository<Article,Long>,
JpaSpecificationExecutor<Article>{

	@Query(value="update article set state=:state where id=:id",nativeQuery=true)
	public void updateSate(@Param("id") Long id,@Param ("state")String state);

}
 