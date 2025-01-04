package com.game.model.projectile.factory;

import com.badlogic.gdx.math.Vector2;
import com.engine.model.data.ProjectileData;
import com.engine.model.entity.components.hitBox.HitBox;
import com.engine.model.entity.components.hitBox.factory.HitBoxFactory;
import com.engine.model.projectile.model.AbstractProjectile;
import com.engine.model.projectile.factory.ProjectileFactory;
import com.game.model.projectile.model.Fireball;

public class FireballFactory extends ProjectileFactory {


    public FireballFactory(ProjectileData data,HitBoxFactory hitBoxFactory) {
        super(data,hitBoxFactory);
    }

    @Override
    public AbstractProjectile createProjectile(Vector2 position, Vector2 velocity, HitBox hitbox) {
        return new Fireball(position, velocity, data, hitbox);
    }
}
