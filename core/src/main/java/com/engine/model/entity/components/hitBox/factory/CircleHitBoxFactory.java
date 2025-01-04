package com.engine.model.entity.components.hitBox.factory;

import com.badlogic.gdx.math.Vector2;
import com.engine.model.entity.components.hitBox.CircleCollisionStrategy;
import com.engine.model.entity.components.hitBox.CircleHitBox;
import com.engine.model.entity.components.hitBox.HitBox;

public class CircleHitBoxFactory implements HitBoxFactory {
    @Override
    public HitBox createHitBox(Vector2 position, Vector2 size) {
        return new CircleHitBox(position, size.x, new CircleCollisionStrategy());
    }
}
