package com.epam.jwd.web.servlet.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Interface for auxiliary objects that ensure safe access to data in
 * {@link javax.servlet.ServletContext}, {@link javax.servlet.http.HttpServletRequest},
 * {@link javax.servlet.http.HttpSession} objects
 *
 * @author Ilia Eriomkin
 */

public interface RequestContent {

    /**
     * @return <tt>true</tt> if {@link javax.servlet.http.HttpSession} has to be invalidate
     */
    boolean isInvalidateSession();

    /**
     * Set <tt>true</tt> if {@link javax.servlet.http.HttpSession} has to be invalidate
     */
    void setInvalidateSession(boolean invalidateSession);

    /**
     * Associates the specified value with the specified key in this object.
     * If this object previously contained a mapping for
     * the key for request attribute, the old value is replaced by the specified value.
     *
     * @param key key with which the specified value is to be associated
     * @param attribute value to be associated with the specified key
     */
    void setRequestAttribute(String key, Object attribute);

    /**
     * Associates the specified value with the specified key in this object.
     * If this object previously contained a mapping for
     * the key for session attribute, the old value is replaced by the specified value.
     *
     * @param key key with which the specified value is to be associated
     * @param attribute value to be associated with the specified key
     */
    void setSessionAttribute(String key, Object attribute);

    /**
     * Extract all data from
     * {@link javax.servlet.ServletContext}, {@link javax.servlet.http.HttpServletRequest},
     * {@link javax.servlet.http.HttpSession} objects
     * and insert them into this object
     */
    void extractValues(HttpServletRequest request);

    /**
     * Extract all data from this object into
     * {@link javax.servlet.ServletContext}, {@link javax.servlet.http.HttpServletRequest},
     * {@link javax.servlet.http.HttpSession} objects
     * after executing command
     */
    void insertAttributes(HttpServletRequest request);

    /**
     * Returns the array with request parameters to which the specified key is mapped,
     * or {@code null} if this map contains no mapping for the key.
     *
     * @param key the key whose associated value is to be returned
     * @return the array with request parameters to which the specified key is mapped, or
     * {@code null} if this map contains no mapping for the key
     */
    String[] getRequestParameter(String key);

    /**
     * Returns the value to which the specified key is mapped,
     * or {@code null} if this map contains no mapping for the key.
     *
     * @param key the key whose associated value is to be returned
     * @return the value to which the specified key is mapped, or
     * {@code null} if this map contains no mapping for the key
     */
    Object getRequestAttribute(String key);

    /**
     * Returns the value to which the specified key is mapped,
     * or {@code null} if this map contains no mapping for the key.
     *
     * @param key the key whose associated value is to be returned
     * @return the value to which the specified key is mapped, or
     * {@code null} if this map contains no mapping for the key
     */
    String getContextParameter(String key);

    /**
     * Returns the String to which the specified key is mapped in {@link javax.servlet.ServletContext} object,
     * or {@code null} if {@link javax.servlet.ServletContext} object contains no mapping for the key.
     *
     * @param key the key whose associated value is to be returned
     * @return the value to which the specified key is mapped, or
     * {@code null} if this map contains no mapping for the key
     */
    Object getSessionAttribute(String key);


}
