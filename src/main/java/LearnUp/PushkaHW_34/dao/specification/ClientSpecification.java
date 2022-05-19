package LearnUp.PushkaHW_34.dao.specification;

import LearnUp.PushkaHW_34.dao.entity.Client;
import LearnUp.PushkaHW_34.dao.filter.ClientFilter;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;

public class ClientSpecification {
    
    public static Specification<Client> byClientFilter(ClientFilter clientFilter) {
        
        return (root, q, cb) -> {
            
            Predicate predicate = cb.isNotNull(root.get("id"));
            
            if (clientFilter.getUser() != null) {
                predicate = cb.and(predicate, cb.like(root.get("user"), "%" + clientFilter.getUser() + "%"));
            }
            
            if (clientFilter.getDateOfBirth() != null) {
                predicate = cb.and(predicate, cb.like(root.get("dateOfBirth"), "%" + clientFilter.getDateOfBirth() + "%"));
            }
    
            if (clientFilter.getOrders() != null) {
                predicate = cb.and(predicate, cb.like(root.get("orders"), "%" + clientFilter.getOrders() + "%"));
            }

            if (clientFilter.getFirstName() != null) {
                predicate = cb.and(predicate, cb.like(root.get("firstName"), "%" + clientFilter.getFirstName() + "%"));
            }

            if (clientFilter.getLastName() != null) {
                predicate = cb.and(predicate, cb.like(root.get("lastName"), "%" + clientFilter.getLastName() + "%"));
            }
            
            return predicate;
        };
    }
}
