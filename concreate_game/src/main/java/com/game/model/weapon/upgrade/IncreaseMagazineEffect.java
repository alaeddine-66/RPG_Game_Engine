package com.game.model.weapon.upgrade;

import com.engine.model.data.UpgradeWeaponData;
import com.engine.model.weapon.model.AbstractWeapon;
import com.engine.model.weapon.upgrade.UpgradeEffect;

public class IncreaseMagazineEffect implements UpgradeEffect {
    @Override
    public void apply(UpgradeWeaponData upgrade, AbstractWeapon weapon) {
        weapon.increaseMagazineCapacity((int) upgrade.getValue());
    }
}
