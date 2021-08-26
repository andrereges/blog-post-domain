package br.com.andre.blog.post.models;

import br.com.andre.blog.post.utils.AuthorCreator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AuthorTest {

    @Test
    @DisplayName("Author created with success")
    public void testAuthorWithSuccess() {
        Author author = AuthorCreator.createOne().stream().findFirst().get();

        Assertions.assertEquals("1", author.getId());
        Assertions.assertEquals("Author 01", author.getName());
    }

    @Test
    @DisplayName("Create author name empty and null throws exception")
    public void testCreateAuthorNameThrowsException() {
        IllegalArgumentException ex = Assertions.assertThrows(IllegalArgumentException.class, () ->
                AuthorCreator.create(null, ""));

        IllegalArgumentException ex2 = Assertions.assertThrows(IllegalArgumentException.class, () ->
                AuthorCreator.create(null, null));

        Assertions.assertTrue(
            ex.getMessage().toString().equals("Name is required") &&
                  ex2.getMessage().toString().equals("Name is required"));
    }

    @Test
    @DisplayName("Author to string with success")
    public void testAuthorToStringWithSuccess() {
        Author author = AuthorCreator.createOne().stream().findFirst().get();
        String expected = String.format("Author(id=%s, name=%s)", author.getId(), author.getName());
        Assertions.assertEquals(expected, author.toString());
    }
}
