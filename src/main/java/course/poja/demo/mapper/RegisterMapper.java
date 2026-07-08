package course.poja.demo.mapper;

import course.poja.demo.endpoint.rest.controller.health.dto.RegisterRequest;
import course.poja.demo.model.Register;
import course.poja.demo.model.RegisterStatus;
import course.poja.demo.repository.model.JRegister;
import course.poja.demo.service.CourseService;
import course.poja.demo.service.UserService;
import java.time.Instant;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class RegisterMapper {
  private final UserService userService;
  private final CourseService courseService;
  private final UserMapper userMapper;
  private final CourseMapper courseMapper;

  public List<Register> toModel(List<JRegister> jRegisters) {
    return jRegisters.stream().map(this::toModel).toList();
  }

  public Register toModel(JRegister jRegister) {
    return Register.builder()
        .id(jRegister.getId())
        .createdAt(jRegister.getCreatedAt())
        .registerStatus(jRegister.getRegisterStatus())
        .user(userMapper.toModel(jRegister.getUser()))
        .course(courseMapper.toModel(jRegister.getCourse()))
        .build();
  }

  public List<Register> toModelWithoutCourse(List<JRegister> jRegisters) {
    return jRegisters.stream().map(this::toModelWithoutCourse).toList();
  }

  public Register toModelWithoutCourse(JRegister jRegister) {
    return Register.builder()
        .id(jRegister.getId())
        .createdAt(jRegister.getCreatedAt())
        .registerStatus(jRegister.getRegisterStatus())
        .user(userMapper.toModel(jRegister.getUser()))
        .build();
  }

  public List<JRegister> toEntity(List<Register> registers) {
    return registers.stream().map(this::toEntity).toList();
  }

  public JRegister toEntity(Register register) {
    return JRegister.builder()
        .id(register.id())
        .createdAt(register.createdAt())
        .registerStatus(register.registerStatus())
        .user(userMapper.toEntity(register.user()))
        .course(courseMapper.toEntity(register.course()))
        .build();
  }

  public JRegister toEntity(UUID id, UUID courseId, RegisterRequest registerRequest) {
    var user = userService.findById(registerRequest.userId());
    var course = courseService.findById(courseId);
    return JRegister.builder()
        .id(id)
        .createdAt(Instant.now())
        .registerStatus(RegisterStatus.PENDING)
        .user(userMapper.toEntity(user))
        .course(courseMapper.toEntity(course))
        .build();
  }
}
