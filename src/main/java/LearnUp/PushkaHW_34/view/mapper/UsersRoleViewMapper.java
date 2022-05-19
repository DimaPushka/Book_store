package LearnUp.PushkaHW_34.view.mapper;

import LearnUp.PushkaHW_34.dao.entity.UsersRole;
import LearnUp.PushkaHW_34.view.UsersRoleView;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class UsersRoleViewMapper {

    public UsersRoleView mapUsersRoleToView(UsersRole usersRole) {
        UsersRoleView roleView = new UsersRoleView();
        roleView.setId(usersRole.getId());
        roleView.setRole(usersRole.getRole());
        if (usersRole.getUsers() != null) {
            roleView.setUsers(
                    usersRole.getUsers()
                            .stream()
                            .map(users -> new UsersFromUserRoleView(
                                    users.getId(),
                                    users.getLoginName()
                            ))
                            .collect(Collectors.toList()));
        }
        return roleView;
    }

    public UsersRole mapUsersRoleFromView(UsersRoleView UserroleView) {
        UsersRole usersRole = new UsersRole();
        usersRole.setId(UserroleView.getId());
        usersRole.setRole(UserroleView.getRole());
        return usersRole;
    }
    
}
