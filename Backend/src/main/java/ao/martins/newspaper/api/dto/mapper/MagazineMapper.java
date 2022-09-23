package ao.martins.newspaper.api.dto.mapper;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.PagedModel.PageMetadata;
import org.springframework.stereotype.Component;

import ao.martins.newspaper.api.NewspaperManagementLinks;
import ao.martins.newspaper.api.dto.input.MagazineInput;
import ao.martins.newspaper.api.dto.response.MagazineEditionResponse;
import ao.martins.newspaper.api.dto.response.MagazineResponse;
import ao.martins.newspaper.api.dto.response.NewspaperResponse;
import ao.martins.newspaper.domain.entity.Administrator;
import ao.martins.newspaper.domain.entity.Magazine;
import ao.martins.newspaper.domain.entity.Newspaper;
import ao.martins.newspaper.domain.projection.MagazineEdition;
import ao.martins.newspaper.domain.service.MagazineService;

@Component
public class MagazineMapper {


	@Autowired
	private NewspaperManagementLinks links;
	
	@Autowired
	private AdmMapper mapper;
	
	@Autowired
	private ArticleMapper articleMapper;
	
	@Autowired
	private MagazineService magazineService;
	
	

	public Magazine toMagazineObject(MagazineInput input) {
		Magazine magazine = new Magazine();
		magazine.setName(input.getName());

		magazine.setPublisher(new Administrator());
		magazine.getPublisher().setId(input.getPublisherId());

		magazine.setNewspaper(new Newspaper());
		magazine.getNewspaper().setId(input.getNewspaperId());

		return magazine;
	}

	public MagazineResponse toMagazineResponse(Magazine magazine) {
		
		var publisher=mapper.toAdmResponse(magazine.getPublisher());
		
		var administrator= mapper.toAdmResponse(magazine.getNewspaper().getCeo());
				
		var newspaper= NewspaperResponse.builder().id(magazine.getNewspaper().getId())
				.name(magazine.getName())
				.build();
		
		var magazineResponse= MagazineResponse.builder().id(magazine.getId())
				.name(magazine.getName())
				.administrator(administrator)
				.lastEdition(magazine.getLastEdition())
				.publisher(publisher)
				.newspaper(newspaper)
				.isLastEditionPublished(
						// if last edition has date means that was published
						magazineService.getMagazineEdition(magazine, magazine.getLastEdition())
						.getDate()!=null
						)
				.build();

		magazineResponse.add(links.linkToMagazinePublication(magazine.getId()));
		magazineResponse.add(links.linkToMagazineNewEdition(magazine.getId()));
		return magazineResponse;
	}

	public MagazineEditionResponse toMagazineEditionResponse(MagazineEdition magazine) {

		String status;
		if (magazine.getDate() != null)
			status = "PUBLISHED";
		else
			status = "NOT PUBLISHED";

	return MagazineEditionResponse.builder().articles(magazine.getArticles().stream()
			
	.map( articleMapper::toArticleResponse)
	.toList()).edition(magazine.getEdition()).publicationDate(magazine.getDate())
	//.magazineId(magazine.getMagazineId())
	.status(status).build();
	
	}

	public CollectionModel<MagazineResponse> toMagazineCollectionResponse(Page<Magazine> magazines){
		
		var content=magazines.stream().map(this::toMagazineResponse).toList();
		
				
		PageMetadata metadata=
				new PageMetadata(magazines.getSize(), magazines.getNumber(), magazines.getTotalElements());
		
		CollectionModel<MagazineResponse> response=PagedModel.of(content,metadata);		
		
		if(magazines.hasNext()) {
			response.add(links.linkToArticles("next_magazines",magazines.nextPageable()));
		}
		if(!magazines.isFirst()) {
			response.add(links.linkToArticles("previous_magazines",magazines.previousOrFirstPageable()));
		}
		
		return response;
		
	}
	public CollectionModel<MagazineResponse> toMagazineCollectionResponse(List<Magazine> magazines){
		
		var content=magazines.stream().map(this::toMagazineResponse).toList();
		
		return CollectionModel.of(content)		;
		
	}

}
