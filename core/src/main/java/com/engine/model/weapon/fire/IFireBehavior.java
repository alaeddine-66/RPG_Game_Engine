package com.engine.model.weapon.fire;

import com.badlogic.gdx.math.Vector2;
import com.engine.model.projectile.model.AbstractProjectile;

import java.util.List ;

/**
 * Interface représentant le comportement de tir d'une arme.
 * Cette interface définit les méthodes nécessaires pour configurer
 * une usine de projectiles et pour générer des projectiles lors d'un tir.
 */
public interface IFireBehavior {
    /**
     * Tire des projectiles depuis une position donnée dans une direction spécifique.
     *
     * @param position La position de départ des projectiles, représentée par un objet {@code Vector2}.
     * @param angle    L'angle de tir en degrés, indiquant la direction dans laquelle
     *                 les projectiles seront tirés.
     * @return Une liste de {@code Projectile} représentant les projectiles tirés.
     */
    List<AbstractProjectile> fire(Vector2 position, float angle);
}
