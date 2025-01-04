package com.engine.model.projectile.effect;

import com.engine.model.weapon.model.AbstractWeapon;

public interface IEffectBehavior {
    void applyEffect(AbstractWeapon weapon, float deltaTime);
}
