package ao.martins.newspaper.core.security.keys;

import javax.validation.constraints.NotNull;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import lombok.Getter;
import lombok.Setter;

/*
 * Esta classe seria utilizada para a autenticacao RSA
 * em futuras versoes do projecto esta será a forma de assinatura de tokens
 * por enquanto, o uso desta classe é dispensável, pois, não esta devidamente implementada.
 * 
 * 
 * */
 


@Deprecated
@Setter
@Getter
//@Validated
@Component
@ConfigurationProperties("app.auth.jks")
public class KeyStoreProperties {
	
	@NotNull
	private String keypairAlias;
	@NotNull
	private Resource path;
	@NotNull
	private String storepass;
	@NotNull
	private String keystorePassword;
}
