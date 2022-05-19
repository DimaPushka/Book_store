package LearnUp.PushkaHW_34.view.mapper;

import LearnUp.PushkaHW_34.dao.entity.Users;
import LearnUp.PushkaHW_34.view.ClientFromUserView;
import LearnUp.PushkaHW_34.view.UsersRoleFromUserView;
import LearnUp.PushkaHW_34.view.UsersView;
import org.springframework.stereotype.Component;

@Component
public class UsersViewMapper {

    public UsersView mapUsersToView(Users users) {
        UsersView usersView = new UsersView();
        usersView.setId(users.getId());
        usersView.setEmail(users.getEmail());
        usersView.setLoginName(users.getLoginName());
        usersView.setHashPassword(users.getHashPassword());
        usersView.setRole(
                new UsersRoleFromUserView(
                        usersView.getRole().getId(),
                        usersView.getRole().getRole()
                )
        );
        usersView.setClient(
                new ClientFromUserView(
                        usersView.getClient().getId(),
                        usersView.getClient().getDateOfBirth(),
                        usersView.getClient().getDateRegistration(),
                        usersView.getClient().getFirstName(),
                        usersView.getClient().getLastName()
                )
        );
        return usersView;
    }

    public Users mapUsersFromView(UsersView usersView, RoleService roleService) {
        Users users = new Users();
        users.setId(usersView.getId());
        users.setEmail(usersView.getEmail());
        users.setHashPassword(usersView.getHashPassword());
        users.setLoginName(usersView.getLoginName());
        users.setUsersRole(roleService.getUsersRoleById(usersView.getRole().getRole()));
        return users;
    }
    
}
