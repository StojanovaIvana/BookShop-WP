package mk.ukim.finki.wpproekt.model;


import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;

@Data
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private Double price;

    private Integer quantity;

    @ManyToOne
    private Category category;

    @ManyToOne
    private Author author;

    public Product() {
    }

    public Product(String name, String description, Double price, Integer quantity, Category category, Author author) {
        this.name = name;
        this.description=description;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
        this.author  = author;
    }
}

