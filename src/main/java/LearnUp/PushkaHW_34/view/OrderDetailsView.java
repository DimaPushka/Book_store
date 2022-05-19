package LearnUp.PushkaHW_34.view;

import LearnUp.PushkaHW_34.dao.entity.OrderDetails;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailsView {

    private BookViewForDetails book;

    private int countOfBook;

    private int priceOfBook;

    public OrderDetailsToView mapToView(OrderDetails orderDetails) {
        OrderDetailsToView view = new OrderDetailsToView();
        view.setBook(new BookViewForDetails(orderDetails.getBook().getName()));
        view.setCountOfBook(orderDetails.getCountOfBook());
        view.setPriceOfBook(orderDetails.getPriceOfBook());
        return view;
    }

    public List<OrderDetailsToView> mapToView(List<OrderDetails> orderDetailsList) {
        List<OrderDetailsToView> views = new ArrayList<>();
        orderDetailsList.forEach(orderDetails -> {
            OrderDetailsToView view = new OrderDetailsToView();
            view.setBook(new BookViewForDetails(orderDetails.getBook().getName()));
            view.setCountOfBook(orderDetails.getCountOfBook());
            view.setPriceOfBook(orderDetails.getPriceOfBook());
        });
        return views;
    }
}
