package LearnUp.PushkaHW_34.view.mapper;

import LearnUp.PushkaHW_34.dao.entity.BookStorage;
import LearnUp.PushkaHW_34.service.BookStorageService;
import LearnUp.PushkaHW_34.view.BookStorageView;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class BookStorageViewMapper {

    private final BookStorageService bookStorageService;
    private final BookStorageViewMapper bookStorageViewMapper;

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @GetMapping
    public List<BookStorageView> getBookWarehouse(
            @RequestParam(value = "theRestOfTheBooks", required = false) Long theRestOfTheBooks
    ) {
        return BookStorageService.getAllBookStorage(new BookStorageFilter(theRestOfTheBooks))
                .stream()
                .map(bookStorageViewMapper::mapBookStorageToView)
                .collect(Collectors.toList());
    }

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @GetMapping("/{id}")
    public BookStorageView getBookWarehouseById(@PathVariable("id") Long id) {
        return bookStorageViewMapper.mapBookStorageToView(bookStorageService.getBookStorageById(id));
    }

    @Secured({"ROLE_ADMIN"})
    @PostMapping
    public BookStorageView createBookWarehouse(@RequestBody BookStorageView bookStorageView) {
        if (bookStorageView.getId() != null) {
            throw new EntityExistsException(
                    String.format("BookWarehouse with id = %s already exist", bookStorageView.getId())
            );
        }
        BookStorage bookStorage = bookStorageViewMapper.mapBookStorageFromView(bookStorageView);
        BookStorage createBookStorage = bookStorageService.createBookStorage(bookStorage);
        return bookStorageViewMapper.mapBookStorageToView(createBookStorage);
    }

    @Secured({"ROLE_ADMIN"})
    @PutMapping("/{id}")
    public BookStorageView updateBookStorage(@PathVariable("id") Long id,
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
        BookStorage updateBookStorage = bookStorageService.updateBookStorage(bookStorage);
        return bookStorageViewMapper.mapBookStorageToView(updateBookStorage);
    }

    @Secured({"ROLE_ADMIN"})
    @DeleteMapping("/{id}")
    public Boolean deleteBookWarehouse(@PathVariable("id") Long id) {
        return bookStorageService.deleteBookStorage(id);
    }
    
}
