package LearnUp.PushkaHW_34.controller;

import LearnUp.PushkaHW_34.dao.entity.Author;
import LearnUp.PushkaHW_34.dao.entity.Book;
import LearnUp.PushkaHW_34.dao.entity.BookStorage;
import LearnUp.PushkaHW_34.dao.filter.BookFilter;
import LearnUp.PushkaHW_34.service.AuthorService;
import LearnUp.PushkaHW_34.service.BookService;
import LearnUp.PushkaHW_34.view.BooksView;
import LearnUp.PushkaHW_34.view.mapper.BooksViewMapper;
import lombok.AllArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
@RequestMapping("rest/bookshop")
public class BookControllerRest {

    private final BookService bookService;
    private final AuthorService authorService;
    private final BooksViewMapper booksViewMapper;

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @GetMapping
    public List<BooksView> getBooks(
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "author", required = false) Author author,
            @RequestParam(value = "yearOfPublication", required = false) LocalDate yearOfPublication,
            @RequestParam(value = "numberOfPages", required = false) Long numberOfPages,
            @RequestParam(value = "price", required = false) Long price,
            @RequestParam(value = "bookStorage", required = false) BookStorage bookStorage
    ) {
        return bookService.getAllBooks(new BookFilter(title, author, yearOfPublication, numberOfPages, price, bookStorage))
                .stream()
                .map(booksViewMapper::mapBooksToView)
                .collect(Collectors.toList());
    }

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @GetMapping("/{id}")
    public BooksView getBookById(@PathVariable("id") Long id) {
        return booksViewMapper.mapBooksToView(bookService.getBookById(id));
    }

    @Secured({"ROLE_ADMIN"})
    @PostMapping
    public BooksView createBook(@RequestBody BooksView booksView) {
        if (booksView.getId() != null) {
            throw new EntityExistsException(
                    String.format("Books with id = %s already exist", booksView.getId())
            );
        }
        Book books = booksViewMapper.mapBooksFromView(booksView, authorService);
        Book createBook = bookService.createBook(books);
        return booksViewMapper.mapBooksToView(createBook);
    }

    @Secured({"ROLE_ADMIN"})
    @PutMapping("/{id}")
    public BooksView updateBook(@PathVariable("id") Long id,
                                @RequestBody BooksView authorsView) {
        if (authorsView.getId() == null) {
            throw new EntityNotFoundException("Try to found null entity");
        }
        if (!Objects.equals(id, authorsView.getId())) {
            throw new RuntimeException("Entity has bad id");
        }
        Book authors = bookService.getBookById(id);
        if (!authors.getTitle().equals(authorsView.getTitle())) {
            authors.setTitle(authorsView.getTitle());
        }
        if (!authors.getPrice().equals(authorsView.getPrice())) {
            authors.setPrice(authorsView.getPrice());
        }
        if (!authors.getNumberOfPages().equals(authorsView.getNumberOfPages())) {
            authors.setNumberOfPages(authorsView.getNumberOfPages());
        }
        if (!authors.getYearOfPublication().equals(authorsView.getYearOfPublication())) {
            authors.setYearOfPublication(authorsView.getYearOfPublication());
        }
        Book updateBook = bookService.updateBook(authors);
        return booksViewMapper.mapBooksToView(updateBook);
    }

    @Secured({"ROLE_ADMIN"})
    @DeleteMapping("/{id}")
    public Boolean deleteBook(@PathVariable("id") Long id) {
        return bookService.deleteBook(id);
    }
    
}
