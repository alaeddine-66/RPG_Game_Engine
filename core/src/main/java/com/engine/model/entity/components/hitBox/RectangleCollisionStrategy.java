package com.engine.model.entity.components.hitBox;

public class RectangleCollisionStrategy implements CollisionStrategy {
    @Override
    public boolean intersects(IHitBox hitBox1, IHitBox hitBox2) {
        RectangleHitBox rect1 = (RectangleHitBox) hitBox1;
        RectangleHitBox rect2 = (RectangleHitBox) hitBox2;

        return rect1.getRectangle().overlaps(rect2.getRectangle());
    }
}
