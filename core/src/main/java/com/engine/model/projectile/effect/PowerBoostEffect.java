package com.engine.model.projectile.effect;

import com.engine.model.weapon.model.AbstractWeapon;

public class PowerBoostEffect implements IEffectBehavior {
    private final float boostMultiplier;  // Multiplicateur de puissance
    private final float duration;         // Durée de l'effet (en secondes)
    private float elapsedTime;            // Temps écoulé

    public PowerBoostEffect(float boostMultiplier, float duration) {
        this.boostMultiplier = boostMultiplier;
        this.duration = duration;
        this.elapsedTime = 0;
    }

    @Override
    public void applyEffect(AbstractWeapon weapon, float deltaTime) {
        // Appliquer l'effet si la durée n'est pas expirée
        if (elapsedTime < duration) {
            weapon.setDamage(weapon.getDamage() * boostMultiplier); // Augmente les dégâts
            elapsedTime += deltaTime;  // Met à jour le temps écoulé
        } else {
            // Réinitialise les dégâts une fois l'effet expiré
            weapon.setDamage(weapon.getDamage());
        }
    }
}
