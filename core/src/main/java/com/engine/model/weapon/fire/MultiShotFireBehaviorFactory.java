package com.engine.model.weapon.fire;

import com.engine.model.data.WeaponData;
import com.engine.model.resource.DataManager;
import com.engine.model.weapon.BehaviorFactory;

public class MultiShotFireBehaviorFactory implements BehaviorFactory<IFireBehavior> {
    @Override
    public IFireBehavior create(WeaponData data) {
        return new MultiShotFireBehavior(
            DataManager.getInstance().getProjectileFactory(data.getProjectile()),
            data.getExtra().getOrDefault("shotCount", 3)
        );
    }
}
