package mk.ukim.finki.wpproekt.service;


import mk.ukim.finki.wpproekt.model.Product;
import mk.ukim.finki.wpproekt.model.dto.ProductDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<Product> findAll();

    Optional<Product> findById(Long id);

    Optional<Product> findByName(String name);

    Optional<Product> save(String name, String description, Double price, Integer quantity, Long category, Long author);

    Optional<Product> save(ProductDto productDto);

    Optional<Product> edit(Long id, String name, String description, Double price, Integer quantity, Long category, Long author);

    Optional<Product> edit(Long id, ProductDto productDto);

    void deleteById(Long id);
}
