package com.engine.model.entity.components.hitBox;

public class RectangleCircleCollisionStrategy implements CollisionStrategy {
    @Override
    public boolean intersects(IHitBox hitBox1, IHitBox hitBox2) {
        RectangleHitBox rect = (RectangleHitBox) hitBox1;
        CircleHitBox circle = (CircleHitBox) hitBox2;

        float closestX = Math.max(rect.getRectangle().x, Math.min(circle.getCenter().x, rect.getRectangle().x + rect.getRectangle().width));
        float closestY = Math.max(rect.getRectangle().y, Math.min(circle.getCenter().y, rect.getRectangle().y + rect.getRectangle().height));
        return circle.getCenter().dst(closestX, closestY) <= circle.getRadius();
    }
}
