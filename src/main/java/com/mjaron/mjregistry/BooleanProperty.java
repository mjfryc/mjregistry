package com.mjaron.mjregistry;

import com.mjaron.mjregistry.core.CommonProperty;
import com.mjaron.mjregistry.core.IRegistry;
import com.mjaron.mjregistry.core.ISerializer;

public class BooleanProperty extends CommonProperty<Boolean, BooleanProperty> {

    private static class BooleanSerializer implements ISerializer<Boolean> {

        @Override
        public String toStr(Boolean what) {
            return what.toString();
        }

        @Override
        public Boolean fromStr(String whatStr) {
            return Boolean.valueOf(whatStr);
        }

        @Override
        public String toStrOrNull(Boolean what) {
            if (what == null) return null;
            return this.toStr(what);
        }

        @Override
        public Boolean fromStrOrNull(String whatStr) {
            if (whatStr == null) return null;
            return this.fromStr(whatStr);
        }
    }

    public BooleanProperty(IRegistry registryInstance, String name) {
        super(registryInstance, name, new BooleanSerializer());
    }
}
