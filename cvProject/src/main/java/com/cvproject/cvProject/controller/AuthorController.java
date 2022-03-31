package com.cvproject.cvProject.controller;

import com.cvproject.cvProject.model.Author;
import org.springframework.web.bind.annotation.*;
import com.cvproject.cvProject.service.AuthorService;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/author")
public class AuthorController {
    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/getAuthors")
    public void getAllAuthors() {
        List<Author> allAuthors = authorService.findAllAuthors();
        allAuthors.stream()
                .sorted(Comparator.comparing(Author::getName))
                .collect(Collectors.toList());

    }

    @PostMapping("/create")
    public Author createAuthor(@RequestBody Author author) {
        return authorService.createAuthor(author);
    }

    @GetMapping("/authors/{id}")
    public Author getAuthorById(@PathVariable("id")Long id) {
        return authorService.findAuthorsById(id);
    }

    @PostMapping("/update")
    public Author updateAuthor(@RequestBody Author author) {
        return authorService.updateAuthor(author);
    }

    @DeleteMapping(path = "{authorid}")
    public void deleteAuthor(@PathVariable("authorid") Long id) {
        authorService.deleteAuthorById(id);
    }
}
