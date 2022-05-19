package LearnUp.PushkaHW_34.dao.repository;

import LearnUp.PushkaHW_34.dao.entity.OrderDetails;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Long> {
    
    List<OrderDetails> findAll(Specification<OrderDetails> specification);

    OrderDetails getOrderDetailsById(Long id);
}
