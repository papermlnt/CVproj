package com.cvproject.cvProject.controller;

import com.cvproject.cvProject.model.Book;
import org.springframework.web.bind.annotation.*;
import com.cvproject.cvProject.service.BookService;

@RestController
@RequestMapping("api/v1/book")
public class BookController {
    private final BookService bookService;
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/createBook")
    public Book createBook(@RequestBody Book book) {
        return bookService.createBook(book);
    }

    @GetMapping("/books")
    public void getAllBooks() {
         bookService.getAllBooks();
    }

    @GetMapping("/books/{id}")
    public Book getBookById(@PathVariable("id") Long id) {
        return bookService.findBookById(id);
    }

    @PostMapping("/update")
    public Book updateBook(@RequestBody Book book) {
        return bookService.updateBook(book);
    }

    @DeleteMapping(path = "{bookid}")
    public void deleteBook(@PathVariable("bookid") Long id) {
            bookService.deleteBook(id);
    }
}
