package ao.martins.newspaper.api.v1.controller;

import java.io.IOException;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sun.istack.NotNull;

import ao.martins.newspaper.api.dto.input.ArticleInput;
import ao.martins.newspaper.api.dto.mapper.ArticleMapper;
import ao.martins.newspaper.api.dto.response.ArticleResponse;
import ao.martins.newspaper.api.openapi.controller.ArticleControllerOpenApi;
import ao.martins.newspaper.core.security.api.NewspaperManagementSecutiyProvider;
import ao.martins.newspaper.core.security.api.Security;
import ao.martins.newspaper.domain.entity.Article;
import ao.martins.newspaper.domain.entity.enums.State;
import ao.martins.newspaper.domain.exception.BusinessException;
import ao.martins.newspaper.domain.repository.ArticleRepository;
import ao.martins.newspaper.domain.service.ArticleService;
import ao.martins.newspaper.domain.service.FileService;
import ao.martins.newspaper.infrastructure.repository.specs.ArticleSpecs;

//@Slf4j
@RequestMapping("v1/articles")
@RestController
public class ArticleController implements ArticleControllerOpenApi {

	@Autowired
	private ArticleRepository articles;
	@Autowired
	private ArticleService articleService;
	@Autowired
	private FileService fileService;
	@Autowired
	private ArticleMapper mapper;

	@Autowired
	private NewspaperManagementSecutiyProvider securityHolder;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@Security.Acticles.CanCreateArticle
	public ArticleResponse create(@Valid @RequestBody ArticleInput input) {

		var article = mapper.toArticleObject(input);

		article.getCreator().setId(securityHolder.getUserId());

		article = articleService.saveArticle(article);
		return mapper.toArticleResponse(article);
	}

	@GetMapping("/{articleId}")
	@Security.Acticles.CanAccessArticle

	public ArticleResponse findOne(@PathVariable Long articleId) {

		var article = articleService.findArticleByIdOrThrows(articleId);

		var response = mapper.toArticleResponse(article);

		response.setBody(fileService.getFileText(article.getPath()));

		return response;
	}
 
	@GetMapping
	public CollectionModel<ArticleResponse> getArticles(@RequestParam Optional<String> state,
			@PageableDefault(direction = Direction.ASC, size = 10) Pageable pageable) {

		try {
			Thread.sleep(100L);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		
		var spec = ArticleSpecs.withNewspaperId(this.securityHolder.getNewspaperId());

		try {
			if (state.isPresent()) 
				spec = spec.and(ArticleSpecs.withState(State.fromString(state.get())));
			
		} catch (IllegalArgumentException e) {

			throw new BusinessException(String.format("'%s' is not a valid type of article state.", state.get()));
		}

		return this.mapper.toArticleCollectionResponse(articles.findAll(spec, pageable));
	}

	@PutMapping("{id}/upload-body")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> uploadBody(@PathVariable Long id, @NotNull @RequestPart MultipartFile file) {

		Article article = articleService.findArticleByIdOrThrows(id);

		try {
			fileService.SaveFile(article.getPath(), file.getBytes());
		} catch (IOException e) {
		}
 
		return ResponseEntity.noContent().build();
	}

}