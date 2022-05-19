package LearnUp.PushkaHW_34.controller;

import LearnUp.PushkaHW_34.dao.entity.BookStorage;
import LearnUp.PushkaHW_34.dao.entity.Client;
import LearnUp.PushkaHW_34.dao.entity.Order;
import LearnUp.PushkaHW_34.dao.entity.OrderDetails;
import LearnUp.PushkaHW_34.dao.filter.OrderFilter;
import LearnUp.PushkaHW_34.service.BookStorageService;
import LearnUp.PushkaHW_34.service.ClientService;
import LearnUp.PushkaHW_34.service.OrderDetailsService;
import LearnUp.PushkaHW_34.service.OrdersService;
import LearnUp.PushkaHW_34.view.OrdersView;
import LearnUp.PushkaHW_34.view.mapper.OrdersViewMapper;
import lombok.AllArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("order")
public class OrderControllerRest {

    private final OrdersService ordersService;
    private final OrderDetailsService orderDetailsService;
    private final ClientService clientService;
    private final BookStorageService bookStorageService;
    private final OrdersViewMapper ordersViewMapper;

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @GetMapping
    public List<OrdersView> getOrders(
            @RequestParam(value = "purchaseAmount", required = false) Long purchaseAmount,
            @RequestParam(value = "client", required = false) Client client,
            @RequestParam(value = "orderDetails", required = false) List<OrderDetails> orderDetails
    ) {
        return ordersService.getAllOrders(new OrderFilter(purchaseAmount, client, orderDetails))
                .stream()
                .map(ordersViewMapper::mapOrdersToView)
                .collect(Collectors.toList());
    }

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @GetMapping("/{id}")
    public OrdersView getOrderById(@PathVariable("id") Long id) {
        return ordersViewMapper.mapOrdersToView(ordersService.getOrderById(id));
    }

    @Secured({"ROLE_USER"})
    @PostMapping
    public OrdersView createOrder(@RequestBody OrdersView ordersView) {
        if (ordersView.getId() != null) {
            throw new EntityExistsException(
                    String.format("Orders with id = %s already exist", ordersView.getId()));
        }
        Order order = ordersViewMapper.mapOrdersFromView(ordersView, clientService, orderDetailsService);
        Order createOrders = ordersService.createOrder(order);
        BookStorage mapOrdersForBookWarehouseFromView = ordersViewMapper.mapOrdersForBookStorageFromView(createOrders);
        BookStorage bookStorage = bookStorageService.getBookStorageById(mapOrdersForBookWarehouseFromView.getId());
        if (!bookStorage.getTheRestOfTheBooks().equals(mapOrdersForBookWarehouseFromView.getTheRestOfTheBooks())) {
            bookStorage.setTheRestOfTheBooks(mapOrdersForBookWarehouseFromView.getTheRestOfTheBooks());
        }
        bookStorageService.updateBookStorage(bookStorage);
        return ordersViewMapper.mapOrdersToView(ordersService.getOrderById(createOrders.getId()));
    }

    @Secured({"ROLE_USER"})
    @PutMapping("/{id}")
    public OrdersView updateOrder(@PathVariable("id") Long id,
                                  @RequestBody OrdersView authorsView) {
        if (authorsView.getId() == null) {
            throw new EntityNotFoundException("Try to found null entity");
        }
        if (!Objects.equals(id, authorsView.getId())) {
            throw new RuntimeException("Entity has bad id");
        }
        Order authors = ordersService.getOrderById(id);
        if (!authors.getPurchaseAmount().equals(authorsView.getPurchaseAmount())) {
            authors.setPurchaseAmount(authorsView.getPurchaseAmount());
        }
        Order updateOrder = ordersService.updateOrder(authors);
        return ordersViewMapper.mapOrdersToView(updateOrder);
    }

    @Secured({"ROLE_ADMIN"})
    @DeleteMapping("/{id}")
    public Boolean deleteOrder(@PathVariable("id") Long id) {
        return ordersService.deleteOrder(id);
    }
    
}
