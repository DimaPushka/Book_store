package LearnUp.PushkaHW_34.service;

import LearnUp.PushkaHW_34.dao.entity.Order;
import LearnUp.PushkaHW_34.dao.filter.OrderFilter;
import LearnUp.PushkaHW_34.dao.repository.OrderRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.OptimisticLockException;
import java.util.List;

import static LearnUp.PushkaHW_34.dao.specification.OrderSpecification.byOrdersFilter;


@Slf4j
@AllArgsConstructor
@Service
public class OrdersService {

    private final OrderRepository orderRepository;
    
    public List<Order> getAllOrders(OrderFilter orderFilter) {
        Specification<Order> specification = Specification.where(byOrdersFilter(orderFilter));
        log.info("Request getAllOrders: {}", specification);
        return orderRepository.findAll(specification);
    }
    
    public Order createOrder(Order order) {
        log.info("CreateOrder: {}", order.toString());
        return orderRepository.save(order);
    }
    
    public Order getOrderById(Long id) {
        log.info("Request getOrderById: {}", id);
        return orderRepository.getOrderById(id);
    }
    
    public Boolean deleteOrder(Long id) {
        log.info("DeleteOrder by id: {}", id);
        orderRepository.delete(orderRepository.getById(id));
        return true;
    }
    
    public Order updateOrder(Order order) {
        try {
            log.info("UpdateOrder: {}", order.toString());
            return orderRepository.save(order);
        } catch (OptimisticLockException e) {
            log.warn("Optimistic lock exception for Order id {}", order.getId());
            throw e;
        }
    }
    
}
