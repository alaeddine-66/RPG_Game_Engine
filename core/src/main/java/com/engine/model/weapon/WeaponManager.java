package com.engine.model.weapon;

import com.engine.model.data.WeaponData;
import com.engine.model.projectile.ProjectileBehavior.DefaultProjectileBehavior;
import com.engine.model.projectile.ProjectileBehavior.IProjectileBehavior;
import com.engine.model.resource.DataManager;
import com.engine.model.projectile.effect.IEffectBehavior;
import com.engine.model.weapon.fire.IFireBehavior;
import com.engine.model.weapon.fire.MultiShotFireBehavior;
import com.engine.model.weapon.fire.SingleShotFireBehavior;
import com.engine.model.weapon.model.AbstractWeapon ;

import java.util.HashMap;


/**
 * La classe <code>WeaponFactory</code> est responsable de la création des objets <code>Weapon</code>
 * à partir des données fournies dans un objet <code>WeaponData</code>.
 * Elle crée les comportements associés à l'arme (comme le <code>FireBehavior</code> et <code>EffectBehavior</code>)
 * en fonction des informations contenues dans <code>WeaponData</code>.
 * <p>
 * Cette classe permet de gérer la création d'armes avec des comportements variés, comme les tirs simples,
 * les tirs multiples, et les effets comme le temps de recharge.
 * </p>
 */
public class WeaponManager {

    /**
     * Crée une arme en fonction des données fournies dans un objet <code>WeaponData</code>.
     * <p>
     * Le comportement de tir et l'effet de l'arme sont définis en fonction des valeurs contenues dans
     * <code>WeaponData</code>. Si des comportements invalides sont spécifiés, une exception est lancée.
     * </p>
     *
     * @param data L'objet contenant les données nécessaires à la création de l'arme, y compris les comportements de tir et d'effet.
     * @return Une instance de <code>Weapon</code> correspondant aux données fournies.
     * @throws IllegalArgumentException Si un comportement inconnu est spécifié dans les données.
     */
    public static AbstractWeapon createWeapon(String type , HashMap<String , WeaponData > data) {
        IFireBehavior fireBehavior = createFireBehavior(data.get(type));
        IEffectBehavior effectBehavior = createEffectBehavior(data.get(type));
        IProjectileBehavior projectileBehavior = createProjectileBehavior(data.get(type));

        return DataManager.getInstance().getWeaponFactory(type)
            .withFireBehavior(fireBehavior)
            .withEffectBehavior(effectBehavior)
            .withProjectileBehavior(projectileBehavior)
            .withWeaponData(data.get(type))
            .build();  // Exemple avec Gun

    }

    /**
     * Crée le comportement de tir (FireBehavior) en fonction des données fournies dans <code>WeaponData</code>.
     *
     * @param data L'objet contenant les informations nécessaires pour créer le comportement de tir.
     * @return Le comportement de tir (FireBehavior) associé à l'arme.
     * @throws IllegalArgumentException Si un type de FireBehavior inconnu est spécifié dans les données.
     */
    private static IFireBehavior createFireBehavior(WeaponData data) {

        switch (data.getFireBehavior()) {
            case "SingleShotFireBehavior":
                return  new SingleShotFireBehavior(DataManager.getInstance().getProjectileFactory(data.getProjectile()));

            case "MultiShotFireBehavior":
                return new MultiShotFireBehavior(
                    DataManager.getInstance().getProjectileFactory(data.getProjectile()),
                    data.getExtra().getOrDefault("shotCount", 3)
                );

            default:
                throw new IllegalArgumentException("Unknown FireBehavior type: " + data.getFireBehavior());
        }

    }

    /**
     * Crée le comportement d'effet (EffectBehavior) en fonction des données fournies dans <code>WeaponData</code>.
     *
     * @param data L'objet contenant les informations nécessaires pour créer le comportement d'effet.
     * @return Le comportement d'effet (EffectBehavior) associé à l'arme.
     * @throws IllegalArgumentException Si un type de EffectBehavior inconnu est spécifié dans les données.
     */
    private static IEffectBehavior createEffectBehavior(WeaponData data) {

        switch (data.getEffectBehavior()) {
            case "CooldownEffect":
                return null;

            default:
                throw new IllegalArgumentException("Unknown EffectBehavior type: " + data.getEffectBehavior());
        }

    }

    private static IProjectileBehavior createProjectileBehavior(WeaponData data) {

        switch (data.getProjectileBehavior()) {
            case "default":
                return  new DefaultProjectileBehavior();

            default:
                throw new IllegalArgumentException("Unknown FireBehavior type: " + data.getFireBehavior());
        }

    }
}

