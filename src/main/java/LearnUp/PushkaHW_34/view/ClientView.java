package LearnUp.PushkaHW_34.view;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientView {

    private Long id;

    private UsersFromClientView user;

    private LocalDate dateOfBirth, dateRegistration;

    private List<OrdersFromBuyersView> orders;

    private String firstName, lastName;

}
