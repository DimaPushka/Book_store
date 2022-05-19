package LearnUp.PushkaHW_34.dao.repository;

import LearnUp.PushkaHW_34.dao.entity.Role;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role getRoleByRole(String role);

    List<Role> findAll(Specification<Role> specification);

    Role getRoleById(Long id);

}
