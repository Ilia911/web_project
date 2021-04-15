package com.epam.jwd.web.observer;

/**
 * This interface and {@link Subscriber} implement pattern Observer (weak connection between objects).
 * <tt>T</tt> is an object that will be sent to {@link Subscriber}
 *
 * @param <T> object that will be sent to a {@link Subscriber}
 * @author Ilia Eriomkin
 */
public interface Publisher<T> {

    /**
     * Subscribe {@link Subscriber} object to {@link Publisher} object.
     *
     * @param subscriber an object that will be subscribed.
     */
    void subscribe(Subscriber<? super T> subscriber);
}
