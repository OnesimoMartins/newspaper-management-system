package ao.martins.newspaper.api.v1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import ao.martins.newspaper.api.NewspaperManagementLinks;
import ao.martins.newspaper.api.dto.response.RootEntryPoint;


@RestController
public class RootEntryPointController {

	@Autowired
	private NewspaperManagementLinks links;
	
	
	@GetMapping
	public RootEntryPoint root() {
		var root=new RootEntryPoint();
		
		root.add(links.linkToArticles("articles",PageRequest.of(0, 7)));
		
		return root;
	}

}
