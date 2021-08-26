package br.com.andre.blog.post.services.impl;

import br.com.andre.blog.post.exceptions.BadRequestException;
import br.com.andre.blog.post.models.Author;
import br.com.andre.blog.post.models.Post;
import br.com.andre.blog.post.ports.publisher.Publisher;
import br.com.andre.blog.post.ports.repository.AuthorRepository;
import br.com.andre.blog.post.ports.repository.PostRepository;
import br.com.andre.blog.post.services.PostService;
import br.com.andre.blog.post.utils.PageInfo;
import br.com.andre.blog.post.utils.Pagination;

import java.util.Set;

public class PostServiceImpl implements PostService {
    private final PostRepository<Post, String> postRepository;
    private final AuthorRepository<Author, String> authorRepository;
    private final Publisher<Post> eventPublisher;

    public PostServiceImpl(
            PostRepository<Post, String> postRepository,
            AuthorRepository<Author, String> authorRepository,
            Publisher eventPublisher) {
        this.postRepository = postRepository;
        this.authorRepository = authorRepository;
        this.eventPublisher = eventPublisher;
    }

    @Override
    public Set<Post> findAll() {
        return postRepository.findAll();
    }

    @Override
    public PageInfo<Post> findAll(Pagination pagination) {
        return postRepository.findAll(pagination);
    }

    @Override
    public Post find(String id) {
        return postRepository.find(id)
                .orElseThrow(() -> new BadRequestException("Post", id));
    }

    @Override
    public void create(Post entity) {
        final Set<Author> authors = authorRepository.findIn(entity.getAuthors());

        if (authors.isEmpty())
            throw new BadRequestException("At least one author is required");

        if (!(authors.size() == entity.getAuthors().size()))
            throw new BadRequestException("One or more authors not found");

        postRepository.save(entity);
    }

    @Override
    public void update(Post entity) {
        postRepository.update(entity);
    }

    @Override
    public void delete(String id) {
        if (find(id) != null)
            postRepository.delete(id);
    }

}
