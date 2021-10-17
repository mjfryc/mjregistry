package com.mjaron.mjregistry.core;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Defines format of property used to store or send.
 */
public interface IRawProperty extends IPropertyOperations<String, Object> {

    /**
     * Provides format validator which checks if value can be converted to typed (runtime) format.
     *
     * @return Reference to format validator.
     */
    @NotNull
    IValueFormatValidator getFormatValidator();

    /**
     * Checks if given value passes value restrictions.
     * Currently, there is one restriction supported: enumeration.
     *
     * @param rawValue Raw value to check.
     * @throws RuntimeException if given raw value doesn't match range requirements.
     */
    void validateValueRange(final String rawValue);
}