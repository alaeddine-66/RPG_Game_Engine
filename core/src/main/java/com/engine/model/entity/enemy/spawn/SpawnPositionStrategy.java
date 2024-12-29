package com.engine.model.entity.enemy.spawn;

import com.badlogic.gdx.math.Vector2;
import com.engine.model.data.EnemyData;


public interface SpawnPositionStrategy {
    Vector2 generateSpawnPosition(EnemyData data);
}
