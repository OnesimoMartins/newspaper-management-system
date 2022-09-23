package ao.martins.newspaper.domain.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Category {
	
	@Id
	private Long id;
	private String name;
}
