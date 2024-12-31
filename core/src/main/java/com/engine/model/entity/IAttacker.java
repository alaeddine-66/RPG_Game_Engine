package com.engine.model.entity;


import com.engine.model.entity.components.StrengthComponent;

/**
 * Interface représentant un objet ou une entité qui peut attaquer dans le jeu.
 * <p>
 * Les objets ou entités qui implémentent cette interface ont un composant de force,
 * qui détermine la puissance de leurs attaques. Cela peut inclure des ennemis, des joueurs, ou d'autres entités capables d'infliger des dégâts.
 * </p>
 */
public interface IAttacker {

    /**
     * Récupère le composant de force de l'entité.
     * <p>
     * Le composant de force gère la puissance de l'attaque de l'entité, déterminant les dégâts infligés lors d'une attaque.
     * </p>
     *
     * @return le {@link StrengthComponent} de l'entité, qui détermine la puissance de ses attaques.
     */
    StrengthComponent getStrengthComponent();
}
