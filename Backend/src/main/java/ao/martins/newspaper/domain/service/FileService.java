package ao.martins.newspaper.domain.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class FileService {

	@Value("${articles.source}")
	private String ARTICLES_SRC;

	public String buildArticleFilePath() {
		LocalDate date = LocalDate.now();

		Path path = Paths.get(this.ARTICLES_SRC.concat("/").concat(date.toString()).concat("/")
				.concat(UUID.randomUUID().toString()).concat(".txt"));
		
		this.log.debug(path.toString());
		
		return path.toString();
	}

	public Boolean SaveFile(String path, byte[] bytes) {
		File file = new File(path);
		try {
			file.createNewFile();
			Files.write(Paths.get(path), bytes, StandardOpenOption.APPEND);
			return true;
		} catch (IOException e) {

			if (file.getParentFile().mkdirs()) {
				try {
					file.createNewFile();
					Files.write(Paths.get(path), bytes, StandardOpenOption.APPEND);
					return true;
				} catch (IOException e1) {
					return false;
				}
			}

			return false;
		}

	}
	
	public String getFileText(String path) {
		try {
			return Files.readString(Paths.get(path));
		} catch (IOException e) {
			return null;
		}
	}
}