package ao.martins.newspaper.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ao.martins.newspaper.domain.entity.Category;

public interface CategoryRepository extends JpaRepository<Category,Long > {

}
