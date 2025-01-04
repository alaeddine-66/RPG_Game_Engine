package com.engine.model.weapon.fire;

import com.engine.model.data.WeaponData;
import com.engine.model.resource.DataManager;
import com.engine.model.weapon.BehaviorFactory;

public class SingleShotFireBehaviorFactory implements BehaviorFactory<IFireBehavior> {
    @Override
    public IFireBehavior create(WeaponData data) {
        return new SingleShotFireBehavior(DataManager.getInstance().getProjectileFactory(data.getProjectile()));
    }
}

