package com.epam.jwd.web.servlet.command;

/**
 * Interface for auxiliary objects which return path to page
 * where we should forward to and check if we should be redirected
 *
 * @author Ilia Eriomkin
 */
public interface ResponseContext {
    /**
     * If <tt>isRedirect</tt> returns <tt>false</tt> then this method has to return path
     * to view page
     *
     * @return path to page where we should forward to
     */
    String getPage();

    /**
     * Returns <tt>true</tt> if we should be redirected.
     *
     * @return <tt>true</tt> if we should be redirected.
     */
    boolean isRedirect();
}
