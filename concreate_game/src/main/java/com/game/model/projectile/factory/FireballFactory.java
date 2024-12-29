package com.game.model.projectile.factory;

import com.badlogic.gdx.math.Vector2;
import com.engine.model.data.ProjectileData;
import com.engine.model.projectile.model.AbstractProjectile;
import com.engine.model.projectile.factory.IProjectileFactory;
import com.game.model.projectile.model.Fireball;

public class FireballFactory implements IProjectileFactory {

    /** Les données associées aux projectiles de type Fireball. */
    private final ProjectileData data;

    public FireballFactory(ProjectileData data) {
        this.data = data;
    }

    @Override
    public AbstractProjectile createProjectile(Vector2 position, Vector2 velocity) {
        return new Fireball(position, velocity, data);
    }
}
