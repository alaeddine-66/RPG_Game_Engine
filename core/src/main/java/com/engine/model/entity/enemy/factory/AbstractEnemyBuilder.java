package com.engine.model.entity.enemy.factory;

import com.badlogic.gdx.math.Vector2;
import com.engine.model.data.EnemyData;
import com.engine.model.entity.enemy.model.Enemy;
import com.engine.model.map.IMapCollisionChecker;

public abstract class AbstractEnemyBuilder implements EnemyFactory {

    protected Vector2 position;
    protected IMapCollisionChecker collisionChecker;
    protected EnemyData data;

    public AbstractEnemyBuilder withPosition(Vector2 position) {
        this.position = position;
        return this;
    }

    public AbstractEnemyBuilder withCollisionChecker(IMapCollisionChecker collisionChecker) {
        this.collisionChecker = collisionChecker;
        return this;
    }

    public AbstractEnemyBuilder withData(EnemyData data) {
        this.data = data;
        return this;
    }

    @Override
    public Enemy build() {
        if (position == null || collisionChecker == null || data == null) {
            throw new IllegalStateException("Les paramètres nécessaires n'ont pas été définis");
        }
        return createEnemy();
    }

    protected abstract Enemy createEnemy();
}

