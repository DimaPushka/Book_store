package LearnUp.PushkaHW_34.dao.repository;

import LearnUp.PushkaHW_34.dao.entity.Client;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    
    List<Client> findAll(Specification<Client> specification);

    Client getClientById(Long id);
}
