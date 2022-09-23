package ao.martins.newspaper.core.security.authorizarion;

import java.time.Duration;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import ao.martins.newspaper.core.security.keys.SecretKey;
import ao.martins.newspaper.core.security.token.UserDetailsTokenEnhancer;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter{

	@Autowired
	private JpaUserDetailsService userDetailsService;
	
	@Autowired
	private AuthenticationManager authManager;

//	@Autowired
//	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private SecretKey secretKey;
	
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		
		TokenEnhancerChain tokenEnhancerChain= new TokenEnhancerChain();
		tokenEnhancerChain.setTokenEnhancers(List.of(tokenEnhancer(), jwtConverter() ));

		endpoints
	.authenticationManager(authManager)
	.userDetailsService(  userDetailsService)
	.tokenEnhancer(tokenEnhancerChain)
	.tokenStore(new InMemoryTokenStore())
	.reuseRefreshTokens(false);
	}
	
	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		security.checkTokenAccess("permitAll()");
	}
	
	
	
	@Override 
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.inMemory()
		.withClient("FRONTEND")
		.secret(  "SECRET")
		.authorizedGrantTypes("password","refresh_token")
		.scopes("READ","WRITE")
		.accessTokenValiditySeconds((int) Duration.ofMinutes(340).toSeconds())
//		.accessTokenValiditySeconds((int)Duration.ofMinutes(2).toSeconds())
		.refreshTokenValiditySeconds((int)Duration.ofHours(100).toSeconds());
	}
	
	@Bean
	public JwtAccessTokenConverter jwtConverter() {
		
		JwtAccessTokenConverter jwt = new JwtAccessTokenConverter();
		jwt.setSigningKey(this.secretKey.getSecretKey());
		return jwt;
	}
	
	public TokenEnhancer tokenEnhancer() {
		return new UserDetailsTokenEnhancer();
	}

}
