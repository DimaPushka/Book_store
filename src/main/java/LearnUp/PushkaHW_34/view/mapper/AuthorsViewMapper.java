package LearnUp.PushkaHW_34.view.mapper;

import LearnUp.PushkaHW_34.dao.entity.Author;
import LearnUp.PushkaHW_34.view.AuthorsView;
import LearnUp.PushkaHW_34.view.BooksFromAuthorView;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class AuthorsViewMapper {

    public AuthorsView mapAuthorsToView(Author author) {
        AuthorsView authorsView = new AuthorsView();
        authorsView.setId(author.getId());
        authorsView.setFullName(author.getFullName());
        if (author.getBook() != null) {
            authorsView.setBooks(
                    author.getBook()
                            .stream()
                            .map(books -> new BooksFromAuthorView(
                                    books.getId(),
                                    books.getTitle(),
                                    books.getYearOfPublication(),
                                    books.getNumberOfPages(),
                                    books.getPrice()))
                            .collect(Collectors.toList()));
        }
        return authorsView;
    }

    public Author mapAuthorsFromView(AuthorsView authorsView) {
        Author authors = new Author();
        authors.setId(authorsView.getId());
        authors.setFullName(authorsView.getFullName());
        return authors;
    }
    
}
