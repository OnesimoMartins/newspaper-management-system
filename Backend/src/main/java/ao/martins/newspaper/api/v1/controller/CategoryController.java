package ao.martins.newspaper.api.v1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ao.martins.newspaper.api.openapi.controller.CategoryControllerOpenApi;
import ao.martins.newspaper.domain.entity.Category;
import ao.martins.newspaper.domain.repository.CategoryRepository;

@RestController
@RequestMapping("v1/categories")
public class CategoryController implements CategoryControllerOpenApi {

	@Autowired
	private CategoryRepository categories;
	
		@GetMapping
	public List<Category> allCategories(){
		return this.categories.findAll();
	}
}
