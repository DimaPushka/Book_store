package LearnUp.PushkaHW_34.dao.repository;

import LearnUp.PushkaHW_34.dao.entity.Users;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {

    List<Users> findAll(Specification<Users> specification);

    Users getUsersById(Long id);

    Users findByLoginName(String loginName);
    
}
