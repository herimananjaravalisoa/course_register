package course.poja.demo.endpoint.rest.controller.health.dto;

import java.util.UUID;
import lombok.Builder;

@Builder
public record RegisterRequest(UUID userId) {}
