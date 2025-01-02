package com.engine.model.entity.player;

import com.badlogic.gdx.math.Vector2;
import com.engine.controller.IInputHandler;
import com.engine.model.entity.Entity;
import com.engine.model.entity.IAttackable;
import com.engine.model.entity.IAttacker;
import com.engine.model.entity.components.*;
import com.engine.model.data.PlayerData;
import com.engine.model.weapon.model.AbstractWeapon;

import java.util.List;

/**
 * Represents the protagonist of the game.
 * This class defines the player's fundamental behavior such as movement, attacks, and interaction with the game environment.
 * It extends {@link Entity} and implements {@link IAttackable} and {@link IAttacker}.
 */
public abstract class Player extends Entity implements IAttackable, IAttacker {

    /** Handles the player's movement logic. */
    protected final IMovement movement;

    /** Handles the player's combat logic, including weapon management. */
    protected final ICombat combat;

    /** The current rotation angle of the player, used for directional control. */
    protected float rotationAngle = 0f;

    /** Indicates if the player is currently facing right. */
    protected boolean facingRight = true;

    /** Handles the player's input for controlling actions. */
    protected final IInputHandler inputHandler;

    /**
     * Constructs a new Player with the specified data, input handler, movement logic, and combat logic.
     *
     * @param playerData   The data containing player-specific attributes like ID, position, and dimensions.
     * @param inputHandler The input handler for processing player controls.
     * @param movement     The movement component defining how the player moves.
     * @param combat       The combat component defining how the player attacks.
     */
    public Player(PlayerData playerData, IInputHandler inputHandler, IMovement movement, ICombat combat) {
        super(playerData.getid(), new Vector2(playerData.getStartPosition().getX(), playerData.getStartPosition().getY()),
            new Vector2(playerData.getWidth(), playerData.getHeight()));
        this.movement = movement;
        this.combat = combat;
        this.inputHandler = inputHandler;
    }

    /**
     * Updates the player's state, including movement and combat, during the game loop.
     *
     * @param targets The list of attackable entities in the game world.
     * @param dt      The time elapsed since the last update (delta time).
     */
    public void update(List<IAttackable> targets, float dt) {
        attack(targets);
        move(dt);
    }

    /**
     * Defines how the player moves. This method must be implemented by subclasses.
     *
     * @param dt The time elapsed since the last update (delta time).
     */
    public abstract void move(float dt);

    /**
     * Defines how the player attacks. This method must be implemented by subclasses.
     *
     * @param targets The list of attackable entities in the game world.
     */
    public abstract void attack(List<IAttackable> targets);

    /**
     * Gets the current rotation angle of the player.
     *
     * @return The rotation angle in degrees.
     */
    public float getRotationAngle() {
        return rotationAngle;
    }

    /**
     * Sets the rotation angle of the player.
     *
     * @param rotationAngle The new rotation angle in degrees.
     */
    public void setRotationAngle(float rotationAngle) {
        this.rotationAngle = rotationAngle;
    }

    /**
     * Checks if the player is currently facing right.
     *
     * @return {@code true} if the player is facing right; {@code false} otherwise.
     */
    public boolean isFacingRight() {
        return facingRight;
    }

    /**
     * Gets the weapon currently equipped by the player.
     *
     * @return The player's current weapon.
     */
    public AbstractWeapon getWeapon() {
        return combat.getWeapon();
    }

    /**
     * Sets the weapon for the player.
     *
     * @param weapon The new weapon to equip.
     */
    public void setWeapon(AbstractWeapon weapon) {
        combat.setWeapon(weapon);
    }

    /**
     * Retrieves the player's health component.
     *
     * @return The {@link HealthComponent} associated with the player.
     */
    @Override
    public HealthComponent getHealthComponent() {
        return getComponent(HealthComponent.class);
    }

    /**
     * Retrieves the player's strength component.
     *
     * @return The {@link StrengthComponent} associated with the player.
     */
    @Override
    public StrengthComponent getStrengthComponent() {
        return getComponent(StrengthComponent.class);
    }
}
