package LearnUp.PushkaHW_34.dao.entity;

import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.Collection;
import java.util.Collections;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
public class Users implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn
    @Fetch(FetchMode.JOIN)
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private Role role;

    @Column(nullable = false, unique = true)
    private String loginName;

    @Column(nullable = false)
    private String hashPassword;

    @Email
    @Column(nullable = false, unique = true)
    private String email;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Client client;

    @Transient
    @Override
    public Collection<Role> getAuthorities() {
        return Collections.singleton(role);
    }

    @Transient
    @Override
    public String getPassword() {
        return hashPassword;
    }

    @Transient
    @Override
    public String getUsername() {
        return loginName;
    }

    @Transient
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Transient
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Transient
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Transient
    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", role=" + role +
                ", loginName='" + loginName + '\'' +
                ", hashPassword='" + hashPassword + '\'' +
                ", email='" + email + '\'' +
                ", client=" + client +
                '}';
    }
    
}
