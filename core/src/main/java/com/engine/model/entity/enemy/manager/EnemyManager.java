package com.engine.model.entity.enemy.manager;

import com.badlogic.gdx.math.Rectangle;
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
    private final FactoryProvider<AbstractEnemyBuilder> factoryProvider;
    private SpawnPositionStrategy spawnStrategy;

    public EnemyManager(IMapCollisionChecker collisionChecker,
                        SpawnPositionStrategy spawnStrategy,
                        FactoryProvider<AbstractEnemyBuilder> factoryProvider) {
        this.collisionChecker = collisionChecker;
        this.spawnStrategy = spawnStrategy;
        this.factoryProvider = factoryProvider;
    }

    // Factory pour créer des ennemis
    public List<Enemy> generateEnemies(int numberOfEnemies, EnemyData data) {
        List<Enemy> enemies = new ArrayList<>();
        for (int i = 0; i < numberOfEnemies; i++) {
            Vector2 spawnPos;
            do {
                spawnPos = spawnStrategy.generateSpawnPosition(data);
            } while (collisionChecker.isInRestrictedZone(new Rectangle(spawnPos.x, spawnPos.y, data.getWidth(), data.getHeight())));

            AbstractEnemyBuilder factory = factoryProvider.getFactory(data.getId());


            enemies.add(factory
                .withPosition(spawnPos)
                .build()
            );
        }
        return enemies;
    }

    public void setSpawnStrategy(SpawnPositionStrategy spawnStrategy) {
        this.spawnStrategy = spawnStrategy;
    }
}


