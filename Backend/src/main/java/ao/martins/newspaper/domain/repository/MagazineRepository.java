package ao.martins.newspaper.domain.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import ao.martins.newspaper.domain.entity.Magazine;

public interface MagazineRepository extends JpaRepository<Magazine, Long>,
JpaSpecificationExecutor<Magazine>{

}
