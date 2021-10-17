package com.mjaron.mjregistry.core;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Implementation of property which is meant to store values in unified format.
 */
public class LocalRawProperty implements IRawProperty {

    private final IRegistry mRegistry;
    private final String mName;
    private final IValueFormatValidator mValidator;
    private String mDefaultValue = null;
    private boolean mIsEnum = false;
    private List<String> enumList = null;
    private String mDescription = "";

    public LocalRawProperty(final IRegistry mRegistry, final String name, final IValueFormatValidator validator) {
        this.mRegistry = mRegistry;
        this.mName = name;
        this.mValidator = validator;
    }

    @NotNull
    @Override
    public String getName() {
        return mName;
    }

    @Override
    public boolean hasValue() {
        return mRegistry.getCriticalSection().returnWithLock(() -> mRegistry.getStorage().hasValue(mName));
    }

    @Override
    public String getOrNull() {
        return mRegistry.getCriticalSection().returnWithLock(() -> {
            final String rawValue = mRegistry.getStorage().getValue(mName);
            if (rawValue != null) {
                return rawValue;
            }
            return mDefaultValue;
        });
    }

    @NotNull
    @Override
    public String get() {
        final String rawValue = this.getOrNull();
        if (rawValue == null) {
            throw new NullPointerException("Cannot get value of: [" + mName + "]: There is no value is storage nor default value is set.");
        }
        return rawValue;
    }

    @Override
    public Object set(final String value) {
        this.validateValueRange(value);
        return mRegistry.getCriticalSection().returnWithLock(() -> {
            mRegistry.getStorage().setValue(mName, value);
            return null;
        });
    }

    @Nullable
    @Override
    public String getDefault() {
        return mDefaultValue;
    }

    @NotNull
    @Override
    public Object setDefault(String defaultValue) {
        this.validateValueRange(defaultValue);
        this.mDefaultValue = defaultValue;
        return this;
    }

    @Override
    public boolean isEnum() {
        return mIsEnum;
    }

    @NotNull
    @Override
    public Object setEnum(boolean enumState) {
        mIsEnum = enumState;
        return this;
    }

    @NotNull
    @Override
    public Object addEnum(String enumValue) {
        if (enumList == null) {
            enumList = new ArrayList<>();
        }
        enumList.add(enumValue);
        return this;
    }

    @NotNull
    @Override
    public List<String> getEnums() {
        if (enumList == null) {
            return Collections.emptyList();
        }
        return enumList;
    }

    @Nullable
    @Override
    public String getDesc() {
        return mDescription;
    }

    @NotNull
    @Override
    public Object setDesc(String desc) {
        mDescription = desc;
        return this;
    }

    @NotNull
    @Override
    public IValueFormatValidator getFormatValidator() {
        return null;
    }

    @Override
    public void validateValueRange(String rawValue) {
        if (mIsEnum) {
            if ((enumList == null || enumList.isEmpty()) && rawValue != null) {
                throw new RuntimeException("Given value is not valid: [" + rawValue + "]: valid enumerations list is empty.");
            }
            if (enumList != null && rawValue != null) {
                for (final String entry : enumList) {
                    if (entry.equals(rawValue)) {
                        return;
                    }
                }
                throw  new RuntimeException("Given value is not valid: [" + rawValue + "]: Enum values valid only: " + enumList);
            }
        }
    }
}
