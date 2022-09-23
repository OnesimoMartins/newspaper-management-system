package ao.martins.newspaper.domain.event;

import ao.martins.newspaper.domain.entity.Avaliation;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AvaliationCreatedEvent {
private	Avaliation avaliation;
}
