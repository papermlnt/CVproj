package com.cvproject.cvProject.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(name = "book_genre",
               joinColumns = {@JoinColumn(name = "book_id")},
               inverseJoinColumns = {@JoinColumn(name = "genre_genre_id")})
    private Set<Genre> genre = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinTable(name = "book_publisher",
               joinColumns = {@JoinColumn(name = "book_id")},
               inverseJoinColumns = {@JoinColumn(name = "publisher_publisher_id")})
    private Set<Publisher> publisher = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinTable(name = "book_author",
               joinColumns = {@JoinColumn(name = "book_id")},
               inverseJoinColumns = {@JoinColumn(name = "author_author_id")})
    private Set<Author> author = new HashSet<>();
}
