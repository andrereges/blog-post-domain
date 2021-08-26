package br.com.andre.blog.post.ports.repository;

import br.com.andre.blog.post.utils.PageInfo;
import br.com.andre.blog.post.utils.Pagination;

import java.util.Optional;
import java.util.Set;

public interface PostRepository<Post, String> {
    Set<Post> findAll();
    PageInfo<Post> findAll(Pagination pagination);
    Set<Post> findByTitleAndContentWithLike(String title, String content);
    Set<Post> findByAuthorNameWithLike(String authorName);
    Optional<Post> find(String id);
    void save(Post entity);
    void update(Post entity);
    void delete(String id);
}
