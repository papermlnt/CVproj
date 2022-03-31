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
@Table(name = "publisher")
public class Publisher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 55, nullable = false)
    private String name;

    @Column(name = "second_name", length = 55, nullable = false)
    private String secondName;

    @Column(name = "description")
    private String description;

    @Column(name = "email",nullable = false)
    private String email;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {
            CascadeType.MERGE,
            CascadeType.PERSIST} , mappedBy = "publisher")
    private Set<Book> books = new HashSet<>();
}
