package com.mjaron.mjregistry.core;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface IProperty<T, ThisT> extends IPropertyOperations<T, ThisT> {

    /**
     * @return Object responsible for storing value.
     */
    @NotNull
    IRawProperty getRawProperty();
}
