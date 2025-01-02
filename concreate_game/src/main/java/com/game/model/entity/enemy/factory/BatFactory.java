package com.game.model.entity.enemy.factory;

import com.engine.model.data.EnemyData;
import com.engine.model.entity.enemy.factory.AbstractEnemyBuilder;
import com.engine.model.entity.enemy.model.Enemy;
import com.engine.model.map.IMapCollisionChecker;
import com.game.model.entity.enemy.model.Bat;

public class BatFactory extends AbstractEnemyBuilder {

    public BatFactory(IMapCollisionChecker collisionChecker, EnemyData data) {
        super(collisionChecker, data);
    }

    @Override
    public Enemy createEnemy() {
        return new Bat(position, collisionChecker, data);
    }
}
