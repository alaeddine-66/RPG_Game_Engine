package com.engine.model.entity.enemy.manager;

import com.engine.model.entity.components.hitBox.HitBox;
import com.engine.model.entity.components.hitBox.factory.HitBoxFactory;
import com.engine.model.resource.FactoryProvider;
import com.engine.model.entity.enemy.model.Enemy;
import com.engine.model.entity.enemy.spawn.SpawnPositionStrategy;
import com.engine.model.map.IMapCollisionChecker;

import com.engine.model.data.EnemyData;
import com.engine.model.entity.enemy.factory.AbstractEnemyBuilder;

import com.badlogic.gdx.math.Vector2;
import java.util.ArrayList;
import java.util.List;

public class EnemyManager {
    private final IMapCollisionChecker collisionChecker;
    private final FactoryProvider<AbstractEnemyBuilder> EnemyfactoryProvider;
    private final FactoryProvider<HitBoxFactory> HitBoxfactoryProvider;
    private SpawnPositionStrategy spawnStrategy;
    private HitBox hitBox;

    public EnemyManager(IMapCollisionChecker collisionChecker,
                        SpawnPositionStrategy spawnStrategy,
                        FactoryProvider<AbstractEnemyBuilder> EnemyfactoryProvider,
                        FactoryProvider<HitBoxFactory> HitBoxfactoryProvider) {
        this.collisionChecker = collisionChecker;
        this.spawnStrategy = spawnStrategy;
        this.EnemyfactoryProvider = EnemyfactoryProvider;
        this.HitBoxfactoryProvider = HitBoxfactoryProvider;
    }

    // Factory pour créer des ennemis
    public List<Enemy> generateEnemies(int numberOfEnemies, EnemyData data) {
        List<Enemy> enemies = new ArrayList<>();
        Vector2 size = new Vector2(data.getWidth() , data.getHeight());
        for (int i = 0; i < numberOfEnemies; i++) {
            Vector2 spawnPos;
            do {
                spawnPos = spawnStrategy.generateSpawnPosition(data);
                hitBox = HitBoxfactoryProvider.getFactory(data.getHitBoxType()).createHitBox(spawnPos, size);
            } while (collisionChecker.isInRestrictedZone(hitBox));

            AbstractEnemyBuilder factory = EnemyfactoryProvider.getFactory(data.getId());
            enemies.add(factory
                .withPosition(spawnPos)
                .withHitBox(hitBox)
                .build()
            );
        }
        return enemies;
    }

    public void setSpawnStrategy(SpawnPositionStrategy spawnStrategy) {
        this.spawnStrategy = spawnStrategy;
    }
}


