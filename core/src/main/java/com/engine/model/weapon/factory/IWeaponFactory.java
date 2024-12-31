package com.engine.model.weapon.factory;

import com.engine.model.weapon.model.AbstractWeapon;

/**
 * Interface représentant une factory pour la création d'armes.
 * <p>
 * Cette interface permet de standardiser la construction des objets {@code Weapon},
 * en utilisant des comportements de tir, des effets et des données spécifiques.
 * Elle définit la méthode de construction d'une arme, qui sera implémentée par des
 * classes concrètes pour fabriquer différents types d'armes avec des configurations spécifiques.
 * </p>
 */
public interface IWeaponFactory {

    /**
     * Crée et retourne une nouvelle instance d'arme.
     * <p>
     * Cette méthode construit un objet de type {@link AbstractWeapon}, qui peut
     * être une arme spécifique avec des comportements et des effets adaptés.
     * </p>
     *
     * @return une nouvelle instance d'arme, de type {@link AbstractWeapon}.
     */
    AbstractWeapon build();
}
