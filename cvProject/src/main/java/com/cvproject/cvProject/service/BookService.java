package com.cvproject.cvProject.service;

import com.cvproject.cvProject.exception.BookNotFoundException;
import com.cvproject.cvProject.model.Book;
import com.cvproject.cvProject.repository.BookRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {
    private final BookRepository bookRepository;
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        return books.stream()
                .sorted(Comparator.comparing(Book::getName))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    @Transactional(readOnly = true)
    public Book updateBook(Book updateBook) {
        Book book = bookRepository.findById(updateBook.getId())
                .orElseThrow(()-> new BookNotFoundException(""));
        book.setId(book.getId());
        book.setAuthor(book.getAuthor());
        book.setGenre(book.getGenre());
        book.setName(book.getName());
        book.setPublisher(book.getPublisher());
        return bookRepository.save(book);
    }

    //@Transactional(readOnly = true)
    public List<Book> searchBook(String keyword) {
        if(keyword != null) {
            return bookRepository.search(keyword);
        }
        return bookRepository.findAll();
    }

    //@Transactional(readOnly = true)
    public Book findBookById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(()-> new BookNotFoundException("Book with id: " + id + " not found"));
    }

    @Transactional(readOnly = true)
    public void deleteBook(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("Book with id: " + id + " not found"));
        bookRepository.deleteById(book.getId());
    }

}
