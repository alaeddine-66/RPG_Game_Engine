package com.engine.model.entity.components.hitBox;

import com.badlogic.gdx.math.Vector2;

public interface IHitBox {
    boolean intersects(IHitBox other);

    boolean contains(Vector2 point);

    void updateHitBox(Vector2 position , Vector2 size);

    Vector2 getPosition();

    Vector2 getSize();

    IHitBox copy(float x, float y);

}
