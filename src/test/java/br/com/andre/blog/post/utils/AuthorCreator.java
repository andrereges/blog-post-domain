package br.com.andre.blog.post.utils;

import br.com.andre.blog.post.models.Author;

import java.util.Optional;
import java.util.Set;

public class AuthorCreator {
    public static Set<Author> createOne() {
        return Set.of(new Author("1", "Author 01"));
    }

    public static Set<Author> createTwo() {
        return Set.of(
            new Author("1", "Author 01"),
            new Author("2", "Author 02")
        );
    }

    public static Author create(String id, String name) {
        return new Author(id, name);
    }

    public static Set<Author> createMany(Author ... authors) {
        return Set.of(authors);
    }

    public static Optional<Set<Author>> createOneOptional() {
        return Optional.of(createOne());
    }
}
