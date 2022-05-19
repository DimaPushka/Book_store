package LearnUp.PushkaHW_34.view;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class BookViewForPurchase {

    private String bookName;
    private int countOfBook;
}
