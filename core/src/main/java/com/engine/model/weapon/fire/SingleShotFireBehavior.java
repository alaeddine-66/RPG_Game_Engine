package com.engine.model.weapon.fire;

import com.badlogic.gdx.math.Vector2;
import com.engine.model.projectile.model.AbstractProjectile;
import com.engine.model.projectile.factory.ProjectileFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Implémentation du comportement de tir pour un tir simple.
 * Cette classe utilise une factory de projectiles pour créer et tirer un seul projectile
 * dans une direction spécifiée.
 */
public class SingleShotFireBehavior implements IFireBehavior {

    /** La factory utilisée pour créer les projectiles. */
    private ProjectileFactory projectileFactory;

    /**
     * Constructeur permettant d'initialiser la factory de projectiles.
     *
     * @param factory L'instance de {@code ProjectileFactory} utilisée pour générer les projectiles.
     */
    public SingleShotFireBehavior(ProjectileFactory factory) {
        this.projectileFactory = factory;
    }

    /**
     * Tire un seul projectile depuis une position donnée dans une direction spécifique.
     *
     * @param position La position de départ du projectile, représentée par un objet {@code Vector2}.
     * @param angle    L'angle de tir en degrés, indiquant la direction du projectile.
     * @return Une liste contenant un unique {@code Projectile} créé par la factory.
     */
    @Override
    public List<AbstractProjectile> fire(Vector2 position, float angle) {

        // Liste des projectiles tirés
        List<AbstractProjectile> projectiles = new ArrayList<>();

        // Calcul de la direction du projectile
        Vector2 velocity = new Vector2(
            (float) Math.cos(Math.toRadians(angle)),
            (float) Math.sin(Math.toRadians(angle))
        );

        // Création du projectile via la factory
        projectiles.add(projectileFactory.createProjectile(position.cpy(), velocity , projectileFactory.getHitBoxFactory().createHitBox(position.cpy() , projectileFactory.getSize())));

        return projectiles;
    }
}

