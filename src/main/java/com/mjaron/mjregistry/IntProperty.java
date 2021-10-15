package com.mjaron.mjregistry;

import com.mjaron.mjregistry.core.CommonProperty;
import com.mjaron.mjregistry.core.IRegistry;
import com.mjaron.mjregistry.core.ISerializer;

public class IntProperty extends CommonProperty<Integer, IntProperty> {

    private static class IntSerializer implements ISerializer<Integer> {

        @Override
        public String toStr(Integer what) {
            return what.toString();
        }

        @Override
        public Integer fromStr(String whatStr) {
            return Integer.valueOf(whatStr);
        }

        @Override
        public String toStrOrNull(Integer what) {
            if (what == null) return null;
            return this.toStr(what);
        }

        @Override
        public Integer fromStrOrNull(String whatStr) {
            if (whatStr == null) return null;
            return this.fromStr(whatStr);
        }
    }

    public IntProperty(IRegistry registryInstance, String name) {
        super(registryInstance, name, new IntSerializer());
    }
}
