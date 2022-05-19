package LearnUp.PushkaHW_34.dao.specification;

import LearnUp.PushkaHW_34.dao.entity.Order;
import LearnUp.PushkaHW_34.dao.filter.OrderFilter;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;

public class OrderSpecification {
    
    public static Specification<Order> byOrdersFilter(OrderFilter orderFilter) {
        
        return (root, q, cb) -> {
            
            Predicate predicate = cb.isNotNull(root.get("id"));
            
            if (orderFilter.getClient() != null) {
                predicate = cb.and(predicate, cb.like(root.get("buyer"), "%" + orderFilter.getClient() + "%"));
            }
            
            if (orderFilter.getPurchaseAmount() != null) {
                predicate = cb.and(predicate, cb.like(root.get("purchaseAmount"), "%" + orderFilter.getPurchaseAmount() + "%"));
            }

            if (orderFilter.getOrderDetails() != null) {
                predicate = cb.and(predicate, cb.like(root.get("orderDetails"), "%" + orderFilter.getOrderDetails() + "%"));
            }
            
            return predicate;
        };
    }
}
