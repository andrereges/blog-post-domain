package br.com.andre.blog.post.utils;

import java.util.Collection;

public interface PageInfo<T> {
    Collection<T> content();
    Pagination pagination();

    boolean first();
    boolean last();
    boolean empty();

    int totalPages();
    long totalElements();

}
