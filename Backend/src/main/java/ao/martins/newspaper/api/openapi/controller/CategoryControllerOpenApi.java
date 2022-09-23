package ao.martins.newspaper.api.openapi.controller;

import java.util.List;

import ao.martins.newspaper.domain.entity.Category;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags="Categories")
public interface CategoryControllerOpenApi {

	@ApiOperation("apresenta a lista de todas as categorias")
	public List<Category> allCategories();
}
