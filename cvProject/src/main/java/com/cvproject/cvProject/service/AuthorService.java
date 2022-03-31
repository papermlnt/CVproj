package com.cvproject.cvProject.service;

import com.cvproject.cvProject.repository.AuthorRepository;
import com.cvproject.cvProject.exception.AuthorNotFoundException;
import com.cvproject.cvProject.model.Author;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorService {
    private final AuthorRepository authorRepository;
    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public List<Author> findAllAuthors() {
        List<Author> authors = authorRepository.findAll();
        return authors.stream()
                .sorted(Comparator.comparing(Author::getName))
                .collect(Collectors.toList());
    }

    public Author createAuthor(Author author) {
        return authorRepository.save(author);
    }

    @Transactional(readOnly = true)
    public Author findAuthorsById(Long id) {
        return authorRepository.findById(id)
                .orElseThrow(()-> new AuthorNotFoundException("Author with: " + id + " not found"));
    }

    @Transactional(readOnly = true)
    public Author updateAuthor(Author updateAuthor) {
        Author author = authorRepository.findById(updateAuthor.getId())
                .orElseThrow(() -> new AuthorNotFoundException("Author with id not found"));
        author.setId(author.getId());
        author.setName(author.getName());
        author.setBooks(author.getBooks());
        author.setSecondName(author.getSecondName());
        author.setDescription(author.getDescription());

        return authorRepository.save(author);
    }

    public void deleteAuthorById(Long id) {
        Author author = authorRepository.findById(id)
                .orElseThrow(()-> new AuthorNotFoundException("Author not found"));
        authorRepository.deleteById(author.getId());
    }

}
