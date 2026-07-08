package course.poja.demo.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.time.Instant;
import java.util.List;
import java.util.UUID;
import lombok.Builder;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public record Course(
    UUID id, String title, Instant startDate, Instant endDate, List<Register> registers) {}
