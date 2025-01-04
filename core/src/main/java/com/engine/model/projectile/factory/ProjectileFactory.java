package com.engine.model.projectile.factory;

import com.badlogic.gdx.math.Vector2;
import com.engine.model.data.ProjectileData;
import com.engine.model.entity.components.hitBox.HitBox;
import com.engine.model.entity.components.hitBox.factory.HitBoxFactory;
import com.engine.model.projectile.model.AbstractProjectile;

/**
 * Interface représentant une factory pour la création de projectiles.
 * Cette interface définit une méthode standardisée pour instancier des projectiles
 * avec une position et une vitesse données.
 */
public abstract class ProjectileFactory {
    protected final ProjectileData data;
    protected final HitBoxFactory hitBoxFactory;
    protected Vector2 Size;


    public ProjectileFactory(ProjectileData data , HitBoxFactory hitBoxFactory) {
        this.data = data;
        this.hitBoxFactory = hitBoxFactory;
        this.Size = new Vector2(data.getWidth(),data.getHeight());
    }

    public HitBoxFactory getHitBoxFactory(){
        return hitBoxFactory;
    }

    public ProjectileData getData(){
        return data;
    }

    public Vector2 getSize(){
        return Size;
    }

    /**
     * Crée un nouveau projectile à partir de la position et de la vitesse spécifiées.
     *
     * @param position La position initiale du projectile, représentée par un objet {@code Vector2}.
     * @param velocity La direction et vitesse du projectile, représentée par un objet {@code Vector2}.
     * @return Une instance de {@code Projectile} initialisée avec les paramètres fournis.
     */
    public abstract AbstractProjectile createProjectile(Vector2 position, Vector2 velocity, HitBox hitBox);
}
