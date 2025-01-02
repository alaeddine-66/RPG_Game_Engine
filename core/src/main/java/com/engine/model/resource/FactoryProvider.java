package com.engine.model.resource;

public interface FactoryProvider<T> {
    T getFactory(String id);
    void registerFactory(String id, T factory);
}

