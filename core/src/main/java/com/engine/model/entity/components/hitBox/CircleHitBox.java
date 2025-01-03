package com.engine.model.entity.components.hitBox;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;

public class CircleHitBox extends HitBoxBase {
    private Vector2 center;
    private float radius;
    private Circle circle;

    public CircleHitBox(Vector2 center, float radius, CollisionStrategy collisionStrategy) {
        super(center, new Vector2(radius * 2, radius * 2), collisionStrategy);
        this.circle = new Circle(center , radius);
        this.radius = radius;
    }

    public Circle getCircle(){
        return circle;
    }

    @Override
    public IHitBox copy(float x, float y) {
        return new CircleHitBox(new Vector2(x, y), this.radius , collisionStrategy);
    }

    public Vector2 getCenter() {
        return center;
    }

    public float getRadius() {
        return radius;
    }

    @Override
    public boolean contains(Vector2 point) {
        return circle.contains(point);
    }

}
