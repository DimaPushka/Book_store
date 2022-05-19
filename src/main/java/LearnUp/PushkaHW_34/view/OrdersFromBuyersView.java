package LearnUp.PushkaHW_34.view;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrdersFromBuyersView {

    private Long id;

    private Long purchaseAmount;

    private OrderDetailsFromOrderView orderDetails;
}
