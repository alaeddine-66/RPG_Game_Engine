package com.engine.model.weapon.upgrade;

import com.engine.model.data.UpgradeWeaponData;
import com.engine.model.weapon.model.AbstractWeapon;

public interface UpgradeEffect {
    void apply(UpgradeWeaponData upgrade, AbstractWeapon weapon);
}
