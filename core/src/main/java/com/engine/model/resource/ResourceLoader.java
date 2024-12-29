package com.engine.model.resource;

public interface ResourceLoader<T> {
    T load(String path);
}
