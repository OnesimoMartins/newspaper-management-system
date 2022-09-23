package ao.martins.newspaper.infrastructure.repository.specs;

import org.springframework.data.jpa.domain.Specification;

import ao.martins.newspaper.domain.entity.Magazine;

public class MagazineSpecs {
	
	 public static Specification<Magazine> withNewspaperId(Long id){

		 return (root,query,builder)->{
			 return builder.equal(root.get("newspaper").get("id"), id);
		 };
	 }

	 
}
