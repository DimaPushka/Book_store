package LearnUp.PushkaHW_34.dao.filter;

import LearnUp.PushkaHW_34.dao.entity.Order;
import LearnUp.PushkaHW_34.dao.entity.Users;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
public class ClientFilter {
    
    private final Users user;
    
    private final LocalDate dateOfBirth, dateRegistration;
    
    private final List<Order> orders;

    private final String firstName, lastName;
}
