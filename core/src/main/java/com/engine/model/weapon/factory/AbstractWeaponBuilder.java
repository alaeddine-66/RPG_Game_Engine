package com.engine.model.weapon.factory;

import com.engine.model.data.WeaponData;
import com.engine.model.projectile.ProjectileBehavior.IProjectileBehavior;
import com.engine.model.projectile.effect.IEffectBehavior;
import com.engine.model.weapon.fire.IFireBehavior;

/**
 * Classe abstraite représentant un constructeur d'armes.
 * <p>
 * Cette classe abstraite implémente l'interface {@link IWeaponFactory} et permet de construire des objets {@code Weapon}
 * de manière modulaire et fluide, en définissant les comportements de tir, les effets, les comportements de projectile
 * et les données associées à l'arme.
 * </p>
 */
public abstract class AbstractWeaponBuilder implements IWeaponFactory {

    protected IFireBehavior fireBehavior; // Comportement de tir de l'arme
    protected IEffectBehavior effectBehavior; // Effets appliqués par l'arme
    protected IProjectileBehavior projectileBehavior; // Comportement des projectiles de l'arme
    protected WeaponData data; // Données associées à l'arme

    /**
     * Définit le comportement de tir de l'arme.
     * <p>
     * Cette méthode permet de configurer l'arme avec un comportement de tir spécifique, tel qu'un tir unique,
     * un tir en rafale, etc.
     * </p>
     *
     * @param fireBehavior le comportement de tir à appliquer.
     * @return cette instance du {@link AbstractWeaponBuilder}, permettant le chaînage.
     */
    public AbstractWeaponBuilder withFireBehavior(IFireBehavior fireBehavior) {
        this.fireBehavior = fireBehavior;
        return this;
    }

    /**
     * Définit le comportement des effets de l'arme.
     * <p>
     * Cette méthode permet de configurer les effets spécifiques associés à l'arme, tels que des effets de
     * statut, des changements sur l'environnement ou des modifications sur le joueur ou les ennemis.
     * </p>
     *
     * @param effectBehavior le comportement des effets à appliquer.
     * @return cette instance du {@link AbstractWeaponBuilder}, permettant le chaînage.
     */
    public AbstractWeaponBuilder withEffectBehavior(IEffectBehavior effectBehavior) {
        this.effectBehavior = effectBehavior;
        return this;
    }

    /**
     * Définit le comportement des projectiles de l'arme.
     * <p>
     * Cette méthode permet de configurer l'arme avec un comportement particulier pour ses projectiles, comme
     * les projectiles poursuivants, perçants, etc.
     * </p>
     *
     * @param projectileBehavior le comportement des projectiles à appliquer.
     * @return cette instance du {@link AbstractWeaponBuilder}, permettant le chaînage.
     */
    public AbstractWeaponBuilder withProjectileBehavior(IProjectileBehavior projectileBehavior) {
        this.projectileBehavior = projectileBehavior;
        return this;
    }

    /**
     * Définit les données associées à l'arme.
     * <p>
     * Cette méthode permet de configurer les données spécifiques à l'arme, telles que les statistiques
     * de l'arme (dégâts, portée, cadence de tir, etc.).
     * </p>
     *
     * @param data les données de l'arme.
     * @return cette instance du {@link AbstractWeaponBuilder}, permettant le chaînage.
     */
    public AbstractWeaponBuilder withWeaponData(WeaponData data) {
        this.data = data;
        return this;
    }
}
