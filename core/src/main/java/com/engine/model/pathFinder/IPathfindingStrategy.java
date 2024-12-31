package com.engine.model.pathFinder;


import com.badlogic.gdx.math.Vector2;

import java.util.List;

/**
 * Interface représentant une stratégie de pathfinding (recherche de chemin) pour une entité.
 * <p>
 * Cette interface définit la méthode nécessaire pour trouver un chemin entre deux nœuds dans une carte,
 * en tenant compte des dimensions de l'entité (largeur et hauteur) et en appliquant une logique de pathfinding.
 * Différentes stratégies de pathfinding peuvent être implémentées, comme A* ou Dijkstra.
 * </p>
 */
public interface IPathfindingStrategy {

    /**
     * Trouve un chemin entre deux nœuds, du point de départ au point d'arrivée.
     * <p>
     * Cette méthode calcule une liste de positions (sous forme de {@link Vector2}) représentant le chemin à suivre,
     * en tenant compte des dimensions de l'entité et des obstacles éventuels.
     * </p>
     *
     * @param start le nœud de départ.
     * @param End le nœud d'arrivée.
     * @param entityWidth la largeur de l'entité.
     * @param entityHeight la hauteur de l'entité.
     * @return une liste de {@link Vector2} représentant les positions à suivre pour atteindre l'objectif.
     */
    List<Vector2> findPath(Node start, Node End, int entityWidth, int entityHeight);
}
