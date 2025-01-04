package com.engine.model.projectile.ProjectileBehavior.factory;

import com.engine.model.data.WeaponData;
import com.engine.model.projectile.ProjectileBehavior.IProjectileBehavior;
import com.engine.model.projectile.ProjectileBehavior.PursuitProjectileBehavior;
import com.engine.model.projectile.model.AbstractProjectile;
import com.engine.model.weapon.BehaviorFactory;

public class PursuitProjectileBehaviorFactory implements BehaviorFactory<IProjectileBehavior> {
    @Override
    public IProjectileBehavior create(WeaponData data) {
        return new PursuitProjectileBehavior();
    }
}
