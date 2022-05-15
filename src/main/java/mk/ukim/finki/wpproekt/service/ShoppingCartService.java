package mk.ukim.finki.wpproekt.service;



import mk.ukim.finki.wpproekt.model.Product;
import mk.ukim.finki.wpproekt.model.ShoppingCart;

import java.util.List;

public interface ShoppingCartService {

    List<Product> listAllProductsInShoppingCart(Long cartId);
    ShoppingCart getActiveShoppingCart(String username);
    ShoppingCart addProductToShoppingCart(String username, Long productId);
    void deleteProductFromShoppingCart(String username, Long id);

}

