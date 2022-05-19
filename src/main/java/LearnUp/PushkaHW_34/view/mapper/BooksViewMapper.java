package LearnUp.PushkaHW_34.view.mapper;

import LearnUp.PushkaHW_34.dao.entity.Author;
import LearnUp.PushkaHW_34.dao.entity.Book;
import LearnUp.PushkaHW_34.dao.entity.BookStorage;
import LearnUp.PushkaHW_34.service.AuthorService;
import LearnUp.PushkaHW_34.view.AuthorsFromBookView;
import LearnUp.PushkaHW_34.view.BooksView;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BooksViewMapper {

    public BooksView mapBooksToView(Book book) {
        BooksView booksView = new BooksView();
        booksView.setId(book.getId());
        booksView.setTitle(book.getTitle());
        booksView.setPrice(book.getPrice());
        booksView.setNumberOfPages(book.getNumberOfPages());
        booksView.setYearOfPublication(book.getYearOfPublication());
        booksView.setAuthor(
                new AuthorsFromBookView(
                        book.getAuthor().getId(),
                        book.getAuthor().getFullName()
                ));
        booksView.setBookStorage(
                new BookStorageViewMapper(
                        book.getBookStorage().getId(),
                        book.getBookStorage().getTheRestOfTheBooks()
                )
        );
        return booksView;
    }

    public Book mapBooksFromView(BooksView booksView, AuthorService authorService) {
        Book book = new Book();
        book.setId(booksView.getId());
        book.setTitle(booksView.getTitle());
        book.setPrice(booksView.getPrice());
        book.setNumberOfPages(booksView.getNumberOfPages());
        book.setYearOfPublication(booksView.getYearOfPublication());
        List<Book> booksList = new ArrayList<>();
        booksList.add(book);
        book.setAuthor(
                new Author(
                        booksView.getAuthor().getId(),
                        booksView.getAuthor().getFullName(),
                        booksList));
        book.setBookStorage(
                new BookStorage(
                        booksView.getBookStorage().getId(),
                        book,
                        booksView.getBookStorage().getTheRestOfTheBooks()
                ));
        return book;
    }
    
}
