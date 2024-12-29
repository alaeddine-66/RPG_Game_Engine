package com.game.model.entity.enemy.factory;


import com.engine.model.entity.enemy.factory.AbstractEnemyBuilder;
import com.engine.model.entity.enemy.model.Enemy;
import com.game.model.entity.enemy.model.Magician;


public class MagicienFactory extends AbstractEnemyBuilder {
    @Override
    public Enemy createEnemy(){
        return new Magician(position, collisionChecker, data);
    }
}

