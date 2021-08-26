package br.com.andre.blog.post.models;

import br.com.andre.blog.post.utils.AuthorCreator;
import br.com.andre.blog.post.utils.PostCreator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

public class PostTest {

    @Test
    @DisplayName("Post created with success")
    public void testPostWithSuccess() {
        Post post = PostCreator.createOne();

        Assertions.assertEquals("1", post.getId());
        Assertions.assertEquals("My first post", post.getTitle());
        Assertions.assertEquals("This is my content", post.getContent());
        Assertions.assertEquals("1", post.getAuthors().stream().findFirst().get().getId());
        Assertions.assertEquals("Author 01", post.getAuthors().stream().findFirst().get().getName());
    }

    @Test
    @DisplayName("Create post title empty and null throws exception")
    public void testCreatePostTitleThrowsException() {
        IllegalArgumentException ex = Assertions.assertThrows(IllegalArgumentException.class, () ->
                PostCreator.create(null, "", "This content", AuthorCreator.createOne()));

        IllegalArgumentException ex2 = Assertions.assertThrows(IllegalArgumentException.class, () ->
                PostCreator.create(null, null, "This content", AuthorCreator.createOne()));

        Assertions.assertTrue(
            ex.getMessage().toString().equals("Title is required") &&
                  ex2.getMessage().toString().equals("Title is required"));
    }

    @Test
    @DisplayName("Create post content empty and null throws exception")
    public void testCreatePostContentThrowsException() {
        IllegalArgumentException ex = Assertions.assertThrows(IllegalArgumentException.class, () ->
                PostCreator.create(null, "My title", "", AuthorCreator.createOne()));

        IllegalArgumentException ex2 = Assertions.assertThrows(IllegalArgumentException.class, () ->
                PostCreator.create(null, "My title", null, AuthorCreator.createOne()));

        Assertions.assertTrue(
                ex.getMessage().toString().equals("Content is required") &&
                        ex2.getMessage().toString().equals("Content is required"));
    }

    @Test
    @DisplayName("Create post authors empty and null throws exception")
    public void testCreatePostAuthorsThrowsException() {
        IllegalArgumentException ex = Assertions.assertThrows(IllegalArgumentException.class, () ->
                PostCreator.create(null, "My title", "This content", null));

        IllegalArgumentException ex2 = Assertions.assertThrows(IllegalArgumentException.class, () ->
                PostCreator.create(null, "My title", "This content", Set.of()));

        Assertions.assertTrue(
                ex.getMessage().toString().equals("At least one author is required") &&
                        ex2.getMessage().toString().equals("At least one author is required"));
    }

    @Test
    @DisplayName("Default post status created")
    public void testDefaultPostStatusCreatedWithSuccess() {
        Post post = PostCreator.createOne();
        Assertions.assertEquals(PostStatus.CREATED, post.getStatus());
    }

    @Test
    @DisplayName("Change post status with success")
    public void testChangePostStatusWithSuccess() {
        Post post = PostCreator.createOne();

        post.changeStatus(PostStatus.REJECTED);
        Assertions.assertEquals(PostStatus.REJECTED, post.getStatus());

        post.changeStatus(PostStatus.PUBLISHED);
        Assertions.assertEquals(PostStatus.PUBLISHED, post.getStatus());
    }

    @Test
    @DisplayName("Post to string with success")
    public void testPostToStringWithSuccess() {
        Post post = PostCreator.createOne();
        String expected = String.format("Post(id=%s, title=%s)", post.getId(), post.getTitle());
        Assertions.assertEquals(expected, post.toString());
    }
}
