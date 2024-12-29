package com.game.model.weapon.factory;

import com.engine.model.weapon.factory.AbstractWeaponBuilder;
import com.game.model.weapon.model.Gun;
import com.engine.model.weapon.model.AbstractWeapon;

/**
 * Factory permettant de créer des instances de {@code Gun}.
 * Cette classe implémente l'interface {@code WeaponFactory} pour générer des armes de type "Gun"
 * avec les comportements et les données spécifiés.
 */
public class GunFactory extends AbstractWeaponBuilder {

    @Override
    public AbstractWeapon build() {
        return new Gun(fireBehavior, effectBehavior,projectileBehavior, data);
    }
}
