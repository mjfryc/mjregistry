package com.mjaron.mjregistry;

import com.mjaron.mjregistry.core.IStorage;

import java.util.Map;
import java.util.TreeMap;

/**
 * Stores all data in RAM only.
 * Usually for test purposes.
 */
public class MemoryStorage implements IStorage {

    @Override
    public boolean hasValue(final String name) {
        return values.containsKey(name);
    }

    @Override
    public void cleanValue(final String name) {
        values.remove(name);
    }

    @Override
    public String getValue(final String name) {
        return values.get(name);
    }

    @Override
    public void setValue(final String name, final String what) {
        values.put(name, what);
    }

    private final Map<String, String> values = new TreeMap<>();
}
