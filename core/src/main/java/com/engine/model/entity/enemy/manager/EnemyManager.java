package com.engine.model.entity.enemy.manager;

import com.badlogic.gdx.math.Rectangle;
import com.engine.model.entity.enemy.model.Enemy;
import com.engine.model.entity.enemy.spawn.SpawnPositionStrategy;
import com.engine.model.map.IMapCollisionChecker;

import com.engine.model.data.EnemyData;
import com.engine.model.entity.enemy.factory.AbstractEnemyBuilder;

import com.engine.model.resource.DataManager;
import com.badlogic.gdx.math.Vector2;
import java.util.ArrayList;
import java.util.List;

public class EnemyManager {
    private final IMapCollisionChecker collisionChecker;
    private SpawnPositionStrategy spawnStrategy;

    public EnemyManager(IMapCollisionChecker collisionChecker, SpawnPositionStrategy spawnStrategy) {
        this.collisionChecker = collisionChecker;
        this.spawnStrategy = spawnStrategy;
    }

    // Factory pour créer des ennemis
    public List<Enemy> generateEnemies(int numberOfEnemies, EnemyData data) {
        List<Enemy> enemies = new ArrayList<>();
        for (int i = 0; i < numberOfEnemies; i++) {
            Vector2 spawnPos;
            do {
                spawnPos = spawnStrategy.generateSpawnPosition(data);
            } while (collisionChecker.isInRestrictedZone(new Rectangle(spawnPos.x , spawnPos.y, data.getWidth(), data.getHeight())));

            AbstractEnemyBuilder factory = DataManager.getInstance().getEnemyFactory(data.getId());
            if (factory == null) {
                throw new IllegalArgumentException("Factory non trouvée pour l'ID: " + data.getId());
            }

            enemies.add(factory
                .withData(data)
                .withPosition(spawnPos)
                .withCollisionChecker(collisionChecker)
                .build()
            );
        }
        return enemies;
    }

    public void setSpawnStrategy(SpawnPositionStrategy spawnStrategy){
        this.spawnStrategy = spawnStrategy;
    }

}



