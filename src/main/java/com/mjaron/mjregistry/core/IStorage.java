package com.mjaron.mjregistry.core;

/**
 * Any storage interface.
 */
public interface IStorage {
    /**
     * Tells whether given child has any value set in persistent storage.
     *
     * @return True if given child is stored in persistent storage.
     */
    boolean hasValue(final String name);

    /**
     * Removes value of child property. It is no longer stored in persistent storage.
     * Post condition: hasValue(path) returns false.
     *
     * @param name Route to the child property.
     */
    void cleanValue(final String name);

    /**
     * Provides value related with given child property.
     *
     * @param name Route to the child property.
     * @return Value of child property or throws exception.
     */
    String getValue(final String name);

    /**
     * Creates or changes property's value.
     *
     * @param name Route to the child property.
     * @param what Object which will be set as a property value.
     */
    void setValue(final String name, final String what);
}
