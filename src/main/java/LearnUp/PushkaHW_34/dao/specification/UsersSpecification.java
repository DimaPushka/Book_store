package LearnUp.PushkaHW_34.dao.specification;

import LearnUp.PushkaHW_34.dao.entity.Users;
import LearnUp.PushkaHW_34.dao.filter.UsersFilter;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;

public class UsersSpecification {
    
    public static Specification<Users> byUsersFilter(UsersFilter usersFilter) {
        
        return (root, q, cb) -> {
            
            Predicate predicate = cb.isNotNull(root.get("id"));
            
            if (usersFilter.getEmail() != null) {
                predicate = cb.and(predicate, cb.like(root.get("email"), "%" + usersFilter.getEmail() + "%"));
            }

            if (usersFilter.getLoginName() != null) {
                predicate = cb.and(predicate, cb.like(root.get("loginName"), "%" + usersFilter.getLoginName() + "%"));
            }

            if (usersFilter.getHashPassword() != null) {
                predicate = cb.and(predicate, cb.like(root.get("hashPassword"), "%" + usersFilter.getHashPassword() + "%"));
            }

            if (usersFilter.getUsersRoleFilter() != null) {
                predicate = cb.and(predicate, cb.like(root.get("role"), "%" + usersFilter.getUsersRoleFilter() + "%"));
            }

            if (usersFilter.getClient() != null) {
                predicate = cb.and(predicate, cb.like(root.get("buyer"), "%" + usersFilter.getClient() + "%"));
            }
            
            return predicate;
        };
    }
}
