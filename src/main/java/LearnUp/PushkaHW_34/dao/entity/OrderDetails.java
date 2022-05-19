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
@ToString
public class OrderDetails implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @OneToOne
    private Order order;
    
    @JoinColumn
    @Fetch(FetchMode.JOIN)
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Book book;
    
    @Min(value = 0)
    @Column(nullable = false)
    private Long quantity, price;
    
}
