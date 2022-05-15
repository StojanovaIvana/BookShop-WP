package mk.ukim.finki.wpproekt.bootstrap;

import lombok.Getter;
import mk.ukim.finki.wpproekt.model.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Getter
public class DataHolder {

    public static List<Category> categories = new ArrayList<>();
    public static List<User> users = new ArrayList<>();
    public static List<Product> products = new ArrayList<>();
    public static List<Author> authors = new ArrayList<>();
    public static List<ShoppingCart> shoppingCarts = new ArrayList<>();

}
