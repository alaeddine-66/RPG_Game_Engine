package com.engine.model.projectile.factory;

import com.badlogic.gdx.math.Vector2;
import com.engine.model.projectile.model.AbstractProjectile;

/**
 * Interface représentant une factory pour la création de projectiles.
 * Cette interface définit une méthode standardisée pour instancier des projectiles
 * avec une position et une vitesse données.
 */
public interface IProjectileFactory {

    /**
     * Crée un nouveau projectile à partir de la position et de la vitesse spécifiées.
     *
     * @param position La position initiale du projectile, représentée par un objet {@code Vector2}.
     * @param velocity La direction et vitesse du projectile, représentée par un objet {@code Vector2}.
     * @return Une instance de {@code Projectile} initialisée avec les paramètres fournis.
     */
    AbstractProjectile createProjectile(Vector2 position, Vector2 velocity);
}
