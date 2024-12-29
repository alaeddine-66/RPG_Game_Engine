package com.game.model.weapon.model;

import com.engine.model.data.WeaponData;
import com.engine.model.projectile.ProjectileBehavior.IProjectileBehavior;
import com.engine.model.projectile.effect.IEffectBehavior;
import com.engine.model.weapon.fire.IFireBehavior;
import com.engine.model.weapon.model.AbstractWeapon;


/**
 * Classe représentant une arme de type "MagicStick" (bâton magique).
 * Cette classe hérite de {@code Weapon} et utilise des comportements de tir et d'effet spécifiques,
 * adaptés aux armes magiques.
 */
public class MagicStick extends AbstractWeapon {

    /**
     * Initialise un bâton magique avec les comportements et les données spécifiés.
     *
     * @param fireBehavior   Le comportement de tir de l'arme, implémentant {@code FireBehavior}.
     * @param effectBehavior Le comportement d'effet appliqué par l'arme, implémentant {@code EffectBehavior}.
     * @param data           Les données propres à l'arme, comme les dégâts, la portée ou les effets magiques.
     */
    public MagicStick(IFireBehavior fireBehavior, IEffectBehavior effectBehavior, IProjectileBehavior projectileBehavior, WeaponData data) {
        super(fireBehavior, effectBehavior,projectileBehavior, data);
    }
}
