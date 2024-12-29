package com.game.model.entity.enemy.factory;

import com.engine.model.entity.enemy.factory.AbstractEnemyBuilder;
import com.engine.model.entity.enemy.model.Enemy;
import com.game.model.entity.enemy.model.Necromancer;


public class NecromancerFactory extends AbstractEnemyBuilder {
    @Override
    public Enemy createEnemy() {
        return new Necromancer(position, collisionChecker, data);
    }
}
