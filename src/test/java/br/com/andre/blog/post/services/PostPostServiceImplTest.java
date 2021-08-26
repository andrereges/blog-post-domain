package br.com.andre.blog.post.services;

import br.com.andre.blog.post.models.Author;
import br.com.andre.blog.post.models.Post;
import br.com.andre.blog.post.ports.repository.AuthorRepository;
import br.com.andre.blog.post.ports.repository.PostRepository;
import br.com.andre.blog.post.services.impl.PostServiceImpl;
import br.com.andre.blog.post.utils.PostCreator;
import br.com.andre.blog.post.utils.PostMemoryDBRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class PostPostServiceImplTest {
    PostRepository<Post, String> postRepository;
    AuthorRepository<Author, String> authorRepository;
    PostRepository<Post, String> mockitoPostRepository;
    PostServiceImpl postServiceImpl;

    public PostPostServiceImplTest() {
        // Banco de dados fake em memória com valores
        // Dublê FAKE - inteligente para lógica, imita muito bem o banco de dados
        postRepository = new PostMemoryDBRepository();
        postRepository.save(PostCreator.createOne());
        postServiceImpl = new PostServiceImpl(postRepository, authorRepository, null);

        // Dublê MOCK - burro, entrada/saída
        mockitoPostRepository = (PostRepository<Post, String>) mock(PostRepository.class);
        when(mockitoPostRepository.find("1")).thenReturn(PostCreator.createOneOptional());
    }

    @Test
    @DisplayName("Find post with success")
    public void testFindPostSuccess() {
        Assertions.assertEquals("1", postRepository.find("1").get().getId());
    }

    @Test
    @DisplayName("Find post in mockito with success")
    public void testFindPostSuccessMockito() {
        Assertions.assertEquals("1", mockitoPostRepository.find("1").get().getId());

        // Espião - funciona como se fosse um assert
        // Verifica se o componente postService interage do jeito que eu quero com o componente repository
        verify(mockitoPostRepository, times(1)).find("1");
    }
}
