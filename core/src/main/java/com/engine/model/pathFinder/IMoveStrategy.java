package com.engine.model.pathFinder;


import com.badlogic.gdx.math.Vector2;
import com.engine.model.entity.Entity;


/**
 * Interface représentant une stratégie de mouvement pour une entité.
 * <p>
 * Les objets qui implémentent cette interface définissent la logique de mouvement d'une entité, en particulier
 * pour suivre un chemin ou se déplacer vers un objectif. Par exemple, cette interface peut être utilisée pour
 * définir des comportements de déplacement pour différents types d'entités (ennemis, personnages non-joueurs, etc.)
 * </p>
 */
public interface IMoveStrategy {

    /**
     * Permet à une entité de suivre un chemin vers une cible spécifique.
     * <p>
     * Cette méthode calcule la nouvelle position de l'entité en fonction de la cible et de la stratégie de mouvement
     * choisie (par exemple, un chemin pré-défini ou un mouvement direct vers la cible).
     * </p>
     *
     * @param enemy l'entité qui effectue le mouvement (par exemple, un ennemi).
     * @param target la position cible que l'entité doit atteindre.
     * @return un objet {@link Vector2} représentant la nouvelle position de l'entité après avoir suivi le chemin.
     */
    Vector2 followPath(Entity enemy, Vector2 target);
}
