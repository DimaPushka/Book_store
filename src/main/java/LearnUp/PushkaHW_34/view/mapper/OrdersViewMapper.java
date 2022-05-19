package LearnUp.PushkaHW_34.view.mapper;

import LearnUp.PushkaHW_34.dao.entity.Order;
import LearnUp.PushkaHW_34.service.ClientService;
import LearnUp.PushkaHW_34.service.OrderDetailsService;
import LearnUp.PushkaHW_34.view.*;
import org.springframework.stereotype.Component;

@Component
public class OrdersViewMapper {

    public OrdersView mapOrdersToView(Order order) {
        OrdersView ordersView = new OrdersView();
        ordersView.setId(order.getId());
        ordersView.setPurchaseAmount(order.getPurchaseAmount());
        ordersView.setBuyer(
                new ClientFromOrdersView(
                        order.getClient().getId(),
                        order.getClient().getDateOfBirth(),
                        order.getClient().getDateRegistration(),
                        order.getClient().getFirstName(),
                        order.getClient().getLastName()));
        ordersView.setOrderDetails(
                new OrderDetailsFromOrderView(
                        order.getOrderDetails().getId(),
                        order.getOrderDetails().getQuantity(),
                        order.getOrderDetails().getPrice(),
                        new BooksView(
                                order.getOrderDetails().getBook().getId(),
                                order.getOrderDetails().getBook().getNumberOfPages(),
                                order.getOrderDetails().getBook().getPrice(),
                                order.getOrderDetails().getBook().getTitle(),
                                new AuthorsFromBookView(
                                        order.getOrderDetails().getBook().getAuthor().getId(),
                                        order.getOrderDetails().getBook().getAuthor().getFullName()),
                                order.getOrderDetails().getBook().getYearOfPublication(),
                                new BookStorageViewMapper(
                                        order.getOrderDetails().getBook().getBookStorage().getId(),
                                        order.getOrderDetails().getBook().getBookStorage().getTheRestOfTheBooks()))

                ));
        return ordersView;
    }

    public Order mapOrdersFromView(OrdersView ordersView, ClientService buyersService, OrderDetailsService orderDetailsService) {
        Order order = new Order();
        order.setId(ordersView.getId());
        order.setPurchaseAmount(ordersView.getPurchaseAmount());
        order.setClient(buyersService.getClientById(ordersView.getBuyer().getId()));
        order.setOrderDetails(orderDetailsService.getOrderDetailsById(ordersView.getOrderDetails().getId()));
        return order;
    }
    
}
