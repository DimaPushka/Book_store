package LearnUp.PushkaHW_34.dao.specification;

import LearnUp.PushkaHW_34.dao.entity.Author;
import LearnUp.PushkaHW_34.dao.filter.AuthorFilter;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;

public class AuthorSpecification {
    
    public static Specification<Author> byAuthorFilter(AuthorFilter authorFilter) {
        
        return (root, q, cb) -> {
            
            Predicate predicate = cb.isNotNull(root.get("id"));
            
            if (authorFilter.getFullName() != null) {
                predicate = cb.and(predicate, cb.like(root.get("fullName"), "%" + authorFilter.getFullName() + "%"));
            }
            
            if (authorFilter.getBook() != null) {
                predicate = cb.and(predicate, cb.like(root.get("book"), "%" + authorFilter.getBook() + "%"));
            }
            
            return predicate;
        };
    }
}
