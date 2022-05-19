package LearnUp.PushkaHW_34.dao.repository;

import LearnUp.PushkaHW_34.dao.entity.BookStorage;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookStorageRepository extends JpaRepository<BookStorage, Long> {

    List<BookStorage> findAll(Specification<BookStorage> specification);

    BookStorage getBookStorageById(Long id);
}
