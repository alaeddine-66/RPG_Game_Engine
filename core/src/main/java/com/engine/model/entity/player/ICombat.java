package com.engine.model.entity.player;

import com.engine.model.weapon.model.AbstractWeapon;

public interface ICombat {
    void handleAttack(boolean attackPressed);
    AbstractWeapon getWeapon();
    void setWeapon(AbstractWeapon weapon);
}
