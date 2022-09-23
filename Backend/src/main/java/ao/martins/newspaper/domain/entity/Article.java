package ao.martins.newspaper.domain.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import ao.martins.newspaper.domain.entity.enums.State;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Article {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String tittle;

	private String summary;

	@Enumerated(EnumType.STRING)
	private State state;

	@ManyToOne
	private Category category;

	@ManyToOne(fetch = FetchType.LAZY)
	private Magazine magazine;
	
	private Long magazineEdition;
//	private Integer position;

	private String keywords;

	private String path;
	

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "created_by")
	private Journalist creator;

	
//	public List<String> getKeywordsList() {
//		var words = List.of(this.keywords.split("#"));
//		words.remove(0);
//		return words;
//	}
	
	@Override
	public String toString() {
		return String.format("{ \n id=%s \n tittle=%s \n summary=%s \n state=%s "
				+ "\n category=%s \n keywords=%s \n paths=%s \n}"
				,id,tittle,summary,state,category/*,position*/,keywords,path);
	}


}
