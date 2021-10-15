package com.mjaron.mjregistry.core;

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
    IRawProperty registerRawProperty(final String name);
}
