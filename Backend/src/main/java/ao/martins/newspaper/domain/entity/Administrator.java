package ao.martins.newspaper.domain.entity;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import ao.martins.newspaper.domain.entity.enums.AdministrationRole;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Administrator implements WorkerDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Embedded
	private Worker workerDetails;
	
	@Enumerated(EnumType.STRING)
	private AdministrationRole administrativeRole;
	
	@Override
	public Worker getWorkerDetails() {
		return this.workerDetails;
	}
}
