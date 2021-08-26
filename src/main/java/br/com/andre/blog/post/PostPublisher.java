package br.com.andre.blog.post;

import br.com.andre.blog.post.ports.notifier.Notifier;
import br.com.andre.blog.post.ports.publisher.Publisher;
import br.com.andre.blog.post.ports.sender.MessageSender;
import br.com.andre.blog.post.models.Post;

import java.util.List;

public class PostPublisher {
    private final MessageSender<Post> messageSender;
    private final List<Publisher<Post>> publishers;
    private final List<Notifier<Post>> notifiers;

    public PostPublisher(
            final MessageSender messageSender,
            final List<Publisher<Post>> publishers,
            final List<Notifier<Post>> notifiers) {

        this.messageSender = messageSender;
        this.publishers = publishers;
        this.notifiers = notifiers;
    }

    public void publishCreationOf(final Post post) {
        messageSender.sendMessageForCreated(post);
        publishers.forEach(publisher -> publisher.publish(post));
        notifiers.forEach(notifier -> notifier.notifyAboutCreationOf(post));
    }

    public void publishRetrievalOf(final Post post) {
        messageSender.sendMessageForRetrieved(post);
    }
}
