package com.game.model.entity.player;

import com.badlogic.gdx.math.Vector2;
import com.engine.model.entity.player.IMovement;
import com.engine.model.map.IMapCollisionChecker;

public class PlayerMovement implements IMovement {
    private final IMapCollisionChecker collisionChecker;
    private final float speed;

    public PlayerMovement(float speed, IMapCollisionChecker collisionChecker) {
        this.collisionChecker = collisionChecker;
        this.speed = speed;
    }

    @Override
    public Vector2 move(Vector2 position, float moveX, float moveY, float dt, int width, int height) {
        // Normaliser la direction
        float magnitude = (float) Math.sqrt(moveX * moveX + moveY * moveY);
        if (magnitude > 0) {
            moveX /= magnitude;
            moveY /= magnitude;
        }

        // Appliquer le déplacement
        position.x += moveX * speed * dt;
        position.y += moveY * speed * dt;

        // Gérer les collisions
        collisionChecker.resolveCollisions(position, new Vector2(moveX * speed * dt, moveY * speed * dt), width, height);

        return position;
    }

}
