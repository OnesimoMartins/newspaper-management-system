package ao.martins.newspaper.domain.service;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ao.martins.newspaper.domain.entity.Category;
import ao.martins.newspaper.domain.repository.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categories;
	
	public Category findCategoryById(Long id) {
		return this.categories.findById(id).orElseThrow(
				()->new EntityNotFoundException("Category with id "+id+" was not found.")
				);
	}
}
