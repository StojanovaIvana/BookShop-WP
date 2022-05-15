package mk.ukim.finki.wpproekt.service.impl;


import mk.ukim.finki.wpproekt.model.Author;
import mk.ukim.finki.wpproekt.model.Category;
import mk.ukim.finki.wpproekt.model.Product;
import mk.ukim.finki.wpproekt.model.dto.ProductDto;
import mk.ukim.finki.wpproekt.model.exceptions.*;
import mk.ukim.finki.wpproekt.repository.jpa.AuthorRepository;
import mk.ukim.finki.wpproekt.repository.jpa.CategoryRepository;
import mk.ukim.finki.wpproekt.repository.jpa.ProductRepository;
import mk.ukim.finki.wpproekt.service.ProductService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final AuthorRepository authorRepository;
    private final CategoryRepository categoryRepository;

    public ProductServiceImpl(ProductRepository productRepository,
                              AuthorRepository authorRepository,
                              CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.authorRepository = authorRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Product> findAll() {
        return this.productRepository.findAll();
    }

    @Override
    public Optional<Product> findById(Long id) {
        return this.productRepository.findById(id);
    }

    @Override
    public Optional<Product> findByName(String name) {
        return this.productRepository.findByName(name);
    }

    @Override
    @Transactional
    public Optional<Product> save(String name, String description, Double price, Integer quantity, Long categoryId, Long authorId) {
        Category category = this.categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException(categoryId));
        Author author = this.authorRepository.findById(authorId)
                .orElseThrow(() -> new AuthorNotFoundException(authorId));

        this.productRepository.deleteByName(name);
        return Optional.of(this.productRepository.save(new Product(name,description, price, quantity, category, author)));
    }

    @Override
    public Optional<Product> save(ProductDto productDto) {
        Category category = this.categoryRepository.findById(productDto.getCategory())
                .orElseThrow(() -> new CategoryNotFoundException(productDto.getCategory()));
        Author author = this.authorRepository.findById(productDto.getAuthor())
                .orElseThrow(() -> new AuthorNotFoundException(productDto.getAuthor()));

        this.productRepository.deleteByName(productDto.getName());
        return Optional.of(this.productRepository.save(new Product(productDto.getName(),productDto.getDescription(),productDto.getPrice(), productDto.getQuantity(), category, author)));
    }

    @Override
    @Transactional
    public Optional<Product> edit(Long id, String name, String description, Double price, Integer quantity, Long categoryId, Long authorId) {

        Product product = this.productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));

        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);
        product.setQuantity(quantity);

        Category category = this.categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException(categoryId));
        product.setCategory(category);

        Author author = this.authorRepository.findById(authorId)
                .orElseThrow(() -> new AuthorNotFoundException(authorId));
        product.setAuthor(author);

        return Optional.of(this.productRepository.save(product));
    }

    @Override
    public Optional<Product> edit(Long id, ProductDto productDto) {
        Product product = this.productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));

        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setQuantity(productDto.getQuantity());

        Category category = this.categoryRepository.findById(productDto.getCategory())
                .orElseThrow(() -> new CategoryNotFoundException(productDto.getCategory()));
        product.setCategory(category);

        Author author = this.authorRepository.findById(productDto.getAuthor())
                .orElseThrow(() -> new AuthorNotFoundException(productDto.getAuthor()));
        product.setAuthor(author);

        return Optional.of(this.productRepository.save(product));
    }

    @Override
    public void deleteById(Long id) {
        this.productRepository.deleteById(id);
    }

}

