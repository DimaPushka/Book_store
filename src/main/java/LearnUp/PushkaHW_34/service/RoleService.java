package LearnUp.PushkaHW_34.service;

import LearnUp.PushkaHW_34.dao.entity.Role;
import LearnUp.PushkaHW_34.dao.filter.RoleFilter;
import LearnUp.PushkaHW_34.dao.repository.RoleRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.OptimisticLockException;
import java.util.List;

import static LearnUp.PushkaHW_34.dao.specification.UsersRoleSpecification.byUsersRoleFilter;

@Slf4j
@AllArgsConstructor
@Service
public class RoleService {

    private final RoleRepository roleRepository;

    public List<Role> getAllRole(RoleFilter roleFilter) {
        Specification<Role> specification = Specification.where(byUsersRoleFilter(roleFilter));
        log.info("Request getAllRole: {}", specification);
        return roleRepository.findAll(specification);
    }

    public Role createRole(Role role) {
        log.info("CreateRole: {}", role.toString());
        return roleRepository.save(role);
    }

    public Role getRoleById(Long id) {
        log.info("Request getRoleById: {}", id);
        return roleRepository.getRoleById(id);
    }

    public Boolean deleteRole(Long id) {
        log.info("DeleteRole by id: {}", id);
        roleRepository.delete(roleRepository.getById(id));
        return true;
    }

    public Role updateRole(Role role) {
        try {
            log.info("UpdateRole: {}", role.toString());
            return roleRepository.save(role);
        } catch (OptimisticLockException e) {
            log.warn("Optimistic lock exception for Role {}", role.getId());
            throw e;
        }
    }

    public Role getRoleByRole(String role) {
        return roleRepository.getRoleByRole(role);
    }

}
