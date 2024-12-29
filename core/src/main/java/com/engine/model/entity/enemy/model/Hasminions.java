package com.engine.model.entity.enemy.model;

import java.util.List;

/**
 * Interface HasMinions.
 *
 * <p>Définit un contrat pour les ennemis capables d'invoquer ou de gérer des minions.
 * Les classes qui implémentent cette interface peuvent invoquer des minions,
 * les récupérer ou vider la liste des minions associés.</p>
 *
 */
public interface Hasminions {

    /**
     * Invoque des minions spécifiques à cet ennemi.
     */
    void summonMinions();

    /**
     * Retourne la liste actuelle des minions associés à cet ennemi.
     *
     * @return une liste des minions existants.
     */
    List<Enemy> getMinions();

    /**
     * Vide la liste des minions associés.
     */
    void clearMinions();
}
