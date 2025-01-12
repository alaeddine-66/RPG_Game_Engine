package com.engine.model.map;

import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

public class TileLayerManager {
    private int row;
    private int col;
    private int tileWidth;
    private int tileHeight;
    private TiledMap map;

    public TileLayerManager(TiledMap map) {
        this.map = map;

        loadLayer(map);

    }

    private void loadLayer(TiledMap map) {
        for (MapLayer layer : map.getLayers()) {
            if (layer instanceof TiledMapTileLayer) {
                TiledMapTileLayer tiledLayer = (TiledMapTileLayer) layer;
                this.row = Math.max(row, tiledLayer.getHeight());
                this.col = Math.max(col, tiledLayer.getWidth());
                this.tileWidth = Math.max(tileWidth, tiledLayer.getTileWidth());
                this.tileHeight = Math.max(tileHeight, tiledLayer.getTileHeight());
            }
        }

        if (row == 0 || col == 0) {
            throw new IllegalStateException("Aucune couche de tuiles valide trouvée.");
        }
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
