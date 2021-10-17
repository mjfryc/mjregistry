package com.mjaron.mjregistry.core;

import java.util.Collection;
import java.util.List;

/**
 * Generic container of properties.
 */
public interface IRegistry {

    /**
     * Used internally by API.
     * @return Critical section used to atomically perform operations inside a whole registry.
     */
    ICriticalSection getCriticalSection();

    /**
     * Used internally by API.
     * @return Storage, where whole registry stores properties data.
     */
    IStorage getStorage();

    /**
     * Used internally by API.
     * Initializes raw property which allows read / write operations.
     * @param name Name of created property.
     * @return Instance of IRawProperty.
     */
    IRawProperty registerRawProperty(final String name, IValueFormatValidator validator);

    /**
     * @return List of property names available in this registry.
     */
    Collection<String> listProperties();

    /**
     * @param name Property name.
     * @return Property instance.
     */
    IRawProperty getProperty(final String name);
}
