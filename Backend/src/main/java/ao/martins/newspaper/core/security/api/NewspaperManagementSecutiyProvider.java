package ao.martins.newspaper.core.security.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

import ao.martins.newspaper.domain.service.ArticleService;

@Component("newspaperManagementSecutiyProvider")
public class NewspaperManagementSecutiyProvider {
	

	
	@Autowired
	private ArticleService articleService;
	
	private Authentication getAuthentication() {
		return SecurityContextHolder.getContext().getAuthentication();
	}
	
	public boolean isJournalist() {
		SimpleGrantedAuthority role= new  SimpleGrantedAuthority("JRT");
		return this.getAuthentication().getAuthorities()
				.contains(role);
	}
	
	public boolean isCEO() {
		SimpleGrantedAuthority role= new  SimpleGrantedAuthority("ADM_CEO");
		return this.getAuthentication().getAuthorities()
				.contains(role);
	}
	public boolean isADM() {
		SimpleGrantedAuthority role= new  SimpleGrantedAuthority("ADM");
		return this.getAuthentication().getAuthorities()
				.contains(role);
	}
	
	public boolean isPublisher() {
		SimpleGrantedAuthority role= new  SimpleGrantedAuthority("ADM_PUBLISHER");
		return this.getAuthentication().getAuthorities()
				.contains(role);
	}
	
	public Long getNewspaperId() {
		Jwt jwt= (Jwt)  getAuthentication().getPrincipal();
		
		return jwt.getClaim("newspaper_id");
	}

	public Long getUserId() {
		Jwt jwt= (Jwt)  getAuthentication().getPrincipal();
		
		return jwt.getClaim("user_id");
	}
	
	public boolean worksInSameNewspaper(Long articleId) {
	
		System.out.println("\n\n\n "+articleId);
		
		return articleService.findArticleByIdOrThrows(articleId)
		.getCreator().getWorkerDetails().getNewspaper().getId()
		
		.equals(this.getNewspaperId());
		
	}
	
}
