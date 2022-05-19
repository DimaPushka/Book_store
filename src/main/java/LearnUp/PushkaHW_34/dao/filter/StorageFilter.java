package LearnUp.PushkaHW_34.dao.filter;


import LearnUp.PushkaHW_34.dao.entity.Book;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StorageFilter {
    
    private final Book book;
    
    private final Long theRestOfTheBooks;
    
}
