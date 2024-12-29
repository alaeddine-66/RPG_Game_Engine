package com.game.model.projectile.model;

import com.badlogic.gdx.math.Vector2;
import com.engine.model.data.ProjectileData;
import com.engine.model.projectile.model.AbstractProjectile;


public class Fireball extends AbstractProjectile {

    public Fireball(Vector2 position, Vector2 velocity, ProjectileData data) {
        super(position, velocity, data);
    }
}

