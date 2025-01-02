package com.engine.model.entity.components.hitBox;

public interface CollisionStrategy {
    boolean intersects(IHitBox hitBox1, IHitBox hitBox2);
}

