package course.poja.demo.service.event;

import course.poja.demo.endpoint.event.model.RegisterCreated;
import course.poja.demo.mail.Email;
import course.poja.demo.mail.Mailer;
import course.poja.demo.service.CourseService;
import course.poja.demo.service.UserService;
import jakarta.mail.internet.AddressException;
import jakarta.mail.internet.InternetAddress;
import java.util.List;
import java.util.UUID;
import java.util.function.Consumer;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class RegisterCreatedService implements Consumer<RegisterCreated> {
  private final UserService userService;
  private final CourseService courseService;
  private final Mailer mailer;

  @Override
  @SneakyThrows
  public void accept(RegisterCreated registerCreated) {
    sendCourseConfirmationEmailToUser(
        registerCreated.getRegister().user().id(), registerCreated.getRegister().course().id());
  }

  private void sendCourseConfirmationEmailToUser(UUID userId, UUID courseId)
      throws AddressException {
    var user = userService.findById(userId);
    var course = courseService.findById(courseId);
    var to = user.email();
    var subject = "Subscription confirmation(asynchronous): %s".formatted(course.title());
    var htmlBody =
        """
<html>
    <body>
        <p>Hello %s,</p>
        <p>Your subscription to the course <strong>%s</strong> has been successfully confirmed.</p>
        <p>We're excited to have you with us and hope you enjoy the course.</p>
        <p>Best regards,<br>
        The Team</p>
    </body>
</html>
"""
            .formatted(user.firstName(), course.title());
    var email =
        new Email(new InternetAddress(to), List.of(), List.of(), subject, htmlBody, List.of());
    mailer.accept(email);
  }
}
