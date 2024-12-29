package com.engine.view.entity;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.engine.model.map.MapLoader;

public class MapRenderer {
    private final OrthogonalTiledMapRenderer mapRenderer;
    OrthographicCamera cam;

    public MapRenderer(TiledMap Map, OrthographicCamera cam ) {
        this.cam = cam;
        this.mapRenderer = new OrthogonalTiledMapRenderer(Map , 1); // Échelle des tiles
    }

    public void render() {
        mapRenderer.setView(cam);
        mapRenderer.render(); // Rendu de la carte
    }

}
