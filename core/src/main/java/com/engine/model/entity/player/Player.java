package com.engine.model.entity.player;

import com.badlogic.gdx.math.Vector2;
import com.engine.controller.IInputHandler;
import com.engine.model.entity.Entity;
import com.engine.model.entity.IAttackable;
import com.engine.model.entity.IAttacker;
import com.engine.model.entity.components.*;
import com.engine.model.data.PlayerData;
import com.engine.model.entity.components.hitBox.HitBox;
import com.engine.model.weapon.model.AbstractWeapon;

import java.util.List;


/**
 * The protagonist of the game.
 * */
public abstract class Player extends Entity implements IAttackable, IAttacker {

    protected final IMovement movement;

    protected final ICombat combat;

    protected float rotationAngle = 0f;

    protected boolean facingRight = true;

    protected final IInputHandler inputHandler;

    public Player(PlayerData playerData, IInputHandler inputHandler, IMovement movement, ICombat combat , HitBox hitbox) {
        super(playerData.getid(), hitbox.getPosition() , hitbox);

        this.movement = movement;
        this.combat = combat;
        this.inputHandler = inputHandler;

    }

    public void update(List<IAttackable> targets,float dt) {
        attack(targets);
        move(dt);
    }

    /**
     * Abstract method to define how the player moves.
     */
    public abstract void move(float dt);

    /**
     * Abstract method to define how the player attacks.
     */
    public abstract void attack(List<IAttackable> targets);

    public float getRotationAngle() {
        return rotationAngle;
    }

    public void setRotationAngle(float rotationAngle) {
        this.rotationAngle = rotationAngle;
    }

    public boolean isFacingRight() {
        return facingRight;
    }

    public AbstractWeapon getWeapon() {
        return combat.getWeapon();
    }

    public void setWeapon(AbstractWeapon weapon) {
        combat.setWeapon(weapon);
    }

    @Override
    public HealthComponent getHealthComponent() {
        return getComponent(HealthComponent.class);
    }

    @Override
    public StrengthComponent getStrengthComponent(){
        return getComponent(StrengthComponent.class);
    }

}
