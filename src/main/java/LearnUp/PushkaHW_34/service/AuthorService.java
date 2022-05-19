package LearnUp.PushkaHW_34.service;

import LearnUp.PushkaHW_34.dao.entity.Author;
import LearnUp.PushkaHW_34.dao.filter.AuthorFilter;
import LearnUp.PushkaHW_34.dao.repository.AuthorRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.OptimisticLockException;
import java.util.List;

import static LearnUp.PushkaHW_34.dao.specification.AuthorSpecification.byAuthorFilter;


@Slf4j
@AllArgsConstructor
@Service
public class AuthorService {

    private final AuthorRepository authorRepository;
    
    public List<Author> getAllAuthors(AuthorFilter authorFilter) {
        Specification<Author> specification = Specification.where(byAuthorFilter(authorFilter));
        log.info("Request getAllAuthors: {}", specification);
        return authorRepository.findAll(specification);
    }
    
    public Author createAuthor(Author author) {
        log.warn("CreateAuthor: {}", author.toString());
        return authorRepository.save(author);
    }
    
    public Author getAuthorById(Long id) {
        log.warn("Request getAuthorById: {}", id);
        Author author = authorRepository.getAuthorsById(id);
        log.warn("Author: {}", author.toString());
        return author;
    }
    
    public Boolean deleteAuthor(Long id) {
        log.warn("DeleteAuthor id: {}", id);
        authorRepository.delete(authorRepository.getById(id));
        return true;
    }
    
    public Author updateAuthor(Author author) {
        try {
            log.info("UpdateAuthor: {}", author.toString());
            return authorRepository.save(author);
        } catch (OptimisticLockException e) {
            log.warn("Optimistic lock exception for Author id {}", author.getId());
            throw e;
        }
    }
    
}
