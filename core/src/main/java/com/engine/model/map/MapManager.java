package com.engine.model.map;


public class MapManager {
    private MapLoader currentMapLoader;
    private String currentMapPath;

    public MapManager(String initialMapPath) {
        loadMap(initialMapPath);
    }

    public void loadMap(String mapPath) {
        if (currentMapLoader != null) {
            currentMapLoader.dispose();
        }
        currentMapPath = mapPath;
        currentMapLoader = new MapLoader(mapPath);
    }

    public MapLoader getCurrentMapLoader() {
        return currentMapLoader;
    }

    public String getCurrentMapPath() {
        return currentMapPath;
    }

    public void transitionToMap(String nextMapPath) {
        loadMap(nextMapPath);
        // Vous pouvez ajouter ici une logique supplémentaire, comme initialiser des objets pour la nouvelle carte.
    }

    public void dispose() {
        if (currentMapLoader != null) {
            currentMapLoader.dispose();
        }
    }
}
