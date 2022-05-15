package mk.ukim.finki.wpproekt.repository.impl;

import mk.ukim.finki.wpproekt.bootstrap.DataHolder;
import mk.ukim.finki.wpproekt.model.Author;
import mk.ukim.finki.wpproekt.model.Category;
import mk.ukim.finki.wpproekt.model.Product;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;


    @Repository
    public class InMemoryProductRepository {

        public List<Product> findAll() {
            return DataHolder.products;
        }

        public Optional<Product> findById(Long id) {
            return DataHolder.products.stream().filter(i -> i.getId().equals(id)).findFirst();
        }

        public Optional<Product> findByName(String name) {
            return DataHolder.products.stream().filter(i -> i.getName().equals(name)).findFirst();
        }

        public void deleteById(Long id) {
            DataHolder.products.removeIf(i -> i.getId().equals(id));
        }

        public Optional<Product> save(String name, String description, Double price, Integer quantity,
                                      Category category, Author author) {
            DataHolder.products.removeIf(i -> i.getName().equals(name));
            Product product = new Product(name,description, price, quantity, category, author);
            DataHolder.products.add(product);
            return Optional.of(product);
        }
    }
