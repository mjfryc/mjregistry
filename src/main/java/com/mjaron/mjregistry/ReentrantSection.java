package com.mjaron.mjregistry;

import com.mjaron.mjregistry.core.ICriticalSection;

import java.util.concurrent.Callable;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantSection implements ICriticalSection {

    private final ReentrantLock locker = new ReentrantLock();

    @Override
    public void lock() {
        locker.lock();
    }

    @Override
    public void unlock() {
        locker.unlock();
    }

    @Override
    public void withLock(Runnable func) {
        ICriticalSection.doWithLock(this, func);
    }

    @Override
    public <V> V returnWithLock(Callable<V> func) {
        return ICriticalSection.doReturnWithLock(this, func);
    }
}
