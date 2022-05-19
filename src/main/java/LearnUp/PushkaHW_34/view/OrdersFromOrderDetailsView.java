package LearnUp.PushkaHW_34.view;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrdersFromOrderDetailsView {

    private Long id, purchaseAmount;

    private ClientFromOrdersView buyer;
}
