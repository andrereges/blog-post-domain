package br.com.andre.blog.post.ports.repository;

import java.util.Optional;
import java.util.Set;

public interface AuthorRepository<Author, String> {
    Set<Author> findAll();
    Set<Author> findIn(Set<Author> authors);
    Set<Author> findByNameWithLike(String name);
    Optional<Author> find(String id);
    void save(Author entity);
    void update(Author entity);
    void delete(String id);
}
