package mk.ukim.finki.wpproekt.repository.impl;

import mk.ukim.finki.wpproekt.bootstrap.DataHolder;
import mk.ukim.finki.wpproekt.model.Author;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryAuthorRepository {

    public List<Author> findAll() {
        return DataHolder.authors;
    }

    public Optional<Author> findById(Long id) {
        return DataHolder.authors.stream().filter(i -> i.getId().equals(id)).findFirst();
    }

    public Optional<Author> save(String name, String surname) {
        Author author = new Author(name, surname);
        DataHolder.authors.add(author);
        return Optional.of(author);
    }

    public boolean deleteById(Long id) {
        return DataHolder.authors.removeIf(i -> i.getId().equals(id));
    }
}

