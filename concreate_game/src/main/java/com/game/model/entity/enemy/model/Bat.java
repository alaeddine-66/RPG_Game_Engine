package com.game.model.entity.enemy.model;

import com.badlogic.gdx.math.Vector2;
import com.engine.model.data.EnemyData;
import com.engine.model.entity.IAttackable;
import com.engine.model.entity.components.hitBox.HitBox;
import com.engine.model.entity.enemy.model.Enemy;
import com.engine.model.map.IMapCollisionChecker;

import java.util.List;


public class Bat extends Enemy {

    private int delay = 0;

    public Bat(Vector2 position, IMapCollisionChecker collisionChecker, EnemyData data, HitBox hitBox) {
        super(position, collisionChecker, data,hitBox);
    }

    @Override
    public void update(List<IAttackable> targets, float dt) {
        move(targets, dt);
        attack(targets);
    }

    @Override
    public void attack(List<IAttackable> targets) {
        for (IAttackable target : targets) {
            if (collisionManager.isCollision(getBbox(), target.getBbox())) {
                delay++;
                if (delay > 30) {
                    setAttack();
                    target.getHealthComponent().damageHp(getStrengthComponent().getStrength());
                    delay = 0;
                }
            } else {
                stopAttacking();
                delay = 0;
            }
        }
    }

    @Override
    public void move(List<IAttackable> targets, float dt) {
        if (!targets.isEmpty()) {
            IAttackable target = targets.get(0); // Exemple : viser la première cible
            Vector2 targetPosition = target.getBbox().getPosition().cpy();
            Vector2 targetDirection = targetPosition.sub(this.position).nor();
            setdirection(targetDirection);
            this.position.x += targetDirection.x * speed * dt;
            this.position.y += targetDirection.y * speed * dt;
            getBbox().setPosition(position);
        }
    }
}
