package course.poja.demo.endpoint.rest.controller.health;

import course.poja.demo.service.HelloWorldService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class HelloWorldController {
  private final HelloWorldService helloWorldService;

  @GetMapping("/hello")
  public String helloWorld(@RequestParam String name) {
    return helloWorldService.uploadHelloWorldMessage(name);
  }
}
