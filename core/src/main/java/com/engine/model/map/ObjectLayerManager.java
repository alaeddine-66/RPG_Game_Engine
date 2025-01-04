package com.engine.model.map;

import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.engine.model.entity.components.hitBox.HitBox;
import com.engine.model.entity.components.hitBox.IHitBox;
import com.engine.model.map.MapObjects.HitBoxGenerationStrategy;
import com.engine.model.map.MapObjects.HitBoxGenerationStrategyRegistry;

import java.util.ArrayList;
import java.util.List;

public class ObjectLayerManager {
    private final List<HitBox> collisionObjects;
    private final List<int[]> objectCoordinates;
    private final HitBoxGenerationStrategyRegistry ObstaclesRegistry;

    public ObjectLayerManager(TiledMap map, int tileWidth, int tileHeight , HitBoxGenerationStrategyRegistry ObstaclesRegistry ) {
        this.collisionObjects = new ArrayList<>();
        this.objectCoordinates = new ArrayList<>();
        this.ObstaclesRegistry = ObstaclesRegistry;
        loadObjectLayer(map, tileWidth, tileHeight);
    }

    private List<MapLayer> findObjectLayer(TiledMap map) {
        List<MapLayer> objectLayers = new ArrayList<>();
        for (MapLayer layer : map.getLayers()) {
            String name = layer.getName();
            if (name != null && name.toLowerCase().contains("object")) {
                objectLayers.add(layer);
            }
        }
        return objectLayers;
    }

    private void loadObjectLayer(TiledMap map, int tileWidth, int tileHeight) {
        List<MapLayer> objectLayers = findObjectLayer(map);

        for (MapLayer objectLayer : objectLayers) {
            for (MapObject mapObject : objectLayer.getObjects()) {

                HitBoxGenerationStrategy strategy = ObstaclesRegistry.getStrategy(mapObject.getClass());
                if (strategy != null) {
                    HitBox hitBox = strategy.createHitBox(mapObject);
                    collisionObjects.add(hitBox);
                    objectCoordinates.addAll(hitBox.getOccupiedTiles(tileWidth,tileHeight));
                }
            }
        }
    }


    public List<HitBox> getCollisionObjects() {
        return new ArrayList<>(collisionObjects);
    }

    public List<int[]> getObjectCoordinates() {
        return new ArrayList<>(objectCoordinates);
    }
}
