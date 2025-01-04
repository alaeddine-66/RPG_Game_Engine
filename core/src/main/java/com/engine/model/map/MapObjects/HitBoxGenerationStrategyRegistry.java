package com.engine.model.map.MapObjects;

import com.badlogic.gdx.maps.MapObject;

import java.util.HashMap;
import java.util.Map;

public class HitBoxGenerationStrategyRegistry {
    private  final Map<Class<? extends MapObject>, HitBoxGenerationStrategy> registry;

    public HitBoxGenerationStrategyRegistry(){
        registry = new HashMap<>();
    }

    public void registerStrategy(Class<? extends MapObject> mapObjectType, HitBoxGenerationStrategy strategy) {
        registry.put(mapObjectType, strategy);
    }

    public HitBoxGenerationStrategy getStrategy(Class<? extends MapObject> mapObjectType) {
        return registry.get(mapObjectType);
    }
}
