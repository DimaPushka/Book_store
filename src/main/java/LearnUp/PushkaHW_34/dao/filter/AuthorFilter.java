package LearnUp.PushkaHW_34.dao.filter;


import LearnUp.PushkaHW_34.dao.entity.Book;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class AuthorFilter {
    
    private final String fullName;
    
    private final List<Book> book;
}
