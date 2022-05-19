package LearnUp.PushkaHW_34.dao.specification;

import LearnUp.PushkaHW_34.dao.entity.OrderDetails;
import LearnUp.PushkaHW_34.dao.filter.OrderDetailsFilter;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;

public class OrderDetailsSpecification {
    
    public static Specification<OrderDetails> byOrderDetailsFilter(OrderDetailsFilter orderDetailsFilter) {
        
        return (root, q, cb) -> {
            
            Predicate predicate = cb.isNotNull(root.get("id"));
            
            if (orderDetailsFilter.getOrder() != null) {
                predicate = cb.and(predicate, cb.like(root.get("order"), "%" + orderDetailsFilter.getOrder() + "%"));
            }
            
            if (orderDetailsFilter.getBook() != null) {
                predicate = cb.and(predicate, cb.like(root.get("book"), "%" + orderDetailsFilter.getBook() + "%"));
            }
    
            if (orderDetailsFilter.getQuantity() != null) {
                predicate = cb.and(predicate, cb.like(root.get("quantity"), "%" + orderDetailsFilter.getQuantity() + "%"));
            }
    
            if (orderDetailsFilter.getPrice() != null) {
                predicate = cb.and(predicate, cb.like(root.get("price"), "%" + orderDetailsFilter.getPrice() + "%"));
            }
            
            return predicate;
        };
    }
}
