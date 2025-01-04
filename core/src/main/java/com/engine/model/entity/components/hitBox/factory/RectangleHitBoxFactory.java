package com.engine.model.entity.components.hitBox.factory;

import com.badlogic.gdx.math.Vector2;
import com.engine.model.entity.components.hitBox.HitBox;
import com.engine.model.entity.components.hitBox.RectangleCollisionStrategy;
import com.engine.model.entity.components.hitBox.RectangleHitBox;

public class RectangleHitBoxFactory implements HitBoxFactory {
    @Override
    public HitBox createHitBox(Vector2 position, Vector2 size) {
        return new RectangleHitBox(position, size, new RectangleCollisionStrategy());
    }
}
