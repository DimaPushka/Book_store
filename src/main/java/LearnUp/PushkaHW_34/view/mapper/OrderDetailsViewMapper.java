package LearnUp.PushkaHW_34.view.mapper;

import LearnUp.PushkaHW_34.dao.entity.OrderDetails;
import LearnUp.PushkaHW_34.service.BookService;
import LearnUp.PushkaHW_34.view.*;
import org.springframework.stereotype.Component;

@Component
public class OrderDetailsViewMapper {

    public OrderDetailsView mapOrderDetailsToView(OrderDetails orderDetails) {
        OrderDetailsView orderDetailsView = new OrderDetailsView();
        orderDetailsView.setId(orderDetails.getId());
        orderDetailsView.setQuantity(orderDetails.getQuantity());
        orderDetailsView.setPrice(orderDetails.getPrice());
        orderDetailsView.setOrder(
                new OrdersFromOrderDetailsView(
                        orderDetails.getOrder().getId(),
                        orderDetails.getOrder().getPurchaseAmount(),
                        new ClientFromOrdersView(
                                orderDetails.getOrder().getClient().getId(),
                                orderDetails.getOrder().getClient().getDateOfBirth(),
                                orderDetails.getOrder().getClient().getDateRegistration(),
                                orderDetails.getOrder().getClient().getFirstName(),
                                orderDetails.getOrder().getClient().getLastName()
                        )));
        orderDetailsView.setBook(
                new BooksView(
                        orderDetails.getBook().getId(),
                        orderDetails.getBook().getPrice(),
                        orderDetails.getBook().getNumberOfPages(),
                        orderDetails.getBook().getTitle(),
                        new AuthorsFromBookView(
                                orderDetails.getBook().getAuthor().getId(),
                                orderDetails.getBook().getAuthor().getFullName()),
                        orderDetails.getBook().getYearOfPublication(),
                        new BookStorageViewMapper(
                                orderDetails.getBook().getBookStorage().getId(),
                                orderDetails.getBook().getBookStorage().getTheRestOfTheBooks()

                        )));
        orderDetailsView.setPrice(orderDetails.getPrice());
        orderDetailsView.setQuantity(orderDetails.getQuantity());
        return orderDetailsView;
    }

    public OrderDetails mapOrderDetailsFromView(OrderDetailsView orderDetailsView, BookService booksService) {
        OrderDetails orderDetails = new OrderDetails();
        orderDetails.setId(orderDetailsView.getId());
        orderDetails.setBook(booksService.getBookByTitle(orderDetails.getBook().getTitle()));
        orderDetails.setOrder(ordersService.getOrderById(orderDetailsView.getId()));
        orderDetails.setPrice(orderDetailsView.getPrice());
        orderDetails.setQuantity(orderDetailsView.getQuantity());
        return orderDetails;
    }
    
}
