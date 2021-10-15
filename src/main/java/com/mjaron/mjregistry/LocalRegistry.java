package com.mjaron.mjregistry;

import com.mjaron.mjregistry.core.*;

/**
 * Represents a basic registry created inside an application.
 */
public class LocalRegistry implements IRegistry {

    /**
     * Just for convenient usage. User can call LocalRegistry constructor directly.
     * @return IRegistry instance.
     */
    public static IRegistry createMemoryRegistry() {
        return new LocalRegistry(new MemoryStorage(), new ReentrantSection());
    }

    private final IStorage storage;
    private final ICriticalSection criticalSection;

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
    public IRawProperty registerRawProperty(String name) {
        return new LocalRawProperty(this, name);
    }
}
