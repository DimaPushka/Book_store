package LearnUp.PushkaHW_34.view;

import LearnUp.PushkaHW_34.dao.entity.Users;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientNoOrdersView {

    private Long id;

    private Users user;

    private LocalDate dateOfBirth;

}
