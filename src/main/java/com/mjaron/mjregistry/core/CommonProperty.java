package com.mjaron.mjregistry.core;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * Used to simplify implementation of common property types, e.g. String, Int, Boolean.
 *
 * @param <T>     Type of value stored by property.
 * @param <ThisT> Type of derived property object which extends `CommonProperty` class.
 */
public class CommonProperty<T, ThisT> implements IProperty<T, ThisT> {

    private final IRawProperty rawProperty;
    private final ISerializer<T> serializer;

    private ThisT thisType() {
        //noinspection unchecked
        return (ThisT) this;
    }

    public CommonProperty(final IRawProperty rawProperty, final ISerializer<T> serializer) {
        this.rawProperty = rawProperty;
        this.serializer = serializer;
    }

    public CommonProperty(final IRegistry registryInstance, final String name, final ISerializer<T> serializer) {
        this(registryInstance.registerRawProperty(name), serializer);
    }

    @NotNull
    @Override
    public String getName() {
        return rawProperty.getName();
    }

    @Override
    public IRawProperty getRawProperty() {
        return rawProperty;
    }

    @Override
    public boolean hasValue() {
        return rawProperty.hasValue();
    }

    @NotNull
    @Override
    public T get() {
        return serializer.fromStr(rawProperty.get());
    }

    @Nullable
    @Override
    public T getOrNull() {
        return serializer.fromStrOrNull(rawProperty.getOrNull());
    }

    @Override
    public ThisT set(T value) {
        rawProperty.set(serializer.toStrOrNull(value));
        return thisType();
    }

    @Override
    public T getDefault() {
        return serializer.fromStrOrNull(rawProperty.getDefault());
    }

    @NotNull
    @Override
    public ThisT setDefault(T defaultValue) {
        rawProperty.setDefault(serializer.toStrOrNull(defaultValue));
        return thisType();
    }

    @Override
    public boolean isEnum() {
        return rawProperty.isEnum();
    }

    @NotNull
    @Override
    public ThisT setEnum(boolean enumState) {
        rawProperty.setEnum(enumState);
        return thisType();
    }

    @NotNull
    @Override
    public ThisT addEnum(T enumValue) {
        rawProperty.addEnum(serializer.toStr(enumValue));
        return thisType();
    }

    @NotNull
    @Override
    public List<T> getEnums() {
        final List<String> rawEnums = rawProperty.getEnums();
        List<T> typedEnums = new ArrayList<>(rawEnums.size());
        for (final String rawEntry : rawEnums) {
            typedEnums.add(serializer.fromStr(rawEntry));
        }
        return typedEnums;
    }

    @Override
    public String getDesc() {
        return rawProperty.getDesc();
    }

    @NotNull
    @Override
    public ThisT setDesc(String desc) {
        rawProperty.setDesc(desc);
        return thisType();
    }
}
