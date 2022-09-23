package ao.martins.newspaper.domain.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;

import org.springframework.data.domain.AbstractAggregateRoot;

import ao.martins.newspaper.domain.event.AvaliationCreatedEvent;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Entity
@Getter
@Setter
public class Avaliation extends AbstractAggregateRoot<Avaliation> {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Byte mark;

	@ManyToOne
	private Journalist journalist;

	@ManyToOne
	private Article article;

	private String comment;

	private LocalDateTime date;
	
	@PrePersist
	private void beforePersist() {
		registerEvent(new AvaliationCreatedEvent(this));
	}
	
	@Override
	public boolean equals(Object obj) {
		return obj instanceof Avaliation && 
				((Avaliation) obj).getArticle().getId()== this.getArticle().getId() &&
						((Avaliation) obj).getJournalist().getId()== this.getJournalist().getId();
	}
}
