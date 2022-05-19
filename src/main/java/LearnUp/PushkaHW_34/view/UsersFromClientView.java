package LearnUp.PushkaHW_34.view;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsersFromClientView {

    private Long id;

    private UsersRoleFromUserView role;

    private String loginName;
}
