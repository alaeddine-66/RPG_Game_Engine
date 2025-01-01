package com.engine.model.map;

import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;
import java.util.List;

public class ObjectLayerManager {
    private final List<Rectangle> collisionObjects;
    private final List<int[]> objectCoordinates;

    public ObjectLayerManager(TiledMap map, int tileWidth, int tileHeight) {
        this.collisionObjects = new ArrayList<>();
        this.objectCoordinates = new ArrayList<>();
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
                if (mapObject instanceof RectangleMapObject) {
                    Rectangle rect = ((RectangleMapObject) mapObject).getRectangle();
                    collisionObjects.add(rect);

                    int tileCountX = (int) Math.ceil(rect.width / tileWidth);
                    int tileCountY = (int) Math.ceil(rect.height / tileHeight);

                    for (int i = 0; i < tileCountX; i++) {
                        for (int j = 0; j < tileCountY; j++) {
                            int colIndex = (int) Math.floor((rect.x + i * tileWidth) / tileWidth);
                            int rowIndex = (int) Math.floor((rect.y + j * tileHeight) / tileHeight);

                            objectCoordinates.add(new int[]{colIndex, rowIndex});
                        }
                    }
                }
            }
        }
    }


    public List<Rectangle> getCollisionObjects() {
        return new ArrayList<>(collisionObjects);
    }

    public List<int[]> getObjectCoordinates() {
        return new ArrayList<>(objectCoordinates);
    }
}
