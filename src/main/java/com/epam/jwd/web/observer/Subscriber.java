package com.epam.jwd.web.observer;

public interface Subscriber<T> {
    void update(T item);
}
