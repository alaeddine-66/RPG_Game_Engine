package com.engine.model.weapon.fire;

import com.badlogic.gdx.math.Vector2;
import com.engine.model.projectile.factory.ProjectileFactory;
import com.engine.model.projectile.model.AbstractProjectile;

import java.util.ArrayList;
import java.util.List;

/**
 * Implémentation du comportement de tir pour un tir multiple.
 * Cette classe utilise une factory de projectiles pour créer et tirer plusieurs projectiles
 * en éventail autour d'un angle spécifié.
 */
public class MultiShotFireBehavior implements IFireBehavior {

    /** Nombre de projectiles tirés. */
    private int shotCount;

    /** La factory utilisée pour créer les projectiles. */
    private ProjectileFactory projectileFactory;

    /**
     * Constructeur permettant d'initialiser la factory de projectiles et le nombre de tirs.
     *
     * @param factory   L'instance de {@code ProjectileFactory} utilisée pour générer les projectiles.
     * @param shotCount Le nombre de projectiles à tirer simultanément.
     */
    public MultiShotFireBehavior(ProjectileFactory factory, int shotCount) {
        this.shotCount = shotCount;
        this.projectileFactory = factory;
    }

    /**
     * Tire plusieurs projectiles depuis une position donnée dans un cône autour d'un angle spécifique.
     *
     * @param position La position de départ des projectiles, représentée par un objet {@code Vector2}.
     * @param angle    L'angle central du cône en degrés, indiquant la direction principale du tir.
     * @return Une liste de {@code Projectile} représentant les projectiles tirés.
     */
    @Override
    public List<AbstractProjectile> fire(Vector2 position, float angle) {
        List<AbstractProjectile> projectiles = new ArrayList<>();

        // Calcul de l'angle d'écart entre les projectiles
        float spreadAngle = angle / shotCount;

        // Déterminer les angles des projectiles dans le cône
        float startAngle = angle - spreadAngle / 2; // Angle de départ
        float endAngle = angle + spreadAngle / 2;   // Angle de fin

        // Génération des projectiles
        for (int i = 0; i < shotCount; i++) {
            // Interpolation linéaire de l'angle pour chaque projectile
            float t = (float) i / (shotCount - 1); // Normalisation entre 0 et 1
            float currentAngle = startAngle + (endAngle - startAngle) * t; // Lerp

            // Calcul de la direction du projectile
            Vector2 velocity = new Vector2(
                (float) Math.cos(Math.toRadians(currentAngle)),
                (float) Math.sin(Math.toRadians(currentAngle))
            );

            // Création du projectile via la factory
            projectiles.add(projectileFactory.createProjectile(position.cpy(), velocity ,
                projectileFactory.getHitBoxFactory().createHitBox(position.cpy() , projectileFactory.getSize())));

        }

        return projectiles;
    }

    /**
     * Augmente le nombre de projectiles tirés.
     *
     * @param amount Le nombre de projectiles à ajouter.
     */
    public void increaseShotCount(int amount) {
        this.shotCount += amount;
    }
}
