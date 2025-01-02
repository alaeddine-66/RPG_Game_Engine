package com.engine.model.entity.components.hitBox;

public class CircleCollisionStrategy implements CollisionStrategy {
    @Override
    public boolean intersects(IHitBox hitBox1, IHitBox hitBox2) {
        CircleHitBox circle1 = (CircleHitBox) hitBox1;
        CircleHitBox circle2 = (CircleHitBox) hitBox2;

        return circle1.getCenter().dst(circle2.getCenter()) <= (circle1.getRadius() + circle2.getRadius());
    }
}
