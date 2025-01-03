package com.engine.model.entity.components.hitBox;

import com.badlogic.gdx.math.Vector2;
import com.engine.model.entity.components.IComponent;

public abstract class HitBoxBase implements IHitBox , IComponent {
    protected Vector2 position;
    protected Vector2 size;
    protected CollisionStrategy collisionStrategy;

    public HitBoxBase(Vector2 position, Vector2 size, CollisionStrategy collisionStrategy) {
        this.position = position;
        this.size = size;
        this.collisionStrategy = collisionStrategy;
    }

    @Override
    public boolean intersects(IHitBox other) {
        return collisionStrategy.intersects(this, other);
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public Vector2 getSize() {
        return size;
    }

    public void setSize(Vector2 size) {
        this.size = size;
    }

    /**
     * Updates the bounding box based on the current position and size.
     */
    public void updateHitBox(Vector2 position , Vector2 size) {
        setPosition(position);
        setSize(size);
    }

    public void setCollisionStrategy(CollisionStrategy collisionStrategy){
        this.collisionStrategy = collisionStrategy;
    }

}
