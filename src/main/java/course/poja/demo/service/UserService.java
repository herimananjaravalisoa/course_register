package course.poja.demo.service;

import course.poja.demo.exception.NotFoundException;
import course.poja.demo.mapper.UserMapper;
import course.poja.demo.model.User;
import course.poja.demo.repository.UserRepository;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {
  private final UserRepository userRepository;
  private final UserMapper userMapper;

  public List<User> users() {
    return userMapper.toModel(userRepository.findAll());
  }

  public User findById(UUID id) {
    return userMapper.toModel(
        userRepository
            .findById(id)
            .orElseThrow(() -> new NotFoundException("User with id " + id + " not found")));
  }
}
