package com.engine.model.entity.player;

import com.badlogic.gdx.math.Vector2;

public interface IMovement {
    Vector2 move(Vector2 position, float moveX, float moveY, float dt, int width, int height);
}
