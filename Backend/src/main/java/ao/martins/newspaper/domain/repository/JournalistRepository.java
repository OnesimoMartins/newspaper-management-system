package ao.martins.newspaper.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ao.martins.newspaper.domain.entity.Journalist;

public interface JournalistRepository extends JpaRepository<Journalist,Long>,JpaSpecificationExecutor<Journalist> {
	
	@Query("from Journalist where email=:email")
	 Optional<Journalist>	findJournalistByEmail(@Param("email") String email);
}
