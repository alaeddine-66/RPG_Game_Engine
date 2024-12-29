package com.game.model.weapon.upgrade;

import com.engine.model.data.UpgradeWeaponData;
import com.engine.model.projectile.ProjectileBehavior.PursuitProjectileBehavior;
import com.engine.model.weapon.model.AbstractWeapon;
import com.engine.model.weapon.upgrade.UpgradeEffect;

public class PursuitProjectilesEffect implements UpgradeEffect {
    @Override
    public void apply(UpgradeWeaponData upgrade, AbstractWeapon weapon) {
        weapon.setProjectileBehavior(new PursuitProjectileBehavior());
    }
}
