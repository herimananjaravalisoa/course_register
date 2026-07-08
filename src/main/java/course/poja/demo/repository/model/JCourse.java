package course.poja.demo.repository.model;

import jakarta.persistence.*;
import java.time.Instant;
import java.util.List;
import java.util.UUID;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "course_register")
@Builder
public class JCourse {
  @Id @GeneratedValue @UuidGenerator private UUID id;
  private String title;
  private Instant startDate;
  private Instant endDate;

  @OneToMany(mappedBy = "course")
  private List<JRegister> jRegister;
}
