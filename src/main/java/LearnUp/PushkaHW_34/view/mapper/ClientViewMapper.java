package LearnUp.PushkaHW_34.view.mapper;

import LearnUp.PushkaHW_34.dao.entity.Client;
import LearnUp.PushkaHW_34.service.UsersService;
import LearnUp.PushkaHW_34.view.*;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class ClientViewMapper {

    public ClientView mapClientToView(Client client) {
        ClientView clientView = new ClientView();
        clientView.setId(client.getId());
        clientView.setDateRegistration(client.getDateRegistration());
        clientView.setUser(
                new UsersFromClientView(
                        client.getUser().getId(),
                        new UsersRoleViewMapper(
                                client.getUser().getUsersRole().getId(),
                                client.getUser().getUsersRole().getRole()
                        ),
                        client.getUser().getLoginName()
                ));
        clientView.setDateOfBirth(client.getDateOfBirth());
        clientView.setFirstName(client.getFirstName());
        clientView.setLastName(client.getLastName());
        clientView.setOrders(
                client.getOrder()
                        .stream()
                        .map(orders -> new OrdersFromBuyersView(
                                orders.getId(),
                                orders.getPurchaseAmount(),
                                new OrderDetailsFromOrderView(
                                        orders.getOrderDetails().getId(),
                                        orders.getOrderDetails().getQuantity(),
                                        orders.getOrderDetails().getPrice(),
                                        new BooksView(
                                                orders.getOrderDetails().getBook().getId(),
                                                orders.getOrderDetails().getBook().getPrice(),
                                                orders.getOrderDetails().getBook().getNumberOfPages(),
                                                orders.getOrderDetails().getBook().getTitle(),
                                                new AuthorsFromBookView(
                                                        orders.getOrderDetails().getBook().getAuthor().getId(),
                                                        orders.getOrderDetails().getBook().getAuthor().getFullName()),
                                                orders.getOrderDetails().getBook().getYearOfPublication(),
                                                new BookStorageViewMapper(
                                                        orders.getOrderDetails().getBook().getBookStorage().getId(),
                                                        orders.getOrderDetails().getBook().getBookStorage().getTheRestOfTheBooks()
                                                )))))
                        .collect(Collectors.toList()));

        return clientView;
    }

    public Client mapBuyersFromView(ClientView clientView, UsersService usersService) {
        Client client = new Client();
        client.setId(clientView.getId());
        client.setUser(usersService.getUserById(clientView.getId()));
        client.setDateOfBirth(clientView.getDateOfBirth());
        return client;
    }
    
}
