package LearnUp.PushkaHW_34.controller;

import LearnUp.PushkaHW_34.dao.entity.Client;
import LearnUp.PushkaHW_34.dao.entity.Users;
import LearnUp.PushkaHW_34.dao.filter.UsersFilter;
import LearnUp.PushkaHW_34.service.UsersService;
import LearnUp.PushkaHW_34.view.UsersView;
import LearnUp.PushkaHW_34.view.mapper.UsersViewMapper;
import lombok.AllArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.management.relation.Role;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("users")
public class UsersControllerRest {

    private final UsersService usersService;
    private final RoleService roleService;
    private final UsersViewMapper usersViewMapper;

    @Secured({"ROLE_ADMIN"})
    @GetMapping
    public List<UsersView> getUsers(
            @RequestParam(value = "role", required = false) Role role,
            @RequestParam(value = "loginName", required = false) String loginName,
            @RequestParam(value = "email", required = false) String email,
            @RequestParam(value = "hashPassword", required = false) String hashPassword,
            @RequestParam(value = "buyer", required = false) Client client
    ) {
        return usersService.getAllUsers(new UsersFilter(role, loginName, email, hashPassword, client))
                .stream()
                .map(usersViewMapper::mapUsersToView)
                .collect(Collectors.toList());
    }

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @GetMapping("/{id}")
    public UsersView getUserById(@PathVariable("id") Long id) {
        return usersViewMapper.mapUsersToView(usersService.getUserById(id));
    }


    @PostMapping
    public Boolean createUser(@RequestBody UsersView usersView) {
        if (usersView.getId() != null) {
            throw new EntityExistsException(
                    String.format("User with id = %s already exist", usersView.getId())
            );
        }
        Users users = usersViewMapper.mapUsersFromView(usersView, roleService);
        usersService.createUser(users);
        return true;
    }

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @PutMapping("/{id}")
    public UsersView updateUser(@PathVariable("id") Long id,
                                @RequestBody UsersView authorsView) {
        if (authorsView.getId() == null) {
            throw new EntityNotFoundException("Try to found null entity");
        }
        if (!Objects.equals(id, authorsView.getId())) {
            throw new RuntimeException("Entity has bad id");
        }
        Users users = usersService.getUserById(id);
        if (!users.getEmail().equals(authorsView.getEmail())) {
            users.setEmail(authorsView.getEmail());
        }
        if (!users.getLoginName().equals(authorsView.getLoginName())) {
            users.setLoginName(authorsView.getLoginName());
        }
        if (!users.getHashPassword().equals(authorsView.getHashPassword())) {
            users.setHashPassword(authorsView.getHashPassword());
        }
        Users updateUser = usersService.updateUser(users);
        return usersViewMapper.mapUsersToView(updateUser);
    }

    @Secured({"ROLE_USER, ROLE_ADMIN"})
    @DeleteMapping("/{id}")
    public Boolean deleteAuthor(@PathVariable("id") Long id) {
        return usersService.deleteUser(id);
    }
    
}
