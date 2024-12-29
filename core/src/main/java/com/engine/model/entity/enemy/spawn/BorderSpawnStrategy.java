package com.engine.model.entity.enemy.spawn;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.engine.model.data.EnemyData;
import com.engine.model.map.IMapCollisionChecker;


public class BorderSpawnStrategy implements SpawnPositionStrategy {

    private final IMapCollisionChecker collisionManager ;

    public BorderSpawnStrategy(IMapCollisionChecker collisionManager){
        this.collisionManager = collisionManager ;
    }

    @Override
    public Vector2 generateSpawnPosition(EnemyData data) {
        int mapWidth = collisionManager.getMapWidth();
        int mapHeight = collisionManager.getMapHeight();
        int side = MathUtils.random(3);
        int x = 0, y = 0;
        int width = data.getWidth(), height = data.getHeight();

        switch (side) {
            case 0: x = MathUtils.random(0, mapWidth - width); y = mapHeight - height; break;
            case 1: x = MathUtils.random(0, mapWidth - width); break;
            case 2: y = MathUtils.random(0, mapHeight - height); break;
            case 3: x = mapWidth - width; y = MathUtils.random(0, mapHeight - height); break;
        }
        return new Vector2(x, y);
    }
}
