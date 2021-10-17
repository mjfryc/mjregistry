package com.mjaron.mjregistry.core;

/**
 * Converts properties from / to string.
 */
public interface ISerializer<T> extends IValueFormatValidator {

    String toStr(final T what);

    T fromStr(final String whatStr);

    String toStrOrNull(final T what);

    T fromStrOrNull(final String whatStr);

    @Override
    default void validateValueFormat(final String rawValue) {
        final T tv = fromStr(rawValue);
        final String sv = toStr(tv);
        if (!rawValue.equals(sv)) {
            throw new RuntimeException("Validation failed: rawValue: [" + rawValue + "] doesn't match serialized value: [" + sv + "].");
        }
    }
}