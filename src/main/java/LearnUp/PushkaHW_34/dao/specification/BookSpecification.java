package LearnUp.PushkaHW_34.dao.specification;

import LearnUp.PushkaHW_34.dao.entity.Book;
import LearnUp.PushkaHW_34.dao.filter.BookFilter;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;

public class BookSpecification {
    
    public static Specification<Book> byBookFilter(BookFilter bookFilter) {
        
        return (root, q, cb) -> {
            
            Predicate predicate = cb.isNotNull(root.get("id"));
    
            if (bookFilter.getTitle() != null) {
                predicate = cb.and(predicate, cb.like(root.get("title"), "%" + bookFilter.getTitle() + "%"));
            }
    
            if (bookFilter.getAuthor() != null) {
                predicate = cb.and(predicate, cb.like(root.get("author"), "%" + bookFilter.getAuthor() + "%"));
            }
    
            if (bookFilter.getYearOfPublication() != null) {
                predicate = cb.and(predicate, cb.like(root.get("yearOfPublication"), "%" + bookFilter.getYearOfPublication() + "%"));
            }
    
            if (bookFilter.getNumberOfPages() != null) {
                predicate = cb.and(predicate, cb.like(root.get("numberOfPages"), "%" + bookFilter.getNumberOfPages() + "%"));
            }
            
            if (bookFilter.getPrice() != null) {
                predicate = cb.and(predicate, cb.like(root.get("price"), "%" + bookFilter.getPrice() + "%"));
            }

            if (bookFilter.getBookStorage() != null) {
                predicate = cb.and(predicate, cb.like(root.get("bookWarehouse"), "%" + bookFilter.getBookStorage() + "%"));
            }
            
            return predicate;
        };
    }
}
