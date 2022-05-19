package LearnUp.PushkaHW_34.dao.specification;

import LearnUp.PushkaHW_34.dao.entity.UsersRole;
import LearnUp.PushkaHW_34.dao.filter.UsersRoleFilter;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;

public class UsersRoleSpecification {
    
    public static Specification<UsersRole> byUsersRoleFilter(UsersRoleFilter usersRoleFilter) {
        
        return (root, q, cb) -> {
            
            Predicate predicate = cb.isNotNull(root.get("id"));
            
            if (usersRoleFilter.getRole() != null) {
                predicate = cb.and(predicate, cb.like(root.get("role"), "%" + usersRoleFilter.getRole() + "%"));
            }

            if (usersRoleFilter.getUsers() != null) {
                predicate = cb.and(predicate, cb.like(root.get("users"), "%" + usersRoleFilter.getUsers() + "%"));
            }
            
            return predicate;
        };
    }
}
