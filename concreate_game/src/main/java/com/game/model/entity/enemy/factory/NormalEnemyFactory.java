package com.game.model.entity.enemy.factory;

import com.engine.model.data.EnemyData;
import com.engine.model.entity.components.hitBox.factory.HitBoxFactory;
import com.engine.model.entity.enemy.factory.AbstractEnemyBuilder;
import com.engine.model.entity.enemy.model.Enemy;
import com.engine.model.map.IMapCollisionChecker;
import com.game.model.entity.enemy.model.NormalEnemy;

public class NormalEnemyFactory  extends AbstractEnemyBuilder {

    public NormalEnemyFactory(IMapCollisionChecker collisionChecker, EnemyData data , HitBoxFactory hitBoxFactory) {
        super(collisionChecker, data , hitBoxFactory);
    }

    @Override
    public Enemy createEnemy(){
        return new NormalEnemy(position, collisionChecker, data , hitBox);
    }

}
