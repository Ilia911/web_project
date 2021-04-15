package com.epam.jwd.web.observer;

/**
 * This interface and {@link Publisher} implement pattern Observer (weak connection between objects).
 * <tt></tt>> object that will be received by {@link Subscriber}.
 *
 * @param <T> object that will be received by a {@link Subscriber} with data for updating.
 * @author Ilia Eriomkin
 */
public interface Subscriber<T> {
    /**
     * Update <tt>subscriber</tt>.
     *
     * @param item object that contains data for updating.
     */
    void update(T item);
}
