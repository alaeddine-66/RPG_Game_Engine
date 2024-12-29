package com.game.model.weapon.factory;

import com.engine.model.weapon.factory.AbstractWeaponBuilder;
import com.engine.model.weapon.model.AbstractWeapon;
import com.game.model.weapon.model.MagicStick;

/**
 * Factory permettant de créer des instances de {@code MagicStick}.
 * Cette classe implémente l'interface {@code WeaponFactory} pour générer des armes de type "MagicStick"
 * avec les comportements et les données spécifiés.
 */
public class MagicStickFactory extends AbstractWeaponBuilder {

    /**
     * Construit une instance de {@code MagicStick} avec les comportements de tir, d'effet et les données fournies.
     *
     */
    @Override
    public AbstractWeapon build() {
        return new MagicStick(fireBehavior, effectBehavior, projectileBehavior ,data);
    }
}
