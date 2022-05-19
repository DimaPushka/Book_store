package LearnUp.PushkaHW_34.dao.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.io.Serializable;


@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class BookStorage implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @OneToOne(optional = false, fetch = FetchType.LAZY)
    private Book book;
    
    @Min(value = 0)
    @Column(nullable = false)
    private Long theRestOfTheBooks;
    
    @Version
    private Long version;

    public BookStorage(Long id, Book book, Long theRestOfTheBooks) {
        this.id = id;
        this.theRestOfTheBooks = theRestOfTheBooks;
        this.book = book;
    }
}
