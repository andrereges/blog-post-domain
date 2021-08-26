package br.com.andre.blog.post.services;

import br.com.andre.blog.post.models.Post;
import br.com.andre.blog.post.utils.PageInfo;
import br.com.andre.blog.post.utils.Pagination;

import java.util.Set;

public interface PostService {

    Set<Post> findAll();

    PageInfo<Post> findAll(Pagination pagination);

    Post find(String id);

    void create(Post entity);

    void update(Post entity);

    void delete(String id);

}
