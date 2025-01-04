package com.engine.model.map;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.engine.model.entity.components.hitBox.HitBox;

import java.util.List;

public class CollisionManager implements IMapCollisionChecker{

    private final List<HitBox> collisionObjects;
    private final int mapWidth;
    private final int mapHeight;
    private final MapLoader gameMap;

    public CollisionManager(MapLoader gameMap) {
        this.collisionObjects = gameMap.getObjectLayerManager().getCollisionObjects();
        this.mapWidth = gameMap.getTileLayerManager().getCol() * gameMap.getTileLayerManager().getTileWidth();
        this.mapHeight = gameMap.getTileLayerManager().getRow() * gameMap.getTileLayerManager().getTileHeight();
        this.gameMap = gameMap;
    }

    @Override
    public int getTileWidth() {
        return gameMap.getTileLayerManager().getCol();
    }

    @Override
    public int getTileHeight() {
        return gameMap.getTileLayerManager().getRow();
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
    public boolean isCollision(HitBox hitBox1, HitBox hitBox2) {
        return hitBox1.intersects(hitBox2);
    }

    /**
     * Vérifie si un rectangle donné entre en collision avec des objets de la carte.
     *
     * @param rect Rectangle représentant l'entité.
     * @return true si une collision est détectée, false sinon.
     */
    @Override
    public boolean checkCollisionWithObjects(HitBox rect) {
        for (HitBox object : collisionObjects) {
            if (isCollision(rect, object)) {
                return true; // Collision détectée
            }
        }
        return false;
    }

    @Override
    public boolean isOutOfBounds(HitBox hitBox) {
        return hitBox.getPosition().x < 0 || hitBox.getPosition().x + hitBox.getWidth() > mapWidth ||
            hitBox.getPosition().y < 0 || hitBox.getPosition().y + hitBox.getHeight() > mapHeight;
    }

    public void resolveCollisions(HitBox hitBox, Vector2 position, Vector2 direction) {

        HitBox newHitBox = hitBox.copy(position.x, position.y);

        while (checkCollisionWithObjects(newHitBox)){
            position.x -= direction.x;
            position.y -= direction.y;
            newHitBox.setPosition(position);
        }
        // Appliquer la position ajustée
        hitBox.setPosition(position);
        clampPosition(position, hitBox.getWidth(), hitBox.getHeight());
    }


    public void clampPosition(Vector2 position, float width, float height) {
        position.x = MathUtils.clamp(position.x, 0, mapWidth - width);
        position.y = MathUtils.clamp(position.y, 0, mapHeight - height);
    }

    // Vérifie si une position est dans une zone restreinte
    @Override
    public boolean isInRestrictedZone(HitBox bbox) {
        return checkCollisionWithObjects(bbox) || isOutOfBounds(bbox);
    }

}
