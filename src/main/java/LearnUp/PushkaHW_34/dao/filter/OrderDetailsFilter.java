package LearnUp.PushkaHW_34.dao.filter;

import LearnUp.PushkaHW_34.dao.entity.Book;
import LearnUp.PushkaHW_34.dao.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderDetailsFilter {

    private final Order order;

    private final Book book;

    private final Long quantity, price;
}
