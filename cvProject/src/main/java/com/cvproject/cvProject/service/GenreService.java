package com.cvproject.cvProject.service;

import com.cvproject.cvProject.exception.GenreNotFoundException;
import com.cvproject.cvProject.model.Genre;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.cvproject.cvProject.repository.GenreRepository;

import java.util.List;

@Service
public class GenreService {
    private final GenreRepository genreRepository;
    public GenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    public List<Genre> getAllGenres() {
        return genreRepository.findAll();
    }

    public Genre getGenreById(Long id) {
        return genreRepository.findById(id)
                .orElseThrow(()-> new GenreNotFoundException("Gnere with id: " + id + "not found"));

    }

    @Transactional(readOnly = true)
    public Genre createGenre(Genre genre) {
        return genreRepository.save(genre);
    }

    @Transactional(readOnly = true)
    public Genre updateGenre(Genre updateGenre) {
        Genre genre = genreRepository.findById(updateGenre.getId())
                .orElseThrow(()-> new GenreNotFoundException("Genre with id:"  + updateGenre + " not found"));
        genre.setId(genre.getId());
        genre.setName(genre.getName());
        genre.setDescription(genre.getDescription());
        genre.setBooks(genre.getBooks());

        return genreRepository.save(genre);
    }

    @Transactional(readOnly = true)
    public void deleteGenre(Long id) {
        Genre genre = genreRepository.findById(id)
                .orElseThrow(()-> new GenreNotFoundException(""));
        genreRepository.deleteById(genre.getId());

    }

}
