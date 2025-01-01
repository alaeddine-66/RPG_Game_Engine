package com.engine.model.map;

import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

public class TileLayerManager {
    private final int row;
    private final int col;
    private final int tileWidth;
    private final int tileHeight;

    public TileLayerManager(TiledMap map) {
        int maxTileWidth = 0;
        int maxTileHeight = 0;
        int mapRows = 0;
        int mapCols = 0;

        for (MapLayer layer : map.getLayers()) {
            if (layer instanceof TiledMapTileLayer) {
                TiledMapTileLayer tiledLayer = (TiledMapTileLayer) layer;
                mapRows = Math.max(mapRows, tiledLayer.getHeight());
                mapCols = Math.max(mapCols, tiledLayer.getWidth());
                maxTileWidth = Math.max(maxTileWidth, tiledLayer.getTileWidth());
                maxTileHeight = Math.max(maxTileHeight, tiledLayer.getTileHeight());
            }
        }

        if (mapRows == 0 || mapCols == 0) {
            throw new IllegalStateException("Aucune couche de tuiles valide trouvée.");
        }

        this.row = mapRows;
        this.col = mapCols;
        this.tileWidth = maxTileWidth;
        this.tileHeight = maxTileHeight;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public int getTileWidth() {
        return tileWidth;
    }

    public int getTileHeight() {
        return tileHeight;
    }
}
