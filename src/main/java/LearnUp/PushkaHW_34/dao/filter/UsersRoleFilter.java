package LearnUp.PushkaHW_34.dao.filter;

import LearnUp.PushkaHW_34.dao.entity.Users;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class UsersRoleFilter {
    
    private String role;

    private final List<Users> users;
}
