package com.game.model.entity.enemy.model;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.engine.model.entity.IAttackable;
import com.engine.model.entity.components.hitBox.HitBox;
import com.engine.model.entity.components.hitBox.factory.RectangleHitBoxFactory;
import com.engine.model.entity.enemy.factory.AbstractEnemyBuilder;
import com.engine.model.data.EnemyData;
import com.engine.model.entity.enemy.model.Hasminions;
import com.engine.model.map.IMapCollisionChecker;
import com.engine.model.pathFinder.SteeringBehaviorStrategy;
import com.engine.model.resource.DataManager;
import com.engine.model.entity.enemy.model.Enemy;
import com.game.model.entity.enemy.factory.BatFactory;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Necromancer extends Enemy implements Hasminions {

    private final List<Enemy> Minions;
    private final BatFactory batFactory ;
    private final int SummonCooldown ;
    private final int MaxMinions;
    private final List<Enemy> activeMinions = new ArrayList<>(); // Liste des minions actifs
    private float orbitAngle = 0.0f; // Angle actuel d'orbite
    private float delay = 0;


    public Necromancer(Vector2 position, IMapCollisionChecker collisionManager, EnemyData data , HitBox hitBox) {
        super(position, collisionManager, data , hitBox);
        this.Minions = new ArrayList<>();
        this.batFactory = new BatFactory(collisionManager , DataManager.getInstance().getEnemyData("Bat") , new RectangleHitBoxFactory());
        this.SummonCooldown = data.getExtra().get("SummonCooldown");
        this.MaxMinions = data.getExtra().get("MaxMinions");
        SetMoveStrategy(new SteeringBehaviorStrategy(collisionManager));
    }

    @Override
    public void update(List<IAttackable> targets, float dt) {
        delay += dt*2;
        move(targets, dt);

        if (delay >= SummonCooldown) {
            attack(targets);
            delay = 0;
        }

        // Supprimer les minions "morts"
        Iterator<Enemy> iterator = activeMinions.iterator();
        while (iterator.hasNext()) {
            Enemy minion = iterator.next();
            if (minion.getHealthComponent().isDead()) { // Suppose qu'il y a une méthode isDead()
                iterator.remove();
            }
        }
    }

    @Override
    public void attack(List<IAttackable> attackables) {
        summonMinions();
    }

    @Override
    public void move(List<IAttackable> attackables, float dt) {
        float safeDistance = 200.0f; // Distance minimale
        float maxDistance = 300.0f; // Distance maximale (champ de vision)

        // Supposons que la première cible dans attackables soit la principale (ex. joueur)
        if (attackables.isEmpty()) return;
        IAttackable primaryTarget = attackables.get(0);

        Vector2 playerPos = primaryTarget.getBbox().getPosition().cpy();
        Vector2 enemyPos = position.cpy();

        Vector2 targetPosition; // Position cible calculée

        // Calcul de la distance actuelle
        float distanceToPlayer = enemyPos.dst(playerPos);

        if (distanceToPlayer < safeDistance) {
            // Si trop proche, éloigner l'ennemi en calculant une position opposée
            Vector2 directionAwayFromPlayer = new Vector2(enemyPos).sub(playerPos).nor();
            targetPosition = playerPos.add(directionAwayFromPlayer.scl(safeDistance));
        } else if (distanceToPlayer > maxDistance) {
            // Si trop loin, rapprocher l'ennemi pour rester visible
            Vector2 directionToPlayer = new Vector2(playerPos).sub(enemyPos).nor();
            targetPosition = enemyPos.cpy().add(directionToPlayer.scl(distanceToPlayer - maxDistance));
        } else {
            // Si dans la zone, calculer une position orbitale continue
            float orbitSpeed = 50.0f; // Vitesse de rotation en degrés par seconde
            orbitAngle += orbitSpeed * dt; // Incrémente l'angle selon la vitesse d'orbite
            if (orbitAngle > 360) orbitAngle -= 360; // Réinitialise l'angle si > 360°

            float orbitRadius = (safeDistance + maxDistance) / 2.0f; // Rayon d'orbite moyen
            targetPosition = getOrbitPosition(playerPos, orbitRadius, orbitAngle);
        }

        // Utiliser moveStrategy pour calculer la direction vers la position cible
        direction = moveStrategy.followPath(this, targetPosition);

        // Appliquer le mouvement
        setPosition(position.add(direction.scl(getSpeed() * dt)));
        getBbox().setPosition(position);
    }

    /**
     * Calcule une position autour du joueur pour "orbiter".
     */
    private Vector2 getOrbitPosition(Vector2 playerPos, float radius, float angle) {
        // Calculer les coordonnées polaires en utilisant l'angle et le rayon
        float offsetX = (float) (Math.cos(Math.toRadians(angle)) * radius);
        float offsetY = (float) (Math.sin(Math.toRadians(angle)) * radius);

        return new Vector2(playerPos.x + offsetX, playerPos.y + offsetY);
    }


    public List<Enemy> createMinions(int count) {
        List<Enemy> minions = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            Vector2 spawnPosition = generateSpawnPosition();
            Vector2 Size = new Vector2(batFactory.getData().getWidth() , batFactory.getData().getHeight());
            minions.add(batFactory
                .withPosition(spawnPosition)
                .withHitBox(batFactory.getHitBoxFactory().createHitBox(spawnPosition,Size))
                .build());
        }
        return minions;
    }

    @Override
    public void summonMinions() {
        List<Enemy> newMinions = new ArrayList<>();

        int slotsAvailable = MaxMinions - activeMinions.size(); // Espace restant pour les minions

        if (slotsAvailable >= 3) {
            newMinions.addAll(createMinions(3));
        } else if (slotsAvailable == 2) {
            newMinions.addAll(createMinions(2));
        } else if (slotsAvailable == 1) {
            newMinions.addAll(createMinions(1));
        }

        activeMinions.addAll(newMinions);
        Minions.addAll(newMinions);
    }

    private Vector2 generateSpawnPosition() {
        Vector2 spawnPos;
        do {
            spawnPos = new Vector2(this.position.x + MathUtils.random(-50, 50),
                this.position.y + MathUtils.random(-50, 50));
        } while (collisionManager.isInRestrictedZone(getBbox().copy(spawnPos.x , spawnPos.y)));
        return spawnPos ;
    }

    @Override
    public List<Enemy> getMinions(){
        return Minions ;
    }

    @Override
    public void clearMinions(){
        Minions.clear();
    }

}
