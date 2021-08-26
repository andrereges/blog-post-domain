package br.com.andre.blog.post.utils;

import br.com.andre.blog.post.models.Post;
import br.com.andre.blog.post.ports.repository.PostRepository;

import java.util.HashSet;
import java.util.Hashtable;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class PostMemoryDBRepository implements PostRepository<Post, String> {

    private Hashtable<String, Post> dict;

    public PostMemoryDBRepository() {
        dict = new Hashtable<>();
    }

    @Override
    public Set<Post> findAll() {
        Set<Post> posts = new HashSet<>();

        for(String key : dict.keySet().stream().collect(Collectors.toList())) {
            posts.add(dict.get(key));
        }

        return posts;
    }

    @Override
    public PageInfo<Post> findAll(Pagination pagination) {
        return null;
    }

    @Override
    public Set<Post> findByTitleAndContentWithLike(String title, String content) {
        return null;
    }

    @Override
    public Set<Post> findByAuthorNameWithLike(String authorName) {
        return null;
    }

    @Override
    public Optional<Post> find(String id) {
        return Optional.of(dict.get(id));
    }

    @Override
    public void save(Post entity) {
        dict.put(entity.getId(), entity);
    }

    @Override
    public void update(Post entity) {
        Post post = find(entity.getId()).get();
        new Post(
                entity.getId(),
                entity.getTitle(),
                entity.getContent(),
                entity.getAuthors(),
                entity.getStatus(),
                entity.getCreatedAt()
        );

        if (post.getAuthors() != null) {
            post.getAuthors().clear();
            post.getAuthors().addAll(entity.getAuthors());
        }

        dict.replace(post.getId(), post);
    }

    @Override
    public void delete(String id) {
        Optional<Post> optionalPost = find(id);

        if (optionalPost.isPresent())
            dict.remove(id);
    }
}
