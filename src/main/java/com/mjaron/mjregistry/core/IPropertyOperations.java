package com.mjaron.mjregistry.core;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * Generic definition of any property operations for custom value type.
 */
@SuppressWarnings("UnusedReturnValue")
public interface IPropertyOperations<T, ThisT> {

    /**
     * @return Unique name or tag of property.
     */
    @NotNull
    String getName();

    /**
     * @return True if non-null value is present.
     */
    boolean hasValue();

    /**
     * @return Property value or default value.
     * @throws NullPointerException when there is no value nor default value.
     */
    @NotNull
    T get();

    /**
     * Returns value or null if it is missing.
     * @return Value or default value, else null.
     */
    @Nullable
    T getOrNull();

    /**
     * Sets property value if @param value is not null.
     * @param value New value or null to remove value.
     * @return this
     */
    ThisT set(final @Nullable T value);

    /**
     * @return Default value or null if not set.
     */
    @Nullable
    T getDefault();

    /**
     * @param defaultValue It should be returned if storage doesn't contain a value.
     * @return this
     */
    @NotNull
    ThisT setDefault(final T defaultValue);

    /**
     * @return True if valid values are restricted to registered enums only.
     */
    boolean isEnum();

    /**
     * @param enumState If true, valid values are restricted to registered enums only.
     * @return this
     */
    @NotNull
    ThisT setEnum(final boolean enumState);

    /**
     * Allows to register new enum value.
     * @param enumValue Added enum value.
     * @return this
     */
    @NotNull
    ThisT addEnum(final T enumValue);

    /**
     * @return All enums.
     */
    @NotNull
    List<T> getEnums();

    /**
     * @return Human readable property description.
     */
    @Nullable
    String getDesc();

    /**
     * Sets human readable property description.
     * @param desc Human readable property description.
     * @return this.
     */
    @NotNull
    ThisT setDesc(final String desc);
}
