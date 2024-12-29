package com.engine.model.entity.enemy.factory;

import com.engine.model.entity.enemy.model.Enemy;

/**
 * Interface EnemyFactory.
 * <p>
 * Cette interface définit un contrat pour la création d'instances d'ennemis dans le jeu.
 * Elle suit le patron de conception Factory Method afin de déléguer la création
 * d'objets {@link Enemy} à des classes concrètes.
 * </p>
 */
public interface EnemyFactory {

    /**
     * Construit une instance d'un ennemi.
     * <p>
     * Chaque implémentation concrète de cette interface fournit sa propre logique
     * pour créer et configurer une instance d'ennemi spécifique.
     * </p>
     *
     * @return une nouvelle instance de {@link Enemy}.
     */
    Enemy build();
}
