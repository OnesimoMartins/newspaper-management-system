package ao.martins.newspaper.core.security.keys;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Validated
@Component
public class SecretKey {

	@NotNull
	@Value("${app.auth.secret}")
	private String location;
	
	public String getSecretKey() {
		try {
			return Files.readString(Paths.get(this.location));
		} catch (IOException e) {
			throw new RuntimeException("could not read the key beacause 'location' is null");
		}
	}
}
