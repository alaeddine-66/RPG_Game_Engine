package com.engine.model.entity.enemy.model;

import com.engine.model.projectile.model.AbstractProjectile;

import java.util.List ;

/**
 * Interface HasProjectile.
 *
 * <p>Définit un contrat pour les ennemis capables de tirer des projectiles.
 * Les classes qui implémentent cette interface doivent fournir une liste des projectiles
 * actifs et gérés par l'ennemi.</p>
 *
 */
public interface HasProjectile {

    /**
     * Retourne une liste des projectiles actifs actuellement utilisés par cet ennemi.
     *
     * @return une liste de {@link AbstractProjectile}.
     */
    List<AbstractProjectile> getProjectileList();
}
