package LearnUp.PushkaHW_34.view;

import LearnUp.PushkaHW_34.dao.entity.UsersRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsersView {
    
    private Long id;
    
    private UsersRole role;
    
    private String loginName;
    
    private String email;
    
    private String hashPassword;
    
    private String firstName;
    
    private String lastName;
    
    private LocalDate dateRegistration;
    
}
