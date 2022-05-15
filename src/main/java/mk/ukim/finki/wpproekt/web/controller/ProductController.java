package mk.ukim.finki.wpproekt.web.controller;


import mk.ukim.finki.wpproekt.model.Author;
import mk.ukim.finki.wpproekt.model.*;
import mk.ukim.finki.wpproekt.service.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;
    private final CategoryService categoryService;
    private final AuthorService authorService;

    public ProductController(ProductService productService, CategoryService categoryService, AuthorService authorService) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.authorService = authorService;
    }
    @PostMapping("/add")
    public String saveProduct(
            @RequestParam(required = false) Long id,
            @RequestParam String name,
            @RequestParam String description,
            @RequestParam Double price,
            @RequestParam Integer quantity,
            @RequestParam Long category,
            @RequestParam Long author
    ) {
        if(id!=null) {
            this.productService.edit(id, name, description, price, quantity, category, author);
        } else {
            this.productService.save(name, description, price, quantity, category, author);
        }
        return "redirect:/products";
    }
    @GetMapping("/add-form")
    @PreAuthorize("hasRole('ADMIN')")
    public String addProductPage(Model model) {
        List<Author> authors=this.authorService.findAll();
        List<Category> categories=this.categoryService.listCategories();
        model.addAttribute("authors", authors);
        model.addAttribute("categories", categories);
        model.addAttribute("bodyContent", "add-product");
        return "master-template";
    }
    @GetMapping
    public String getProductPage(@RequestParam(required = false) String error, Model model) {
        if(error!=null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        List<Product> products=this.productService.findAll();
        model.addAttribute("products", products);
        model.addAttribute("bodyContent", "products");
        return "master-template";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        this.productService.deleteById(id);
        return "redirect:/products";
    }

    @GetMapping("/edit-form/{id}")
    public String editProductPage(@PathVariable Long id, Model model) {
        if(this.productService.findById(id).isPresent()) {
            Product product=this.productService.findById(id).get();
            List<Author> authors=this.authorService.findAll();
            List<Category> categories=this.categoryService.listCategories();
            model.addAttribute("authors", authors);
            model.addAttribute("categories", categories);
            model.addAttribute("product", product);
            model.addAttribute("bodyContent", "add-product");
            return "master-template";
        }
        return "redirect:/products?=ProductNotFound";
    }



}