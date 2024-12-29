package com.game.model.weapon.upgrade;

import com.engine.model.data.UpgradeWeaponData;
import com.engine.model.weapon.model.AbstractWeapon;
import com.engine.model.weapon.upgrade.UpgradeEffect;

public class FireRateEffect implements UpgradeEffect {
    @Override
    public void apply(UpgradeWeaponData upgrade, AbstractWeapon weapon) {
        weapon.setFire_rate(weapon.getFire_rate() * (1 - upgrade.getValue()));
    }
}
