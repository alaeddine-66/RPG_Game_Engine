package com.engine.model.map;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.List;

public class CollisionManager implements IMapCollisionChecker{

    private final List<Rectangle> collisionObjects;
    private final int mapWidth;
    private final int mapHeight;
    private final MapLoader gameMap;

    public CollisionManager(MapLoader gameMap) {
        this.collisionObjects = gameMap.getCollisionObjects();
        this.mapWidth = gameMap.getCol() * gameMap.getTileWidth();
        this.mapHeight = gameMap.getRow() * gameMap.getTileHeight();
        this.gameMap = gameMap;
    }

    @Override
    public int getTileWidth() {
        return gameMap.getCol();
    }

    @Override
    public int getTileHeight() {
        return gameMap.getRow();
    }

    @Override
    public int getMapWidth() {
        return mapWidth;
    }

    @Override
    public int getMapHeight() {
        return mapHeight;
    }

    @Override
    public boolean isCollision(Rectangle rect1, Rectangle rect2) {
        return rect2.overlaps(rect1);
    }


    /**
     * Vérifie si un rectangle donné entre en collision avec des objets de la carte.
     *
     * @param rect Rectangle représentant l'entité.
     * @return true si une collision est détectée, false sinon.
     */
    @Override
    public boolean checkCollisionWithObjects(Rectangle rect) {
        for (Rectangle object : collisionObjects) {
            if (isCollision(rect, object)) {
                return true; // Collision détectée
            }
        }
        return false;
    }

    @Override
    public boolean isOutOfBounds(Rectangle rect) {
        return rect.x < 0 || rect.x + rect.width > mapWidth ||
            rect.y < 0 || rect.y + rect.height > mapHeight;
    }

    @Override
    public void resolveCollisions(Vector2 position, Vector2 direction, int width, int height) {
        Rectangle bbox = new Rectangle(position.x, position.y, width, height);
        while (checkCollisionWithObjects(bbox)) {
            position.x -= direction.x;
            position.y -= direction.y;
            bbox.setPosition(position.x, position.y);
        }

        clampPosition(position, width, height);
    }

    public void clampPosition(Vector2 position, int width, int height) {
        position.x = MathUtils.clamp(position.x, 0, mapWidth - width);
        position.y = MathUtils.clamp(position.y, 0, mapHeight - height);
    }

    // Vérifie si une position est dans une zone restreinte
    @Override
    public boolean isInRestrictedZone(Rectangle bbox) {
        return checkCollisionWithObjects(bbox) || isOutOfBounds(bbox);
    }

}
