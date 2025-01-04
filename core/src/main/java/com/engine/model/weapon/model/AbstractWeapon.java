package com.engine.model.weapon.model;

import com.engine.model.data.WeaponData;
import com.engine.model.entity.IAttackable;
import com.engine.model.entity.IAttacker;
import com.engine.model.entity.player.Player;
import com.engine.model.map.IMapCollisionChecker;

import java.util.List;
import java.util.ArrayList;
import com.badlogic.gdx.math.Vector2;
import com.engine.model.projectile.effect.IEffectBehavior;
import com.engine.model.weapon.fire.IFireBehavior;
import com.engine.model.projectile.model.AbstractProjectile;
import com.engine.model.projectile.ProjectileBehavior.IProjectileBehavior;

import java.util.Iterator;

/**
 * Classe abstraite représentant une arme dans le jeu.
 * Cette classe définit les comportements communs à toutes les armes, tels que le tir, les projectiles,
 * la gestion des munitions et l'application d'effets.
 */
public abstract class  AbstractWeapon {
    private IFireBehavior fireBehavior; // Comportement de tir
    private IEffectBehavior effectBehavior; // Comportement des effets
    private float damage; // Dégâts infligés par l'arme
    private float fire_rate; // Cadence de tir de l'arme
    private final String path; // Chemin pour les ressources (ex. textures)
    private final List<AbstractProjectile> projectiles; // Liste des projectiles tirés
    private Vector2 weaponPos; // Position actuelle de l'arme
    private final WeaponData data; // Données spécifiques de l'arme
    private IProjectileBehavior projectileBehavior; // Comportement des projectiles
    private float RotationAngle;

    private int magazineCapacity; // Capacité maximale du chargeur
    private int currentAmmo; // Munitions actuelles
    private int reloadTime; // Temps nécessaire pour recharger

    /**
     * Constructeur de la classe {@code Weapon}.
     *
     * @param fireBehavior   Le comportement de tir de l'arme, implémentant {@code FireBehavior}.
     * @param effectBehavior Le comportement d'effet appliqué par l'arme, implémentant {@code EffectBehavior}.
     * @param data           Les données associées à l'arme, telles que les dégâts, la cadence de tir ou le chargeur.
     */
    public AbstractWeapon(IFireBehavior fireBehavior, IEffectBehavior effectBehavior,IProjectileBehavior projectileBehavior, WeaponData data) {
        this.fireBehavior = fireBehavior;
        this.effectBehavior = effectBehavior;
        this.damage = data.getDamage();
        this.fire_rate = data.getFireRate();
        this.path = data.getPath();
        this.projectiles = new ArrayList<>();
        this.weaponPos = new Vector2();
        this.data = data;
        this.projectileBehavior = projectileBehavior;
        this.magazineCapacity = data.getMagazineCapacity();
        this.reloadTime = data.getReloadTime();
        this.currentAmmo = magazineCapacity; // Initialise avec un chargeur plein
    }

    /**
     * Effectue une attaque en tirant des projectiles depuis une position et un angle spécifiés.
     *
     * @param position La position initiale de l'arme (Vector2).
     * @param angle    L'angle de tir (en degrés).
     */
    public void attack(Vector2 position, float angle) {
        shoot();
        List<AbstractProjectile> newProjectiles = fireBehavior.fire(position, angle);
        projectiles.addAll(newProjectiles);
    }

    /**
     * Met à jour les projectiles et vérifie leurs interactions avec les ennemis et la carte.
     *
     * @param targets   La liste des targets présents dans le jeu.
     * @param collisionManager   La carte du jeu, utilisée pour vérifier les collisions.
     * @param attacker    Le joueur, nécessaire pour certaines interactions.
     */
    public void update(List<IAttackable> targets, IMapCollisionChecker collisionManager, IAttacker attacker) {
        Iterator<AbstractProjectile> iterator = projectiles.iterator();
        while (iterator.hasNext()) {
            AbstractProjectile projectile = iterator.next();

            if (projectile.isActive()) {
                projectileBehavior.updateProjectile(projectile, damage, targets, attacker);

                if (collisionManager.checkCollisionWithObjects(projectile.getBbox()) ||
                    collisionManager.isOutOfBounds(projectile.getBbox())) {
                    projectile.deactivate();
                }
            }

            if (!projectile.isActive()) {
                iterator.remove();
            }

        }
    }

    /**
     * Vérifie si l'arme peut tirer (munitions restantes).
     *
     * @return {@code true} si l'arme peut tirer, sinon {@code false}.
     */
    public boolean canShoot() {
        return currentAmmo > 0;
    }

    /**
     * Décrémente les munitions lorsqu'un tir est effectué.
     */
    public void shoot() {
        if (currentAmmo > 0) {
            currentAmmo--;
        }
    }

    /**
     * Recharge l'arme à sa capacité maximale.
     */
    public void reload() {
        currentAmmo = magazineCapacity;
    }

    // Getters et setters principaux

    public void setFireBehavior(IFireBehavior fireBehavior) {
        this.fireBehavior = fireBehavior;
    }

    public IFireBehavior getFireBehavior() {
        return fireBehavior;
    }

    public void setEffectBehavior(IEffectBehavior effectBehavior) {
        this.effectBehavior = effectBehavior;
    }

    public List<AbstractProjectile> getProjectiles() {
        return projectiles;
    }

    public void setProjectileBehavior(IProjectileBehavior projectileBehavior) {
        this.projectileBehavior = projectileBehavior;
    }

    public int getReloadTime() {
        return reloadTime;
    }

    public int getCurrentAmmo() {
        return currentAmmo;
    }

    public String getPath() {
        return path;
    }

    public Vector2 getweaponPos() {
        return weaponPos;
    }

    public void setweaponPos(Vector2 weaponPos) {
        this.weaponPos = weaponPos;
    }

    public float getDamage() {
        return damage;
    }

    public void setDamage(float damage) {
        this.damage = damage;
    }

    public float getFire_rate() {
        return fire_rate;
    }

    public void setFire_rate(float fire_rate) {
        this.fire_rate = fire_rate;
    }

    public WeaponData getData() {
        return data;
    }

    /**
     * Augmente la capacité maximale du chargeur.
     *
     * @param value La valeur à ajouté à la capacité du chargeur.
     */
    public void increaseMagazineCapacity(int value) {
        this.magazineCapacity += value;
    }

    public void DecreaseReloadTime(double percentage){
        this.reloadTime -= (int) (reloadTime * percentage);
    }

    public float getRotationAngle(){
        return RotationAngle;
    }

    public void setRotationAngle(float angle){
        this.RotationAngle = angle ;
    }
}
