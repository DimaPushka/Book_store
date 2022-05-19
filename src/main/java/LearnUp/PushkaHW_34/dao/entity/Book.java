package LearnUp.PushkaHW_34.dao.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.io.Serializable;
import java.time.LocalDate;


@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class Book implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String title;
    
    @Column(nullable = false)
    private LocalDate yearOfPublication;
    
    @Min(value = 0)
    @Column(nullable = false)
    private Long numberOfPages, price;

    @JoinColumn
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Author author;

    @OneToOne(optional = false, fetch = FetchType.LAZY)
    private BookStorage bookStorage;

}
