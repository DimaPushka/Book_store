package LearnUp.PushkaHW_34.dao.repository;

import LearnUp.PushkaHW_34.dao.entity.UsersRole;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersRoleRepository extends JpaRepository<UsersRole, Long> {

    UsersRole getRoleByRole(String role);

    List<UsersRole> findAll(Specification<UsersRole> specification);

    UsersRole getUsersRoleById(Long id);

    UsersRole findByUserRole(String roles);
}
