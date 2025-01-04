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
    private final BehaviorRegistry<IFireBehavior> fireBehaviorRegistry;
    private final BehaviorRegistry<IEffectBehavior> effectBehaviorRegistry;
    private final BehaviorRegistry<IProjectileBehavior> projectileBehaviorRegistry;

    public WeaponManager(
        BehaviorRegistry<IFireBehavior> fireBehaviorRegistry,
        BehaviorRegistry<IEffectBehavior> effectBehaviorRegistry,
        BehaviorRegistry<IProjectileBehavior> projectileBehaviorRegistry
    ) {
        this.fireBehaviorRegistry = fireBehaviorRegistry;
        this.effectBehaviorRegistry = effectBehaviorRegistry;
        this.projectileBehaviorRegistry = projectileBehaviorRegistry;
    }

    public AbstractWeapon createWeapon(String type, HashMap<String, WeaponData> data) {
        WeaponData weaponData = data.get(type);
        IFireBehavior fireBehavior = fireBehaviorRegistry.create(weaponData.getFireBehavior(), weaponData);
        IEffectBehavior effectBehavior = effectBehaviorRegistry.create(weaponData.getEffectBehavior(), weaponData);
        IProjectileBehavior projectileBehavior = projectileBehaviorRegistry.create(weaponData.getProjectileBehavior(), weaponData);

        return DataManager.getInstance().getWeaponFactory(type)
            .withFireBehavior(fireBehavior)
            .withEffectBehavior(effectBehavior)
            .withProjectileBehavior(projectileBehavior)
            .withWeaponData(weaponData)
            .build();
    }
}


//    /**
//     * Crée le comportement de tir (FireBehavior) en fonction des données fournies dans <code>WeaponData</code>.
//     *
//     * @param data L'objet contenant les informations nécessaires pour créer le comportement de tir.
//     * @return Le comportement de tir (FireBehavior) associé à l'arme.
//     * @throws IllegalArgumentException Si un type de FireBehavior inconnu est spécifié dans les données.
//     */
//    private static IFireBehavior createFireBehavior(WeaponData data) {
//
//        switch (data.getFireBehavior()) {
//            case "SingleShotFireBehavior":
//                return  new SingleShotFireBehavior(DataManager.getInstance().getProjectileFactory(data.getProjectile()));
//
//            case "MultiShotFireBehavior":
//                return new MultiShotFireBehavior(
//                    DataManager.getInstance().getProjectileFactory(data.getProjectile()),
//                    data.getExtra().getOrDefault("shotCount", 3)
//                );
//
//            default:
//                throw new IllegalArgumentException("Unknown FireBehavior type: " + data.getFireBehavior());
//        }
//
//    }
//
//    /**
//     * Crée le comportement d'effet (EffectBehavior) en fonction des données fournies dans <code>WeaponData</code>.
//     *
//     * @param data L'objet contenant les informations nécessaires pour créer le comportement d'effet.
//     * @return Le comportement d'effet (EffectBehavior) associé à l'arme.
//     * @throws IllegalArgumentException Si un type de EffectBehavior inconnu est spécifié dans les données.
//     */
//    private static IEffectBehavior createEffectBehavior(WeaponData data) {
//
//        switch (data.getEffectBehavior()) {
//            case "CooldownEffect":
//                return null;
//
//            default:
//                throw new IllegalArgumentException("Unknown EffectBehavior type: " + data.getEffectBehavior());
//        }
//
//    }
//
//    private static IProjectileBehavior createProjectileBehavior(WeaponData data) {
//
//        switch (data.getProjectileBehavior()) {
//            case "default":
//                return  new DefaultProjectileBehavior();
//
//            default:
//                throw new IllegalArgumentException("Unknown FireBehavior type: " + data.getFireBehavior());
//        }
//
//    }
//}

