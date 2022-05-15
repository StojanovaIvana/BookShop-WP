package mk.ukim.finki.wpproekt.web.controller;


import mk.ukim.finki.wpproekt.model.Author;
import mk.ukim.finki.wpproekt.model.Category;
import mk.ukim.finki.wpproekt.service.AuthorService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/authors")
public class AuthorController {
    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {

        this.authorService = authorService;
    }

    @PostMapping("/add")
    public String saveAuthor(
            @RequestParam(required = false) Long id,
            @RequestParam String name,
            @RequestParam String surname) {

        this.authorService.create(name, surname);
        return "redirect:/authors";
    }


    @GetMapping("/add-form")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String addManufacturerPage(Model model) {
        List<Author> authors = this.authorService.findAll();
        model.addAttribute("authors", authors);
        model.addAttribute("bodyContent", "add-author");
        return "master-template";
    }


    @GetMapping
    public String getAuthorPage(@RequestParam(required = false) String error, Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        List<Author> authors = this.authorService.findAll();
        model.addAttribute("authors", authors);
        model.addAttribute("bodyContent", "authors");
        return "master-template";
    }
    @DeleteMapping("/delete/{id}")
    public String deleteAuthor(@PathVariable Long id) {
        this.authorService.deleteById(id);
        return "redirect:/authors";
    }
}