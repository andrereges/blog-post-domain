package br.com.andre.blog.post.utils;

import br.com.andre.blog.post.models.Author;
import br.com.andre.blog.post.models.Post;

import java.util.Optional;
import java.util.Set;

public class PostCreator {
    public static Post createOne(){
        return create(
            "1",
            "My first post",
            "This is my content",
            AuthorCreator.createOne());
    }

    public static Set<Post> createTwo() {
        return Set.of(
            createOne(),
            create("2", "My second post", "This is my content", AuthorCreator.createOne())
        );
    }

    public static Post create(String id, String title, String content, Set<Author> authors) {
        return new Post(id, title, content, authors);
    }

    public static Optional<Post> createOneOptional() {
        return Optional.of(createOne());
    }

}
