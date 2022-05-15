package mk.ukim.finki.wpproekt.repository.jpa;

import mk.ukim.finki.wpproekt.model.ShoppingCart;
import mk.ukim.finki.wpproekt.model.User;
import mk.ukim.finki.wpproekt.model.enumerations.ShoppingCartStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {
    Optional<ShoppingCart> findByUserAndStatus(User user, ShoppingCartStatus status);
}

