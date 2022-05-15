package mk.ukim.finki.wpproekt.model.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;


@Data
public class ProductDto {

    private String name;

    private String description;

    private Double price;

    private Integer quantity;

    private Long category;

    private Long author;

    public ProductDto() {
    }

    public ProductDto(String name,String description, Double price, Integer quantity, Long category, Long author) {
        this.name = name;
        this.description=description;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
        this.author = author;
    }
}