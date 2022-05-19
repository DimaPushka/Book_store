package LearnUp.PushkaHW_34.view;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientFromUserView {

    private Long id;

    private LocalDate dateOfBirth, dateRegistration;

    private String firstName, lastName;
}
