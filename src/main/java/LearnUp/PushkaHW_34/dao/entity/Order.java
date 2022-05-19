package LearnUp.PushkaHW_34.dao.entity;

import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.io.Serializable;


@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class Order implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Min(value = 0)
    @Column(nullable = false)
    private Long purchaseAmount;

    @Fetch(FetchMode.JOIN)
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Client client;

    @OneToOne
    private OrderDetails orderDetails;
}
