package course.poja.demo.service;

import course.poja.demo.endpoint.event.EventProducer;
import course.poja.demo.endpoint.event.model.RegisterCreated;
import course.poja.demo.endpoint.rest.controller.health.dto.RegisterRequest;
import course.poja.demo.exception.NotFoundException;
import course.poja.demo.mail.Email;
import course.poja.demo.mail.Mailer;
import course.poja.demo.mapper.RegisterMapper;
import course.poja.demo.model.Register;
import course.poja.demo.repository.RegisterRepository;
import jakarta.mail.internet.AddressException;
import jakarta.mail.internet.InternetAddress;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegisterService {
  private final RegisterMapper registerMapper;
  private final RegisterRepository registerRepository;
  private final UserService userService;
  private final EventProducer<RegisterCreated> eventProducer;
  private final Mailer mailer;

  public Register create(UUID courseId, RegisterRequest registerRequest) {
    var registerId = UUID.randomUUID();
    var asEntity = registerMapper.toEntity(registerId, courseId, registerRequest);
    var saved = registerMapper.toModel(registerRepository.save(asEntity));
    eventProducer.accept(List.of(new RegisterCreated(saved)));
    return saved;
  }

  private void sendEmailToUserId(UUID id) throws AddressException {
    var user = userService.findById(id);
    var to = user.email();
    var subject = "Subscription confirmation";
    var htmlBody =
        """
        <html>
            <body>
                <p>Hello %s,</p>
                <p>Your subscription has been successfully confirmed.</p>
                <p>Thank you for choosing our service.</p>
                <p>Best regards,<br>
                The Team</p>
            </body>
        </html>
        """
            .formatted(user.firstName());
    var email =
        new Email(new InternetAddress(to), List.of(), List.of(), subject, htmlBody, List.of());
    mailer.accept(email);
  }

  public List<Register> registers() {
    return registerMapper.toModel(registerRepository.findAll());
  }

  public Register findById(UUID id) {
    return registerMapper.toModel(
        registerRepository
            .findById(id)
            .orElseThrow(() -> new NotFoundException("Register with id " + id + " not found")));
  }
}
