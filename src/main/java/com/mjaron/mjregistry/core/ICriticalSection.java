package com.mjaron.mjregistry.core;

import java.util.concurrent.Callable;

public interface ICriticalSection {

    void lock();
    void unlock();

    void withLock(Runnable func);

    <V> V returnWithLock(Callable<V> func);

    static void doWithLock(final ICriticalSection section, final Runnable func) {
        try {
            section.lock();
            func.run();
        } catch (final Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Critical section exception.", e);
        } finally {
            section.unlock();
        }
    }

    static <V> V doReturnWithLock(final ICriticalSection section, final Callable<V> func) {
        try {
            section.lock();
            return func.call();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Critical section exception.", e);
        } finally {
            section.unlock();
        }
    }

}
