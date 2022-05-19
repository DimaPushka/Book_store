package LearnUp.PushkaHW_34.dao.filter;

import LearnUp.PushkaHW_34.dao.entity.Client;
import LearnUp.PushkaHW_34.dao.entity.OrderDetails;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderFilter {

    private final Long purchaseAmount;

    private final Client client;

    private final OrderDetails orderDetails;
}
