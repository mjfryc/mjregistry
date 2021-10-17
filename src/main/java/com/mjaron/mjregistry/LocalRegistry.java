package com.mjaron.mjregistry;

import com.mjaron.mjregistry.core.*;

import java.util.*;

/**
 * Represents a basic registry created inside an application.
 */
@SuppressWarnings("ClassCanBeRecord")
public class LocalRegistry implements IRegistry {

    /**
     * Just for convenient usage. User can call LocalRegistry constructor directly.
     *
     * @return IRegistry instance.
     */
    public static IRegistry createMemoryRegistry() {
        return new LocalRegistry(new MemoryStorage(), new ReentrantSection());
    }

    private final IStorage storage;
    private final ICriticalSection criticalSection;
    Map<String, IRawProperty> rawProperties = new TreeMap<>();

    public LocalRegistry(final IStorage storage, final ICriticalSection criticalSection) {
        this.storage = storage;
        this.criticalSection = criticalSection;
    }

    @Override
    public IStorage getStorage() {
        return storage;
    }

    @Override
    public ICriticalSection getCriticalSection() {
        return criticalSection;
    }

    @Override
    public IRawProperty registerRawProperty(String name, IValueFormatValidator validator) {
        final LocalRawProperty rawProperty = new LocalRawProperty(this, name, validator);
        rawProperties.put(name, rawProperty);
        return rawProperty;
    }

    @Override
    public Collection<String> listProperties() {
        return rawProperties.keySet();
    }

    @Override
    public IRawProperty getProperty(String name) {
        return rawProperties.get(name);
    }
}
