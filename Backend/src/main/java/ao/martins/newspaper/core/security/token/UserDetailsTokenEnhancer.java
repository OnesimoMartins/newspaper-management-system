package ao.martins.newspaper.core.security.token;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import ao.martins.newspaper.core.security.authorizarion.AuthDetails;

@Configuration
public class UserDetailsTokenEnhancer implements TokenEnhancer {

	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken token, OAuth2Authentication auth) {
		
		AuthDetails user=(AuthDetails) auth.getPrincipal();
		
		var info= new HashMap<String, Object>();
		info.put("user_id",user.getId() );
		info.put("newspaper_id", user.getDetails().getNewspaper().getId());

		 ((DefaultOAuth2AccessToken)token).setAdditionalInformation(info);
		
		return token;
	}
	


}
