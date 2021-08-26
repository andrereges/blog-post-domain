package br.com.andre.blog.post.ports.notifier;

public interface Notifier<T> {
    void notifyAboutCreationOf(T entity);
}
