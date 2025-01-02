package com.engine.model.entity.components.hitBox;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class RectangleHitBox extends HitBoxBase {
    private final Rectangle rectangle;

    public RectangleHitBox(Vector2 position, Vector2 size, CollisionStrategy collisionStrategy) {
        super(position, size, collisionStrategy);
        this.rectangle = new Rectangle(position.x, position.y, size.x, size.y);
    }

    @Override
    public boolean contains(Vector2 point) {
        return rectangle.contains(point.x, point.y);
    }

    @Override
    public IHitBox copy(float x, float y) {
        return new RectangleHitBox(new Vector2(x, y),getSize() , collisionStrategy);
    }

    @Override
    public void setPosition(Vector2 position) {
        rectangle.setPosition(position.x, position.y);
    }

    @Override
    public void setSize(Vector2 size) {
        rectangle.setSize(size.x, size.y);
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

}
