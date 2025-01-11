package com.BootcampFactoria.Library.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Authors")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String biography;

    @ManyToMany(mappedBy = "authors", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Book> books;

    public Author(String name, String biography, List<Book> author_books) {
        this.name = name;
        this.biography = biography;
        this.books = author_books;
    }

    public Author(String name) {
        this.name = name;
    }
}
