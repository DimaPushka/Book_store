package LearnUp.PushkaHW_34.dao.specification;

import LearnUp.PushkaHW_34.dao.entity.BookStorage;
import LearnUp.PushkaHW_34.dao.filter.StorageFilter;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;

public class BookStorageSpecification {
    
    public static Specification<BookStorage> byBookStorageFilter(StorageFilter storageFilter) {
        
        return (root, q, cb) -> {
            
            Predicate predicate = cb.isNotNull(root.get("id"));

            if (storageFilter.getTheRestOfTheBooks() != null) {
                predicate = cb.and(predicate, cb.like(root.get("theRestOfTheBooks"), "%" + storageFilter.getTheRestOfTheBooks() + "%"));
            }
            
            return predicate;
        };
    }
}
