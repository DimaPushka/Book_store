package LearnUp.PushkaHW_34.controller;

import LearnUp.PushkaHW_34.dao.entity.Book;
import LearnUp.PushkaHW_34.dao.entity.BookStorage;
import LearnUp.PushkaHW_34.dao.filter.StorageFilter;
import LearnUp.PushkaHW_34.service.BookService;
import LearnUp.PushkaHW_34.service.BookStorageService;
import LearnUp.PushkaHW_34.view.BookStorageView;
import LearnUp.PushkaHW_34.view.mapper.BookStorageViewMapper;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
@RequestMapping("rest/storage")
public class StorageControllerRest {
    
    private final BookStorageService bookStorageService;

    private final BookService bookService;
    private final BookStorageViewMapper bookStorageViewMapper;
    
    @GetMapping
    public List<BookStorageViewMapper> getBookStorage(
            @RequestParam(value = "book", required = false) Book book,
            @RequestParam(value = "theRestOfTheBooks", required = false) Long theRestOfTheBooks
    ) {
        return bookStorageService.getAllBookStorage(new StorageFilter(book, theRestOfTheBooks))
                .stream()
                .map(bookStorageViewMapper::mapBookStorageToView)
                .collect(Collectors.toList());
    }
    
    @GetMapping("/{id}")
    public BookStorageViewMapper booksBookStorage(@PathVariable("id") Long id) {
        return bookStorageViewMapper.mapBookStorageToView(bookStorageService.getBookStorageById(id));
    }
    
    @PostMapping
    public BookStorageViewMapper createBookStorage(@RequestBody BookStorageView bookStorageView) {
        if (bookStorageView.getId() != null) {
            throw new EntityExistsException(
                    String.format("Book with id = %s already exist", bookStorageView.getId())
            );
        }
        BookStorage bookStorage = bookStorageViewMapper.mapBookStorageToView(bookStorageViewMapper);
        BookStorage createBookStorage = bookStorageService.createBookStorage(bookStorage);
        return bookStorageViewMapper.mapBookStorageToView(createBookStorage);
    }
    
    @PutMapping("/{id}")
    public BookStorageViewMapper updateBookStorage(@PathVariable("id") Long id,
                                                   @RequestBody BookStorageView bookStorageView) {
        if (bookStorageView.getId() == null) {
            throw new EntityNotFoundException("Try to found null entity");
        }
        if (!Objects.equals(id, bookStorageView.getId())) {
            throw new RuntimeException("Entity has bad id");
        }
        BookStorage bookStorage = bookStorageService.getBookStorageById(id);
        if (!bookStorage.getTheRestOfTheBooks().equals(bookStorageView.getTheRestOfTheBooks())) {
            bookStorage.setTheRestOfTheBooks(bookStorageView.getTheRestOfTheBooks());
        }
        if (!bookStorage.getBook().equals(bookStorageView.getBook())) {
            bookStorage.setBook(bookStorageView.getBook());
        }
        if (!bookStorage.getVersion().equals(bookStorageView.getVersion())) {
            bookStorage.setVersion(bookStorageView.getVersion());
        }
        BookStorage updateBookStorage = bookStorageService.updateBookStorage(bookStorage);
        return bookStorageViewMapper.mapBookStorageToView(updateBookStorage);
    }
    
    @DeleteMapping("/{id}")
    public Boolean deleteBookWarehouse(@PathVariable("id") Long id) {
        return bookStorageService.deleteBookStorage(id);
    }
    
}
