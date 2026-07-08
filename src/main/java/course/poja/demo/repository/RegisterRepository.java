package course.poja.demo.repository;

import course.poja.demo.repository.model.JRegister;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegisterRepository extends JpaRepository<JRegister, UUID> {}
