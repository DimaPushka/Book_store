package LearnUp.PushkaHW_34.dao.filter;


import LearnUp.PushkaHW_34.dao.entity.Author;
import LearnUp.PushkaHW_34.dao.entity.BookStorage;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class BookFilter {

    private final String title;

    private final Author author;

    private final LocalDate yearOfPublication;

    private final Long numberOfPages, price;

    private final BookStorage bookStorage;
}
