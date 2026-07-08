package course.poja.demo.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.time.Instant;
import java.util.UUID;
import lombok.Builder;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public record Register(
    UUID id, Instant createdAt, RegisterStatus registerStatus, Course course, User user) {}
