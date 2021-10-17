package com.mjaron.mjregistry.core;

/**
 * Checks if value can be converted to typed (runtime) format.
 */
public interface IValueFormatValidator {

    /**
     * Checks if given raw value can be serialized correctly.
     *
     * @param rawValue Raw value to check.
     * @throws RuntimeException if value is incorrect.
     */
    void validateValueFormat(final String rawValue);
}