package com.game.model.weapon.upgrade;

import com.engine.model.data.UpgradeWeaponData;
import com.engine.model.resource.DataManager;
import com.engine.model.weapon.fire.MultiShotFireBehavior;
import com.engine.model.weapon.model.AbstractWeapon;
import com.engine.model.weapon.upgrade.UpgradeEffect;

public class MultiShotEffect implements UpgradeEffect {
    @Override
    public void apply(UpgradeWeaponData upgrade, AbstractWeapon weapon) {
        if (weapon.getFireBehavior() instanceof MultiShotFireBehavior) {
            MultiShotFireBehavior multiShotBehavior = (MultiShotFireBehavior) weapon.getFireBehavior();
            multiShotBehavior.increaseShotCount((int) upgrade.getValue());
        } else {
            weapon.setFireBehavior(new MultiShotFireBehavior(
                DataManager.getInstance().getProjectileFactory(weapon.getData().getProjectile()),
                1 + (int) upgrade.getValue()
            ));
        }
    }
}
