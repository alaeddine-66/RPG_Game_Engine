package com.engine.model.weapon.factory;

import com.engine.model.weapon.model.AbstractWeapon;

/**
 * Interface représentant une factory pour la création d'armes.
 * Cette interface permet de standardiser la construction des objets {@code Weapon},
 * en utilisant des comportements de tir, des effets et des données spécifiques.
 */
public interface IWeaponFactory {
    AbstractWeapon build();
}
