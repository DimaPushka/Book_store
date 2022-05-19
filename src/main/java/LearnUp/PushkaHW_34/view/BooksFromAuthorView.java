package LearnUp.PushkaHW_34.view;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BooksFromAuthorView {

    private Long id, numberOfPages, price;

    private String title;

    private LocalDate yearOfPublication;

    public BooksFromAuthorView(Long id, String title, LocalDate yearOfPublication, Long numberOfPages, Long price) {
        this.id = id;
        this.numberOfPages = numberOfPages;
        this.price = price;
        this.title = title;
        this.yearOfPublication = yearOfPublication;
    }
}
