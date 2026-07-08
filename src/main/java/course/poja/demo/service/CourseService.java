package course.poja.demo.service;

import course.poja.demo.exception.NotFoundException;
import course.poja.demo.mapper.CourseMapper;
import course.poja.demo.model.Course;
import course.poja.demo.repository.CourseRepository;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CourseService {
  private final CourseMapper courseMapper;
  private final CourseRepository courseRepository;

  public List<Course> courses() {
    return courseMapper.toModel(courseRepository.findAll());
  }

  public Course findById(UUID id) {
    return courseMapper.toModel(
        courseRepository
            .findById(id)
            .orElseThrow(() -> new NotFoundException("Course with id " + id + " not found")));
  }

  public List<Course> courseWithRegisters() {
    return courseMapper.toModelWithRegister(courseRepository.findAll());
  }

  public Course findCourseById(UUID id) {
    return courseMapper.toModelWithRegister(
        courseRepository
            .findById(id)
            .orElseThrow(() -> new NotFoundException("Course with id " + id + " not found")));
  }
}
