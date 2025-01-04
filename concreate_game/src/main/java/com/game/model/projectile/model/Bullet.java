package com.game.model.projectile.model;

import com.badlogic.gdx.math.Vector2;
import com.engine.model.data.ProjectileData;
import com.engine.model.entity.components.hitBox.HitBox;
import com.engine.model.projectile.model.AbstractProjectile;

/**
 * Classe représentant un projectile de type "Bullet" (balle).
 * Cette classe hérite de la classe {@code Projectile} et utilise les propriétés
 * de base des projectiles.
 */
public class Bullet extends AbstractProjectile {

    /**
     * Constructeur permettant d'initialiser une balle avec sa position,
     * sa vitesse et ses données spécifiques.
     *
     * @param position La position initiale de la balle, représentée par un objet {@code Vector2}.
     * @param velocity La vitesse et direction de la balle, représentée par un objet {@code Vector2}.
     * @param data     Les données du projectile contenant ses propriétés (dégâts, durée de vie, etc.).
     */
    public Bullet(Vector2 position, Vector2 velocity, ProjectileData data, HitBox hitBox) {
        super(position, velocity, data , hitBox);
    }
}
