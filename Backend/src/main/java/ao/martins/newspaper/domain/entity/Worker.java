package ao.martins.newspaper.domain.entity;

import java.time.LocalDateTime;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import ao.martins.newspaper.domain.entity.enums.ExperienceLevel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class Worker {
	
	private String firstName;

	private String lastName;
	
	private LocalDateTime recruitmentDate;
	
	private String email;
	
	private String password;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Newspaper newspaper;
	
	@Enumerated(EnumType.STRING)
	private ExperienceLevel experienceLevel;
	
}
