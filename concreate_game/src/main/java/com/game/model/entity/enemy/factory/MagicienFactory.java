package com.game.model.entity.enemy.factory;


import com.engine.model.data.EnemyData;
import com.engine.model.entity.components.hitBox.factory.HitBoxFactory;
import com.engine.model.entity.enemy.factory.AbstractEnemyBuilder;
import com.engine.model.entity.enemy.model.Enemy;
import com.engine.model.map.IMapCollisionChecker;
import com.game.model.entity.enemy.model.Magician;


public class MagicienFactory extends AbstractEnemyBuilder {
    public MagicienFactory(IMapCollisionChecker collisionChecker, EnemyData data, HitBoxFactory hitBoxFactory) {
        super(collisionChecker, data,hitBoxFactory);
    }

    @Override
    public Enemy createEnemy(){
        return new Magician(position, collisionChecker, data , hitBox);
    }
}

