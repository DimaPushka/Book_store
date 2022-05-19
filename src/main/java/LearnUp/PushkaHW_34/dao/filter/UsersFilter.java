package LearnUp.PushkaHW_34.dao.filter;

import LearnUp.PushkaHW_34.dao.entity.Client;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UsersFilter {

    private final UsersRoleFilter usersRoleFilter;

    private final String loginName, email, hashPassword;

    private final Client client;
}
