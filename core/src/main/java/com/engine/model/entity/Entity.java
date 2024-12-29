package com.engine.model.entity;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.engine.model.entity.components.IComponent;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents a generic entity in the game with attributes for position, size,
 * health, damage, and movement. This class is designed to be extended by
 * specific entity types like players or enemies.
 */
public abstract class Entity {

    /** The unique identifier of the entity. */
    protected final String id;

    /** The position of the entity in the game world. */
    protected Vector2 position;

    /** The size of the entity, representing its width and height. */
    protected Vector2 size;

    /** The bounding box used for collision detection. */
    protected Rectangle boundingBox;

    /** The speed of the entity, used for movement calculations. */
    protected int speed;

    protected Map<Class<? extends IComponent>, IComponent> components;

    /** Indicates whether the entity is currently attacking. */
    protected boolean attacking;

    /**
     * Constructs an entity with a given ID, position, and size.
     *
     * @param id       the unique identifier of the entity.
     * @param position the initial position of the entity.
     * @param size     the size of the entity (width and height).
     */
    public Entity(String id, Vector2 position, Vector2 size) {
        this.id = id;
        this.position = position;
        this.size = size;
        this.boundingBox = new Rectangle(position.x, position.y, size.x, size.y);
        this.components = new HashMap<>();

    }

    // --- Getters and Setters ---

    public <T extends IComponent> void addComponent(T component) {
        components.put(component.getClass(), component);
    }

    public <T extends IComponent> T getComponent(Class<T> componentClass) {
        T component = (T) components.get(componentClass);
        if (component == null) {
            throw new IllegalStateException("Component of type " + componentClass.getName() + " is missing.");
        }
        return component;
    }

    public <T extends IComponent> boolean hasComponent(Class<T> componentClass) {
        return components.containsKey(componentClass);
    }

    public Map<Class<? extends IComponent>, IComponent> getComponents(){
        return components;
    }

    /**
     * Gets the unique identifier of the entity.
     *
     * @return the entity's ID.
     */
    public String getId() {
        return id;
    }

    /**
     * Gets the current position of the entity.
     *
     * @return the entity's position.
     */
    public Vector2 getPosition() {
        return position;
    }

    /**
     * Sets the position of the entity and updates the bounding box.
     *
     * @param position the new position.
     */
    public void setPosition(Vector2 position) {
        this.position = position;
        updateBoundingBox();
    }

    /**
     * Gets the bounding box of the entity.
     *
     * @return the entity's bounding box.
     */
    public Rectangle getBbox() {
        return boundingBox;
    }

    /**
     * Updates the bounding box based on the current position and size.
     */
    public void updateBoundingBox() {
        this.boundingBox.set(position.x, position.y, size.x, size.y);
    }

    public int getWidth(){
        return (int) size.x;
    }

    public int getHeight(){
        return (int) size.y;
    }

    /**
     * Gets the speed of the entity.
     *
     * @return the entity's speed.
     */
    public int getSpeed() {
        return speed;
    }

    /**
     * Sets the speed of the entity.
     *
     * @param speed the new speed value.
     */
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    /**
     * Marks the entity as attacking.
     */
    public void setAttack() {
        attacking = true;
    }

    /**
     * Stops the entity from attacking.
     */
    public void stopAttacking() {
        attacking = false;
    }

    /**
     * Checks whether the entity is currently attacking.
     *
     * @return true if the entity is attacking, false otherwise.
     */
    public boolean isAttacking() {
        return attacking;
    }

}
