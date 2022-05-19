package LearnUp.PushkaHW_34.view;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BooksNoAuthorView {
    
    private Long id;
    
    private String title;
    
    private LocalDate yearOfPublication;
    
    private Long numberOfPages;
    
    private Long price;
    
}
