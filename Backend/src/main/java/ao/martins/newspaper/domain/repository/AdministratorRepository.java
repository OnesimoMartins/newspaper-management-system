package ao.martins.newspaper.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ao.martins.newspaper.domain.entity.Administrator;

public interface AdministratorRepository extends JpaRepository<Administrator, Long> 
,JpaSpecificationExecutor<Administrator>{
	
	@Query("from Administrator where email=:email")
	Optional< Administrator>	findAdministratorByEmail(@Param("email")String email);
}
