package course.poja.demo.endpoint.event.model;

import course.poja.demo.model.Register;
import java.time.Duration;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@ToString
@Builder
public class RegisterCreated extends PojaEvent {
  private Register register;

  @Override
  public Duration maxConsumerDuration() {
    return Duration.ofSeconds(45);
  }

  @Override
  public Duration maxConsumerBackoffBetweenRetries() {
    return Duration.ofSeconds(60);
  }
}
