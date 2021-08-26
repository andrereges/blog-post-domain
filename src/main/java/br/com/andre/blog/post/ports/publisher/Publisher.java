package br.com.andre.blog.post.ports.publisher;

public interface Publisher<T> {
    void publish(T entity);
}
