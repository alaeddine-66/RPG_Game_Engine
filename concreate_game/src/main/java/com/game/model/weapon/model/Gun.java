package com.game.model.weapon.model;

import com.engine.model.data.WeaponData;
import com.engine.model.projectile.ProjectileBehavior.IProjectileBehavior;
import com.engine.model.projectile.effect.IEffectBehavior;
import com.engine.model.weapon.fire.IFireBehavior;
import com.engine.model.weapon.model.AbstractWeapon;


/**
 * Classe représentant une arme de type "Gun" (pistolet).
 * Cette classe hérite de {@code Weapon} et utilise des comportements de tir et d'effet spécifiques.
 */
public class Gun extends AbstractWeapon {

    /**
     * Initialise un pistolet avec les comportements et les données spécifiés.
     *
     * @param fireBehavior   Le comportement de tir de l'arme, implémentant {@code FireBehavior}.
     * @param effectBehavior Le comportement d'effet appliqué par l'arme, implémentant {@code EffectBehavior}.
     * @param data           Les données propres à l'arme, comme les dégâts, la cadence, ou d'autres statistiques.
     */
    public Gun(IFireBehavior fireBehavior, IEffectBehavior effectBehavior, IProjectileBehavior projectileBehavior ,WeaponData data) {
        super(fireBehavior, effectBehavior,projectileBehavior, data);
    }
}
