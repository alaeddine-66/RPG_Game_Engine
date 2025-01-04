package com.engine.model.projectile.effect;

import com.engine.model.data.WeaponData;
import com.engine.model.weapon.BehaviorFactory;

public class PowerBoostEffectFactory implements BehaviorFactory<IEffectBehavior> {
    @Override
    public IEffectBehavior create(WeaponData data) {
        float boostMultiplier = data.getExtra().getOrDefault("boostMultiplier", 2); // Par défaut, double les dégâts
        float duration = data.getExtra().getOrDefault("duration", 5); // Par défaut, dure 5 secondes
        return new PowerBoostEffect(boostMultiplier, duration);
    }
}
