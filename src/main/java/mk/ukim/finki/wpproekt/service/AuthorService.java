package mk.ukim.finki.wpproekt.service;

import mk.ukim.finki.wpproekt.model.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    Author create(String name, String surname);
    Optional<Author> findById(Long id);
    List<Author> findAll();
    Optional<Author> save(String name, String surname);
    void deleteById(Long id);

}

