package com.game.model.projectile.factory;

import com.badlogic.gdx.math.Vector2;
import com.engine.model.data.ProjectileData;
import com.engine.model.projectile.model.AbstractProjectile;
import com.engine.model.projectile.factory.IProjectileFactory;
import com.game.model.projectile.model.Bullet;


public class BulletFactory implements IProjectileFactory {

    private final ProjectileData data;


    public BulletFactory(ProjectileData data) {
        this.data = data;
    }

    @Override
    public AbstractProjectile createProjectile(Vector2 position, Vector2 velocity) {
        return new Bullet(position, velocity, data);
    }
}
