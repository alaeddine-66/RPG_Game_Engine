package com.engine.model.entity;

import com.engine.model.entity.components.HealthComponent;
import com.engine.model.entity.components.hitBox.HitBox;

/**
 * Interface représentant un objet ou une entité qui peut être attaqué dans le jeu.
 * <p>
 * Les objets ou entités qui implémentent cette interface ont un composant de santé et une zone de détection
 * pour savoir si elles sont attaquées. Ils peuvent être des ennemis, des personnages non-joueurs, ou même le joueur.
 * </p>
 */
public interface IAttackable {

    /**
     * Récupère le composant de santé de l'entité.
     * <p>
     * Le composant de santé gère la quantité de vie de l'entité et permet de lui infliger des dégâts ou de la guérir.
     * </p>
     *
     * @return le {@link HealthComponent} de l'entité.
     */
    HealthComponent getHealthComponent();

    /**
     * Récupère la boîte de détection (bounding box) de l'entité.
     * <p>
     * La boîte de détection est utilisée pour savoir si un attaquant entre en collision avec l'entité, indiquant qu'une attaque a eu lieu.
     * </p>
     *
     * @return un objet {@link HitBox} représentant la zone de détection de l'entité.
     */
    HitBox getBbox();
}
