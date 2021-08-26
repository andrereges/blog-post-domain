package br.com.andre.blog.post.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode(of = {"id", "name"})
@ToString(of = {"id", "name"})
public class Author {
    private String id;
    private String name;

    public Author(String id, String name) {
        if (name == null || name != null && name.isEmpty())
            throw new IllegalArgumentException("Name is required");

        this.id = id;
        this.name = name;
    }
}
