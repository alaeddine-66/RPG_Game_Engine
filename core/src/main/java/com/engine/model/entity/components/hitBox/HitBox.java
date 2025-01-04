package com.engine.model.entity.components.hitBox;

import com.badlogic.gdx.math.Vector2;

public abstract class HitBox implements IHitBox  {
    protected Vector2 position;
    protected Vector2 size;
    protected CollisionStrategy collisionStrategy;

    public HitBox(Vector2 position, Vector2 size, CollisionStrategy collisionStrategy) {
        this.position = position;
        this.size = size;
        this.collisionStrategy = collisionStrategy;
    }

    @Override
    public boolean intersects(HitBox other) {
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

    public float getWidth(){
        return size.x;
    }

    public float getHeight(){
        return size.y;
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

    public float getLeft() {
        return position.x;
    }

    public float getRight() {
        return position.x + getWidth();
    }

    public float getTop() {
        return position.y;
    }

    public float getBottom() {
        return position.y + getHeight();
    }

}
