package com.engine.model.map;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

public class MapLoader {
    private final TiledMap map;
    private final TileLayerManager tileLayerManager;
    private final ObjectLayerManager objectLayerManager;

    public MapLoader(String mapFilePath) {
        TmxMapLoader mapLoader = new TmxMapLoader();
        map = mapLoader.load(mapFilePath);
        if (map == null) {
            throw new IllegalArgumentException("Impossible de charger la carte : " + mapFilePath);
        }
        this.tileLayerManager = new TileLayerManager(map);
        this.objectLayerManager = new ObjectLayerManager(map, tileLayerManager.getTileWidth(), tileLayerManager.getTileHeight());
    }

    public TiledMap getMap() {
        return map;
    }

    public TileLayerManager getTileLayerManager() {
        return tileLayerManager;
    }

    public ObjectLayerManager getObjectLayerManager() {
        return objectLayerManager;
    }

    public void dispose() {
        if (map != null) {
            map.dispose();
        }
    }

}
