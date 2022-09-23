package ao.martins.newspaper.core.swaggerconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
@Profile("dev")
public class SwaggerConfig {
	
	@Bean
	public Docket apiDocketV1() {
		return new
				Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors
						.basePackage("ao.martins.newspaper.api.v1.controller"))
				.paths(PathSelectors.ant("/v1/**"))
				.build()
	
				
				
				
				.groupName("V1")
				.apiInfo(apiInfov1())
				.useDefaultResponseMessages(false);
	}
	
	private ApiInfo apiInfov1() {
		return new ApiInfoBuilder()
				.version("1.0")
				
				.contact(new Contact("Onesimo Martins"
						,"","onesimogouveiamartins@gmail.com"))
				.description("Api aberta para auxiliar o time de desenvolvimeto frontend")
				.title("Newspaper Management Api")
				.build();
	}
	
}
