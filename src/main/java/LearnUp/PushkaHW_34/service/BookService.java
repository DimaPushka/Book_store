package LearnUp.PushkaHW_34.service;

import LearnUp.PushkaHW_34.dao.entity.Book;
import LearnUp.PushkaHW_34.dao.filter.BookFilter;
import LearnUp.PushkaHW_34.dao.repository.BookRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.OptimisticLockException;
import java.util.List;

import static LearnUp.PushkaHW_34.dao.specification.BookSpecification.byBookFilter;


@Slf4j
@AllArgsConstructor
@Service
public class BookService {
    private final BookRepository bookRepository;

    public List<Book> getAllBooks(BookFilter bookFilter) {
        Specification<Book> specification = Specification.where(byBookFilter(bookFilter));
        log.info("Request getAllUsers: {}", specification);
        return bookRepository.findAll(specification);
    }

    public Book createBook(Book book) {
        log.info("CreateBook: {}", book.toString());
        return bookRepository.save(book);
    }

    public Book getBookById(Long id) {
        log.info("Request getBookById: {}", id);
        return bookRepository.getBooksById(id);
    }

    public Boolean deleteBook(Long id) {
        log.info("DeleteBook id: {}", id);
        bookRepository.delete(bookRepository.getById(id));
        return true;
    }

    public Book updateBook(Book book) {
        try {
            log.info("UpdateBook: {}", book.toString());
            return bookRepository.save(book);
        } catch (OptimisticLockException e) {
            log.warn("Optimistic lock exception for Book id {}", book.getId());
            throw e;
        }
    }

    public Book getBookByTitle(String title) {
        return bookRepository.getBooksByTitle(title);

    }
}