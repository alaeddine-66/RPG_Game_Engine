package com.engine.model.map;

import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;
import java.util.List;

public class MapLoader {
    private final TiledMap map;
    private int row;
    private int col;
    private int tileWidth = 0;
    private int tileHeight = 0;
    private final ArrayList<Rectangle> collisionObjects;
    private final int[][] objectCoordinates;
    private List<int[]> allCoordinates;

    public MapLoader(String mapFilePath) {
        TmxMapLoader mapLoader = new TmxMapLoader();
        map = mapLoader.load(mapFilePath); // Chargement du fichier .tmx
        if (map == null) {
            throw new IllegalArgumentException("Impossible de charger la carte : " + mapFilePath);
        }
        initializeTileLayer(); // Initialiser les informations de la couche de tuiles
        collisionObjects = new ArrayList<>();
        objectCoordinates = loadObjectLayer(); // Charger les objets et leurs coordonnées
    }

    private void initializeTileLayer() {

        for (MapLayer layer : map.getLayers()) {
            if (layer instanceof TiledMapTileLayer) {
                TiledMapTileLayer tiledlayer = (TiledMapTileLayer) layer ;
                row = tiledlayer.getHeight(); // Nombre de lignes
                col = tiledlayer.getWidth(); // Nombre de colonnes
                tileWidth = Math.max(tileWidth,tiledlayer.getTileWidth()); // Largeur d'une tuile
                tileHeight = Math.max(tileHeight,tiledlayer.getTileHeight()); // Hauteur d'une tuile
            }
        }
    }

    private MapLayer findObjectLayer() {
        for (MapLayer layer : map.getLayers()) {
            if (layer.getName() != null && layer.getName().toLowerCase().contains("object")) {
                return layer;
            }
        }
        throw new IllegalArgumentException("Aucune couche d'objets trouvée dans la carte.");
    }


    private int[][] loadObjectLayer() {
        MapLayer objectLayer = findObjectLayer();
        allCoordinates = new ArrayList<>(); // Dynamic list to store all coordinates

        for (MapObject mapObject : objectLayer.getObjects()) {
            Rectangle rect = ((RectangleMapObject) mapObject).getRectangle();
            collisionObjects.add(rect);

            // Calculate number of tiles horizontally and vertically for this obstacle
            int tileCountX = (int) Math.ceil(rect.width / tileWidth);
            int tileCountY = (int) Math.ceil(rect.height / tileHeight);

            // Loop through each tile covered by the obstacle
            for (int i = 0; i < tileCountX; i++) {
                for (int j = 0; j < tileCountY; j++) {
                    int colIndex = (int) ((rect.x + i * tileWidth) / tileWidth);
                    int rowIndex = (int) ((rect.y + j * tileHeight) / tileHeight);

                    allCoordinates.add(new int[]{colIndex, rowIndex});
                }
            }
        }

        // Convert List to 2D array
        int[][] coordinates = new int[allCoordinates.size()][2];
        for (int i = 0; i < allCoordinates.size(); i++) {
            coordinates[i] = allCoordinates.get(i);
        }

        return coordinates;
    }

    public TiledMap getMap() {
        return map;
    }

    public List<Rectangle> getCollisionObjects() {
        return new ArrayList<>(collisionObjects); // Retourner une copie pour éviter les modifications
    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }

    public int getTileWidth() {
        return tileWidth;
    }

    public int getTileHeight() {
        return tileHeight;
    }

    public void dispose() {
        if (map != null) {
            map.dispose(); // Libère les ressources liées à la carte
        }
    }
}
