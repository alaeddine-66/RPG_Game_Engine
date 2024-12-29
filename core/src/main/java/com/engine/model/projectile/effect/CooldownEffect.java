package com.engine.model.projectile.effect;

public class CooldownEffect implements IEffectBehavior {
    private int fireCount = 0;
    private int cooldownThreshold;

    public CooldownEffect(int cooldownThreshold) {
        this.cooldownThreshold = cooldownThreshold;
    }

    @Override
    public void applyEffect() {
        fireCount++;
        if (fireCount % cooldownThreshold == 0) {
            System.out.println("Triggering special cooldown effect!");
        }
    }
}
