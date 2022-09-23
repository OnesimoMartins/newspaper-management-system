package ao.martins.newspaper.domain.entity;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Journalist implements WorkerDetails  {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; 
	
	@Embedded
	private Worker workerDetails;
	
	@Override
	public Worker getWorkerDetails() {
		return this.workerDetails;
	}

}
