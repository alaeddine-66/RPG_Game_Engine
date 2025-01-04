package com.engine.model.entity.enemy.factory;

import com.badlogic.gdx.math.Vector2;
import com.engine.model.data.EnemyData;
import com.engine.model.entity.components.hitBox.HitBox;
import com.engine.model.entity.components.hitBox.factory.HitBoxFactory;
import com.engine.model.entity.enemy.model.Enemy;
import com.engine.model.map.IMapCollisionChecker;

public abstract class AbstractEnemyBuilder implements EnemyFactory {

    protected Vector2 position;
    protected IMapCollisionChecker collisionChecker;
    protected EnemyData data;
    protected HitBox hitBox;
    protected HitBoxFactory hitBoxFactory;

    public AbstractEnemyBuilder(IMapCollisionChecker collisionChecker, EnemyData data , HitBoxFactory hitBoxFactory){
        this.collisionChecker = collisionChecker;
        this.data = data;
        this.hitBoxFactory = hitBoxFactory;
    }

    public EnemyData getData(){
        return data;
    }

    public HitBoxFactory getHitBoxFactory(){
        return hitBoxFactory;
    }

    public AbstractEnemyBuilder withPosition(Vector2 position) {
        this.position = position;
        return this;
    }

    public AbstractEnemyBuilder withHitBox(HitBox hitBox) {
        this.hitBox = hitBox;
        return this;
    }

    @Override
    public Enemy build() {
        if (position == null || collisionChecker == null || data == null || hitBox == null) {
            throw new IllegalStateException("Les paramètres nécessaires n'ont pas été définis");
        }
        return createEnemy();
    }

    protected abstract Enemy createEnemy();
}

