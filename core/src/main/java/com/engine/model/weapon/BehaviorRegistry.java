package com.engine.model.weapon;

import com.engine.model.data.WeaponData;

import java.util.HashMap;
import java.util.Map;

public class BehaviorRegistry<T> {
    private final Map<String, BehaviorFactory<T>> factories = new HashMap<>();

    public void register(String type, BehaviorFactory<T> factory) {
        factories.put(type, factory);
    }

    public T create(String type, WeaponData data) {
        BehaviorFactory<T> factory = factories.get(type);
        if (factory == null) {
            throw new IllegalArgumentException("Unknown behavior type: " + type);
        }
        return factory.create(data);
    }
}
