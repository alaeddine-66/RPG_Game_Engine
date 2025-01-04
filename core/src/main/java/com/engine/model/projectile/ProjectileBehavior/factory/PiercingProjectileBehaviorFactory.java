package com.engine.model.projectile.ProjectileBehavior.factory;

import com.engine.model.data.WeaponData;
import com.engine.model.projectile.ProjectileBehavior.IProjectileBehavior;
import com.engine.model.projectile.ProjectileBehavior.PiercingProjectileBehavior;
import com.engine.model.weapon.BehaviorFactory;

public class PiercingProjectileBehaviorFactory implements BehaviorFactory<IProjectileBehavior> {
    @Override
    public IProjectileBehavior create(WeaponData data) {
        return new PiercingProjectileBehavior();
    }
}
