package LearnUp.PushkaHW_34.controller;

import LearnUp.PushkaHW_34.dao.entity.Author;
import LearnUp.PushkaHW_34.dao.filter.AuthorFilter;
import LearnUp.PushkaHW_34.service.AuthorService;
import LearnUp.PushkaHW_34.view.AuthorsView;
import LearnUp.PushkaHW_34.view.mapper.AuthorsViewMapper;
import lombok.AllArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
@RequestMapping("rest/authors")
public class AuthorControllerRest {

    private final AuthorService authorsService;
    private final AuthorsViewMapper authorsViewMapper;

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @GetMapping
    public List<AuthorsView> getAuthors(
            @RequestParam(value = "fullName", required = false) String fullName
    ) {
        return authorsService.getAllAuthors(new AuthorFilter(fullName))
                .stream()
                .map(authorsViewMapper::mapAuthorsToView)
                .collect(Collectors.toList());
    }

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @GetMapping("/{id}")
    public AuthorsView getAuthorById(@PathVariable("id") Long id) {
        return authorsViewMapper.mapAuthorsToView(authorsService.getAuthorById(id));
    }

    @Secured({"ROLE_ADMIN"})
    @PostMapping
    public AuthorsView createAuthor(@RequestBody AuthorsView authorsView) {
        if (authorsView.getId() != null) {
            throw new EntityExistsException(
                    String.format("Authors with id = %s already exist", authorsView.getId())
            );
        }
        Author author = authorsViewMapper.mapAuthorsFromView(authorsView);
        Author createAuthor = authorsService.createAuthor(author);
        return authorsViewMapper.mapAuthorsToView(createAuthor);
    }

    @Secured({"ROLE_ADMIN"})
    @PutMapping("/{id}")
    public AuthorsView updateAuthor(@PathVariable("id") Long id,
                                    @RequestBody AuthorsView authorsView) {
        if (authorsView.getId() == null) {
            throw new EntityNotFoundException("Try to found null entity");
        }
        if (!Objects.equals(id, authorsView.getId())) {
            throw new RuntimeException("Entity has bad id");
        }
        Author author = authorsService.getAuthorById(id);
        if (!author.getFullName().equals(authorsView.getFullName())) {
            author.setFullName(authorsView.getFullName());
        }
        Author updateAuthor = authorsService.updateAuthor(author);
        return authorsViewMapper.mapAuthorsToView(updateAuthor);
    }

    @Secured({"ROLE_ADMIN"})
    @DeleteMapping("/{id}")
    public Boolean deleteAuthor(@PathVariable("id") Long id) {
        return authorsService.deleteAuthor(id);
    }
    
}
