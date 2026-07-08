package course.poja.demo.repository.model;

import jakarta.persistence.*;
import java.util.UUID;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Table(name = "user_register")
@Entity
@Builder
public class JUser {
  @Id @GeneratedValue @UuidGenerator private UUID id;
  private String firstName;
  private String lastName;
  private String email;
}
