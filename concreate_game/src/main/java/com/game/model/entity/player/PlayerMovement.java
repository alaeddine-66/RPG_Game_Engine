package com.game.model.entity.player;

import com.badlogic.gdx.math.Vector2;
import com.engine.model.entity.components.hitBox.HitBox;
import com.engine.model.entity.player.IMovement;
import com.engine.model.map.IMapCollisionChecker;

public class PlayerMovement implements IMovement {
    private final IMapCollisionChecker collisionChecker;
    private final float speed;
    private HitBox PlayerHitBox;

    public PlayerMovement(HitBox PlayerHitBox ,float speed, IMapCollisionChecker collisionChecker ) {
        this.collisionChecker = collisionChecker;
        this.speed = speed;
        this.PlayerHitBox = PlayerHitBox;
    }

    @Override
    public Vector2 move(Vector2 position, float moveX, float moveY, float dt) {
        // Normaliser la direction
        float magnitude = (float) Math.sqrt(moveX * moveX + moveY * moveY);
        if (magnitude > 0) {
            moveX /= magnitude;
            moveY /= magnitude;
        }

        // Calculer le déplacement
        float deltaX = moveX * speed * dt;
        float deltaY = moveY * speed * dt;

        // Appliquer le déplacement à la position
        position.x += deltaX;
        position.y += deltaY;

        // Gérer les collisions
        collisionChecker.resolveCollisions(PlayerHitBox, position, new Vector2(deltaX, deltaY));

        // Retourner la position mise à jour
        return position;
    }


}
