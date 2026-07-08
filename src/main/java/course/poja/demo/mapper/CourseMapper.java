package course.poja.demo.mapper;

import course.poja.demo.model.Course;
import course.poja.demo.repository.model.JCourse;
import java.util.List;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class CourseMapper {
  private final RegisterMapper registerMapper;

  public CourseMapper(@Lazy RegisterMapper registerMapper) {
    this.registerMapper = registerMapper;
  }

  public List<Course> toModel(List<JCourse> jCourses) {
    return jCourses.stream().map(this::toModel).toList();
  }

  public Course toModel(JCourse jCourse) {
    return Course.builder()
        .id(jCourse.getId())
        .title(jCourse.getTitle())
        .startDate(jCourse.getStartDate())
        .endDate(jCourse.getEndDate())
        .build();
  }

  public List<Course> toModelWithRegister(List<JCourse> jCourses) {
    return jCourses.stream().map(this::toModelWithRegister).toList();
  }

  public Course toModelWithRegister(JCourse jCourse) {
    return Course.builder()
        .id(jCourse.getId())
        .title(jCourse.getTitle())
        .startDate(jCourse.getStartDate())
        .endDate(jCourse.getEndDate())
        .registers(registerMapper.toModelWithoutCourse(jCourse.getJRegister()))
        .build();
  }

  public List<JCourse> toEntity(List<Course> courses) {
    return courses.stream().map(this::toEntity).toList();
  }

  public JCourse toEntity(Course course) {
    return JCourse.builder()
        .id(course.id())
        .title(course.title())
        .startDate(course.startDate())
        .endDate(course.endDate())
        .build();
  }
}
