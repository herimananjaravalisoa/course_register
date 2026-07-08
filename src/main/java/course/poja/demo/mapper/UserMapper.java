package course.poja.demo.mapper;

import course.poja.demo.model.User;
import course.poja.demo.repository.model.JUser;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
  public List<User> toModel(List<JUser> users) {
    return users.stream().map(this::toModel).toList();
  }

  public User toModel(JUser jUser) {
    return User.builder()
        .id(jUser.getId())
        .firstName(jUser.getFirstName())
        .lastName(jUser.getLastName())
        .email(jUser.getEmail())
        .build();
  }

  public List<JUser> toEntity(List<User> users) {
    return users.stream().map(this::toEntity).toList();
  }

  public JUser toEntity(User user) {
    return JUser.builder()
        .id(user.id())
        .firstName(user.firstName())
        .lastName(user.lastName())
        .email(user.email())
        .build();
  }
}
