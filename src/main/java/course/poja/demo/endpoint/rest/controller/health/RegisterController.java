package course.poja.demo.endpoint.rest.controller.health;

import course.poja.demo.endpoint.rest.controller.health.dto.RegisterRequest;
import course.poja.demo.model.Register;
import course.poja.demo.service.RegisterService;
import jakarta.mail.internet.AddressException;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class RegisterController {
  private final RegisterService registerService;

  @GetMapping("/registers/{id}")
  public Register findById(@PathVariable UUID id) {
    return registerService.findById(id);
  }

  @GetMapping("/registers")
  public List<Register> registers() {
    return registerService.registers();
  }

  @PostMapping("registers/courses/{courseId}")
  public Register register(
      @PathVariable UUID courseId, @RequestBody RegisterRequest registerRequest)
      throws AddressException {
    return registerService.create(courseId, registerRequest);
  }
}
