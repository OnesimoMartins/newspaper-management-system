package ao.martins.newspaper.infrastructure.repository.specs;

import org.springframework.data.jpa.domain.Specification;

import ao.martins.newspaper.domain.entity.Administrator;
import ao.martins.newspaper.domain.entity.Journalist;

public class EmployeeSpecs {

	
	 public static Specification<Journalist> journalistWithNewspaperId(Long id){

		 return (root,query,builder)->{

			 return builder.equal(root.get("workerDetails")
					 .get("newspaper").get("id"), id);
		 };
	 }
	 public static Specification<Administrator> administratorWithNewspaperId(Long id){

		 return (root,query,builder)->{

			 return builder.equal(root.get("workerDetails")
					 .get("newspaper").get("id"), id);
		 };
	 }
	 
	 
}
