package com.mjaron.mjregistry.sample;

import com.mjaron.mjregistry.BooleanProperty;
import com.mjaron.mjregistry.IntProperty;
import com.mjaron.mjregistry.LocalRegistry;
import com.mjaron.mjregistry.StrProperty;
import com.mjaron.mjregistry.core.IRegistry;

public class Sample {

    public static class Registry {
        static IRegistry r = LocalRegistry.createMemoryRegistry();

        // For convenient usage.
        static <V> void withLock(Runnable func) {
            r.getCriticalSection().withLock(func);
        }

        static StrProperty APPLICATION_NAME = new StrProperty(r, "application.name").setDefault("Default application name").setDesc("This is a sample description");
        static IntProperty APPLICATION_ACTIVE_USERS_COUNT = new IntProperty(r, "application.users").setDefault(1).setDesc("This is a sample Integer value.");
        static BooleanProperty APPLICATION_PRODUCTION_MODE = new BooleanProperty(r, "application.production").setDefault(false).setDesc("Defines whether application may be launched by the customer.");
        static StrProperty ACTIVE_USER_NAME = new StrProperty(r, "user.active.name");
        static StrProperty DAYS = new StrProperty(r, "days").setEnum(true).addEnum("MONDAY", "TUESDAY", "WEDNESDAY");

        //... Thousands of other properties here.
    }

    public static void main(String[] args) {

        System.out.println("My application name is: " + Registry.APPLICATION_NAME.get()); // Default value returned.
        Registry.APPLICATION_NAME.set("Sample app");
        System.out.println("My application name is: " + Registry.APPLICATION_NAME.get()); // Sample app value returned.

        Registry.withLock(() -> {
            System.out.println("Doing some atomic registry operations here, so two registry variables will be changed atomically.");
            Registry.APPLICATION_PRODUCTION_MODE.set(true);
            if (Registry.APPLICATION_PRODUCTION_MODE.get()) {
                Registry.APPLICATION_NAME.set("My New Application name!");
                Registry.APPLICATION_ACTIVE_USERS_COUNT.set(7);
            }
        });
        System.out.println("Active users count: " + Registry.APPLICATION_ACTIVE_USERS_COUNT.get());

        if (Registry.ACTIVE_USER_NAME.getOrNull() == null) {
            System.out.println("Property " + Registry.ACTIVE_USER_NAME.getName() + " is not set, also hasValue() returns false: " + Registry.ACTIVE_USER_NAME.hasValue());
        }

        Registry.withLock(() -> {
            System.out.println("All properties:");
            System.out.println(Registry.r.listProperties());
        });

        try {
            Registry.DAYS.set("Wrong day constant.");
        }
        catch (final Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
