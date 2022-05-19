package LearnUp.PushkaHW_34.view;

import LearnUp.PushkaHW_34.dao.entity.OrderDetails;
import LearnUp.PushkaHW_34.service.BookService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailsFromOrderView {

    private Long id;

    private BooksOrderView booksOrder;

    private BookViewForDetails book;

    private int countOfBook;

    public OrderDetails mapFromView(OrderDetailsFromView view, BooksOrderService orderService,
                                    BookService bookservice) {
        OrderDetails orderDetails = new OrderDetails();
        orderDetails.setBooksOrder(orderService.getBooksOrderById(view.getBooksOrder().getId()));
        orderDetails.setBook(bookservice.getBookByName(view.getBook().getName()));
        orderDetails.setCountOfBook(view.getCountOfBook());
        orderDetails.setPriceOfBook(bookservice.getBookByName(view.getBook().getName())
                .getPrice() * view.getCountOfBook());
        return orderDetails;
    }
}
