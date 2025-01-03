package com.engine.model.resource;

import com.engine.model.entity.enemy.factory.AbstractEnemyBuilder;
import com.engine.model.entity.enemy.factory.EnemyFactoryNotFoundException;

import java.util.HashMap;
import java.util.Map;

public class FactoryProvider<T> implements IFactoryProvider<T> {
    private final Map<String, T> factoryMap = new HashMap<>();

    @Override
    public T getFactory(String id) {
        if (!factoryMap.containsKey(id)){
            throw new EnemyFactoryNotFoundException("Factory non trouvée pour l'ID: " + id);
        }
        return factoryMap.get(id);
    }

    @Override
    public void registerFactory(String id, T factory) {
        if (id == null || factory == null) {
            throw new IllegalArgumentException("L'ID et la factory ne peuvent pas être nuls.");
        }
        factoryMap.put(id, factory);
    }
}
