package LearnUp.PushkaHW_34.view;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
public class PurchaseFromView {

    private Long clientId;
    private List<BookViewForPurchase> books;
}
