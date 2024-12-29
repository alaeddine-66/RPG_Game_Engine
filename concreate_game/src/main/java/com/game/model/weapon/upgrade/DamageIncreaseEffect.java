package com.game.model.weapon.upgrade;

import com.engine.model.data.UpgradeWeaponData;
import com.engine.model.weapon.model.AbstractWeapon;
import com.engine.model.weapon.upgrade.UpgradeEffect;

public class DamageIncreaseEffect implements UpgradeEffect {
    @Override
    public void apply(UpgradeWeaponData upgrade, AbstractWeapon weapon) {
        weapon.setDamage(weapon.getDamage() * (1 + upgrade.getValue()));
    }
}
