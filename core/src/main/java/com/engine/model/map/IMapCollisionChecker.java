package com.engine.model.map;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public interface IMapCollisionChecker {
    int getTileWidth();
    int getTileHeight();
    int getMapWidth();
    int getMapHeight();
    boolean isCollision(Rectangle rect1 , Rectangle rect2);
    boolean checkCollisionWithObjects(Rectangle entity);
    boolean isOutOfBounds(Rectangle rect);
    boolean isInRestrictedZone(Rectangle rect);
    void resolveCollisions(Vector2 position, Vector2 direction, int width, int height);
}
