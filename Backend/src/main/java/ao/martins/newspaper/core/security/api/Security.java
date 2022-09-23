package ao.martins.newspaper.core.security.api;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import org.springframework.security.access.prepost.PreAuthorize;

public @interface Security {

	public @interface Acticles {

		@Retention(RUNTIME)
		@Target(METHOD)
		@PreAuthorize("@newspaperManagementSecutiyProvider.worksInSameNewspaper(#articleId)")
		public @interface CanAccessArticle {}
		
		@Retention(RUNTIME)
		@Target(METHOD)
		@PreAuthorize("@newspaperManagementSecutiyProvider.isJournalist()")
		public @interface CanCreateArticle {}
		
		@Retention(RUNTIME)
		@Target(METHOD)
		@PreAuthorize("@newspaperManagementSecutiyProvider.isJournalist() and "
				+"@newspaperManagementSecutiyProvider.worksInSameNewspaper(#articleId)")
		public @interface CanAvaliateArticle {}
		
	}

	public @interface Users {
		public @interface CanAccessUser {}
		public @interface CanCreateUser {}
	}
	
	public @interface Employees {
		
		@Retention(RUNTIME)
		@Target(METHOD)
		@PreAuthorize("@newspaperManagementSecutiyProvider.isADM()")
		public @interface CanAccessEmployees {}
	}
	
	
	public @interface Magazines {
		
		@Retention(RUNTIME)
		@Target(METHOD)
		@PreAuthorize("@newspaperManagementSecutiyProvider.isJournalist() and "
				+"@newspaperManagementSecutiyProvider.worksInSameNewspaper(#articleId)")
		public @interface CanManageMagazine {}
	
		@Retention(RUNTIME)
		@Target(METHOD)
		@PreAuthorize("@newspaperManagementSecutiyProvider.worksInSameNewspaper(#articleId)")
		public @interface CanAccessMagazine {}
		
		@Retention(RUNTIME)
		@Target(METHOD)
		@PreAuthorize("@newspaperManagementSecutiyProvider.worksInSameNewspaper(#articleId) and ( "+
		"@newspaperManagementSecutiyProvider.isCEO() or "+
		"@newspaperManagementSecutiyProvider.isPublisher())")
		public @interface CanPulishMagazine {}
	
	}
	

}
