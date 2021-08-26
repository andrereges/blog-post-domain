package br.com.andre.blog.post.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@EqualsAndHashCode(of = {"id", "title"})
@ToString(of = {"id", "title"})
public class Post {
    private String id;
    private String title;
    private String content;
    private Set<Author> authors;
    private LocalDateTime createdAt;
    private PostStatus status;

    public Post(String id, String title, String content, Set<Author> authors) {
        validateConstructor(title, content, authors);

        this.id = id;
        this.title = title;
        this.content = content;
        this.authors = authors;
        this.status = PostStatus.CREATED;
        this.createdAt = LocalDateTime.now();
    }

    public Post(String id, String title, String content, Set<Author> authors, PostStatus status, LocalDateTime createdAt) {
        validateConstructor(title, content, authors);

        this.id = id;
        this.title = title;
        this.content = content;
        this.authors = authors;
        this.status = status;
        this.createdAt = createdAt;
    }

    private void validateConstructor(String title, String content, Set<Author> authors) {
        if (title == null || title != null && title.isEmpty())
            throw new IllegalArgumentException("Title is required");

        if (content == null || content != null && content.isEmpty())
            throw new IllegalArgumentException("Content is required");

        if (authors == null || authors != null && authors.isEmpty())
            throw new IllegalArgumentException("At least one author is required");
    }

    public void changeStatus(PostStatus status) {
        this.status = status;
    }
}
