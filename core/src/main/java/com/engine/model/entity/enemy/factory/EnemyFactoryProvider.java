package com.engine.model.entity.enemy.factory;

import com.engine.model.resource.FactoryProvider;

import java.util.HashMap;
import java.util.Map;

public class EnemyFactoryProvider implements FactoryProvider<AbstractEnemyBuilder> {
    private final Map<String, AbstractEnemyBuilder> factoryMap = new HashMap<>();

    @Override
    public AbstractEnemyBuilder getFactory(String id) {
        if (!factoryMap.containsKey(id)){
            throw new EnemyFactoryNotFoundException("Factory non trouvée pour l'ID: " + id);
        }
        return factoryMap.get(id);
    }

    @Override
    public void registerFactory(String id, AbstractEnemyBuilder factory) {
        if (id == null || factory == null) {
            throw new IllegalArgumentException("L'ID et la factory ne peuvent pas être nuls.");
        }
        factoryMap.put(id, factory);
    }
}
