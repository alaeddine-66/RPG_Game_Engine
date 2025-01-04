package com.engine.model.entity.components.hitBox.factory;

import com.badlogic.gdx.math.Vector2;
import com.engine.model.entity.components.hitBox.HitBox;

public interface HitBoxFactory {
    HitBox createHitBox(Vector2 position, Vector2 size);
}
