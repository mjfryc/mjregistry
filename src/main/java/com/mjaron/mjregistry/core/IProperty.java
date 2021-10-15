package com.mjaron.mjregistry.core;

public interface IProperty<T, ThisT> extends IPropertyOperations<T, ThisT> {

    /**
     * @return Object responsible for storing value.
     */
    IRawProperty getRawProperty();
}
