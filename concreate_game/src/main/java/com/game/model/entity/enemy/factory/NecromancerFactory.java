package com.game.model.entity.enemy.factory;

import com.engine.model.data.EnemyData;
import com.engine.model.entity.enemy.factory.AbstractEnemyBuilder;
import com.engine.model.entity.enemy.model.Enemy;
import com.engine.model.map.IMapCollisionChecker;
import com.game.model.entity.enemy.model.Necromancer;


public class NecromancerFactory extends AbstractEnemyBuilder {
    public NecromancerFactory(IMapCollisionChecker collisionChecker, EnemyData data) {
        super(collisionChecker, data);
    }

    @Override
    public Enemy createEnemy() {
        return new Necromancer(position, collisionChecker, data);
    }
}
