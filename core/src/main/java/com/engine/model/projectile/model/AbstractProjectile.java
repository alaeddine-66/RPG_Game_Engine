package com.engine.model.projectile.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Rectangle;
import com.engine.model.data.ProjectileData;
import com.engine.model.entity.IAttackable;

import java.util.HashSet;
import java.util.Set;

/**
 * Classe abstraite représentant un projectile générique dans le jeu.
 * Cette classe définit les propriétés et comportements communs à tous les projectiles.
 */
public abstract class AbstractProjectile {

    /** Position actuelle du projectile. */
    private Vector2 position;

    /** Vitesse et direction du projectile. */
    private Vector2 direction;

    /** Vitesse du projectile. */
    private int Speed;

    /** Largeur du projectile. */
    private float width;

    /** Hauteur du projectile. */
    private float height;

    /** Statut actif du projectile. Si {@code false}, le projectile est inactif. */
    private boolean active;

    /** Rectangle représentant la hitbox du projectile. */
    private Rectangle rect;

    /** Angle de rotation du projectile en degrés. */
    private float rotationAngle;

    /** Ensemble des ennemis touchés par le projectile. */
    private Set<IAttackable> enemiesHit;

    /** Données spécifiques du projectile (dégâts, dimensions, vitesse, etc.). */
    private ProjectileData data;

    /**
     * Constructeur pour initialiser un projectile avec ses propriétés de base.
     *
     * @param position La position initiale du projectile, représentée par un {@code Vector2}.
     * @param direction La direction du projectile, représentée par un {@code Vector2}.
     * @param data     Les données spécifiques du projectile, contenant ses propriétés comme les dimensions et la vitesse.
     */
    public AbstractProjectile(Vector2 position, Vector2 direction, ProjectileData data) {
        this.position = position;
        this.direction = direction;
        this.width = data.getWidth();
        this.height = data.getHeight();
        this.active = true;
        this.rect = new Rectangle(position.x, position.y, width, height);
        this.rotationAngle = direction.angleDeg();
        this.enemiesHit = new HashSet<>();
        this.Speed = data.getSpeed();
        this.data = data;
    }

    /**
     * Met à jour l'état du projectile.
     * Appelle la méthode {@code move} pour déplacer le projectile et met à jour sa hitbox.
     */
    public void update() {
        if (active) {
            move();
            setRect(new Rectangle(position.x, position.y, width, height));
        }
    }

    /**
     * Déplace le projectile en fonction de sa vitesse, de sa direction et du temps écoulé.
     *
     */
    public void move() {
        float dt = Gdx.graphics.getDeltaTime();
        setRotationAngle(direction.angleDeg());
        position.add(direction.x * Speed * dt, direction.y * Speed * dt);
    }

    /**
     * Désactive le projectile, empêchant toute autre mise à jour ou collision.
     */
    public void deactivate() {
        this.active = false;
    }

    /**
     * Vérifie si le projectile est actif.
     *
     * @return {@code true} si le projectile est actif, {@code false} sinon.
     */
    public boolean isActive() {
        return active;
    }

    public boolean hasHitAttackable(IAttackable target) {
        return enemiesHit.contains(target);
    }

    public void registerHit(IAttackable target) {
        enemiesHit.add(target);
    }

    /**
     * Retourne la position actuelle du projectile.
     *
     * @return La position du projectile sous forme de {@code Vector2}.
     */
    public Vector2 getPosition() {
        return position;
    }

    /**
     * Retourne la largeur du projectile.
     *
     * @return La largeur du projectile.
     */
    public float getWidth() {
        return width;
    }

    /**
     * Retourne la hauteur du projectile.
     *
     * @return La hauteur du projectile.
     */
    public float getHeight() {
        return height;
    }

    /**
     * Retourne la hitbox du projectile.
     *
     * @return Un objet {@code Rectangle} représentant la hitbox actuelle du projectile.
     */
    public Rectangle getRect() {
        return rect;
    }

    /**
     * Met à jour la hitbox du projectile.
     *
     * @param rect La nouvelle hitbox représentée par un objet {@code Rectangle}.
     */
    public void setRect(Rectangle rect) {
        this.rect = rect;
    }

    /**
     * Retourne l'angle de rotation actuel du projectile.
     *
     * @return L'angle de rotation en degrés.
     */
    public float getRotationAngle() {
        return rotationAngle;
    }

    /**
     * Définit l'angle de rotation du projectile.
     *
     * @param rotationAngle Le nouvel angle de rotation en degrés.
     */
    public void setRotationAngle(float rotationAngle) {
        this.rotationAngle = rotationAngle;
    }

    /**
     * Retourne la direction du projectile.
     *
     * @return Un objet {@code Vector2} représentant la direction
     */
    public Vector2 getDirection() {
        return direction;
    }

    /**
     * Met à jour la direction
     *
     * @param direction La nouvelle direction représentée par un objet {@code Vector2}.
     */
    public void setDirection(Vector2 direction) {
        this.direction = direction;
    }

    /**
     * Retourne la vitesse du projectile
     *
     * @return la vitesse du projectile
     */
    public int getSpeed() {
        return Speed;
    }

    /**
     * Met à jour la vitesse du projectile.
     *
     * @param speed La nouvelle vitesse représentée par un objet {@code int}.
     */
    public void setSpeed(int speed) {
        Speed = speed;
    }

    /**
     * Retourne les données spécifiques associées au projectile.
     *
     * @return Un objet {@code ProjectileData} contenant les propriétés du projectile.
     */
    public ProjectileData getData() {
        return data;
    }
}

