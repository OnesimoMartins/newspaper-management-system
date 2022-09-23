package ao.martins.newspaper.api.v1.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ao.martins.newspaper.api.dto.input.AvaliationInput;
import ao.martins.newspaper.api.dto.mapper.AvaliationMapper;
import ao.martins.newspaper.api.dto.response.AvaliationResponse;
import ao.martins.newspaper.api.openapi.controller.AvaliationControllerOpenApi;
import ao.martins.newspaper.core.security.api.Security;
import ao.martins.newspaper.domain.service.AvaliationService;

@RestController
@RequestMapping("v1/")
public class AvaliationController implements AvaliationControllerOpenApi{

	@Autowired 
	private AvaliationService avaliationService;
	
	@Autowired 
	private AvaliationMapper avaliationMapper;

	@PostMapping("articles/{articleId}/avaliate")
	@Security.Acticles.CanAvaliateArticle
	public AvaliationResponse avaliate (@PathVariable Long articleId,
	@RequestBody @Valid AvaliationInput input){
	
		input.setArticleId(articleId);
	
		var avaliation=avaliationService
				.createAvaliation(avaliationMapper.toAvaliationObject(input));
		
		return avaliationMapper.toAvaliationResponse(avaliation);
	
	}

}
