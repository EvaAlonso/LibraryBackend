package com.BootcampFactoria.Library.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
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
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;

    @Column(unique = true)
    private String isbn;
    private String cover_url;
    private String description;
    private Boolean isAvailable;

    @ManyToMany
    @JoinTable(
            name = "book_authors",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    private List<Author> authors;


    @ManyToMany
    @JoinTable(
            name = "book_genres",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    private List<Genre> genres;

    public Book(@NotEmpty(message = "Title is required.") @Size(min = 2, max = 20, message = "Title must be at least 2 characters and max 20 characters.") String title, List<Author> authors, @NotEmpty(message = "ISBN is required.") @Size(min = 13, max = 13, message = "ISBN must be 13 characters.") String isbn, String cover_url, String description, List<Genre> gernes) {
    }
}
