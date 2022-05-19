package LearnUp.PushkaHW_34.service;

import LearnUp.PushkaHW_34.dao.entity.BookStorage;
import LearnUp.PushkaHW_34.dao.filter.StorageFilter;
import LearnUp.PushkaHW_34.dao.repository.BookStorageRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import java.util.List;

import static LearnUp.PushkaHW_34.dao.specification.BookStorageSpecification.byBookStorageFilter;


@Slf4j
@AllArgsConstructor
@Service
public class BookStorageService {
    
    private final BookStorageRepository bookStorageRepository;

    public List<BookStorage> getAllBookStorage(StorageFilter bookStorage) {
        Specification<BookStorage> specification = Specification.where(byBookStorageFilter(bookStorage));
        log.info("Request getAllBookStorage: {}", specification);
        return bookStorageRepository.findAll(specification);
    }
    
    @Transactional
    public BookStorage createBookStorage(BookStorage bookStorage) {
        log.info("CreateBookStorage: {}", bookStorage.toString());
        return bookStorageRepository.save(bookStorage);
    }
    
    public BookStorage getBookStorageById(Long id) {
        log.info("Request getBookStorageById: {}", id);
        return bookStorageRepository.getBookStorageById(id);
    }
    
    public Boolean deleteBookStorage(Long id) {
        log.info("DeleteBookStorage id: {}", id);
        bookStorageRepository.delete(bookStorageRepository.getById(id));
        return true;
    }
    
    @Transactional
    public BookStorage updateBookStorage(BookStorage bookStorage) {
        try {
            log.info("UpdateBookStorage: {}", bookStorage.toString());
            return bookStorageRepository.save(bookStorage);
        } catch (OptimisticLockException e) {
            log.warn("Optimistic lock exception for BookStorage id {}", bookStorage.getId());
            throw e;
        }
    }

}
