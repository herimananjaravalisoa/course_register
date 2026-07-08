package course.poja.demo.endpoint.rest.controller.health;

import course.poja.demo.model.Course;
import course.poja.demo.service.CourseService;
import course.poja.demo.service.RegisterService;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class CourseController {
  private final CourseService courseService;
  private final RegisterService registerService;

  @GetMapping("/courses")
  public List<Course> courses() {
    return courseService.courseWithRegisters();
  }

  @GetMapping("/courses/{id}")
  public Course findById(@PathVariable UUID id) {
    return courseService.findCourseById(id);
  }
}
