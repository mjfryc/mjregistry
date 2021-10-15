package com.mjaron.mjregistry;

import com.mjaron.mjregistry.core.CommonProperty;
import com.mjaron.mjregistry.core.IRegistry;
import com.mjaron.mjregistry.core.ISerializer;

public class StrProperty extends CommonProperty<String, StrProperty> {

    private static class StrSerializer implements ISerializer<String> {

        @Override
        public String toStr(String what) {
            return what;
        }

        @Override
        public String fromStr(String whatStr) {
            return whatStr;
        }

        @Override
        public String toStrOrNull(String what) {
            if (what == null) return null;
            return this.toStr(what);
        }

        @Override
        public String fromStrOrNull(String whatStr) {
            if (whatStr == null) return null;
            return this.fromStr(whatStr);
        }
    }

    public StrProperty(final IRegistry registryInstance, final String name) {
        super(registryInstance, name, new StrSerializer());
    }
}
