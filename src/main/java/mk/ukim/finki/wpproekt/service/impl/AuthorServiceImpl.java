package mk.ukim.finki.wpproekt.service.impl;


import mk.ukim.finki.wpproekt.model.Author;
import mk.ukim.finki.wpproekt.repository.jpa.AuthorRepository;
import mk.ukim.finki.wpproekt.service.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public Author create(String name, String surname) {
        if (name==null || name.isEmpty()) {
            throw new IllegalArgumentException();
        }
        Author a  = new Author(name,surname);
        authorRepository.save(a);
        return a;
    }

    @Override
    public Optional<Author> findById(Long id) {
        return this.authorRepository.findById(id);
    }

    @Override
    public List<Author> findAll() {
        return this.authorRepository.findAll();
    }

    @Override
    public Optional<Author> save(String name, String surname) {
        return Optional.of(this.authorRepository.save(new Author(name, surname)));
    }

    @Override
    public void deleteById(Long id) {
        this.authorRepository.deleteById(id);
    }
}

