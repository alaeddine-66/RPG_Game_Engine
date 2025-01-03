package com.engine.model.resource;

public interface IFactoryProvider<T> {
    T getFactory(String id);
    void registerFactory(String id, T factory);
}

