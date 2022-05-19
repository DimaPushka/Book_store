package LearnUp.PushkaHW_34.view.mapper;

import LearnUp.PushkaHW_34.dao.entity.Role;
import LearnUp.PushkaHW_34.view.RoleView;
import org.springframework.stereotype.Component;

@Component
public class RoleViewMapper {

    public RoleView mapUsersRoleToView(Role role) {
        RoleView roleView = new RoleView();
        roleView.setId(role.getId());
        roleView.setRole(role.getRole());
        return roleView;
    }

    public Role mapUsersRoleFromView(RoleView roleView) {
        Role role = new Role();
        role.setId(roleView.getId());
        role.setRole(roleView.getRole());
        return role;
    }

}
