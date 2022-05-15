package mk.ukim.finki.wpproekt.repository.jpa;

import mk.ukim.finki.wpproekt.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
}

