package com.mjaron.mjregistry.core;

/**
 * Converts properties from / to string.
 */
public interface ISerializer<T> {

    String toStr(final T what);

    T fromStr(final String whatStr);

    String toStrOrNull(final T what);

    T fromStrOrNull(final String whatStr);

}
