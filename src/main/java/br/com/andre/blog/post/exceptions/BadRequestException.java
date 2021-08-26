package br.com.andre.blog.post.exceptions;

public class BadRequestException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException(String entityName, String id) {
        super(String.format("%s not found. Id %d", entityName, id));
    }

    public BadRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}
