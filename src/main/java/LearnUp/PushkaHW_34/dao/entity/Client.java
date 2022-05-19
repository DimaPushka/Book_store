package LearnUp.PushkaHW_34.dao.entity;

import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;


@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class Client implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @JoinColumn
    @Fetch(FetchMode.JOIN)
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Users user;
    
    @Column(nullable = false)
    private LocalDate dateOfBirth, dateRegistration;

    @Fetch(FetchMode.JOIN)
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "client")
    private List<Order> order;

    @Column
    private String firstName, lastName;

}
