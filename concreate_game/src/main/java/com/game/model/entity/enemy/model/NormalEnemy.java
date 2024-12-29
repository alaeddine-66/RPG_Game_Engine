package com.game.model.entity.enemy.model;

import com.badlogic.gdx.math.Vector2;
import com.engine.model.entity.enemy.model.Enemy;
import com.engine.model.map.IMapCollisionChecker;
import com.engine.model.data.EnemyData;
import com.engine.model.pathFinder.SteeringBehaviorStrategy;
import com.engine.model.resource.AudioManager;
import com.engine.model.entity.IAttackable;

import java.util.List;


public class NormalEnemy extends Enemy {

    private int delay = 0;
    private boolean isColliding = false;

    public NormalEnemy(Vector2 position, IMapCollisionChecker collisionManager, EnemyData data) {
        super(position, collisionManager, data);
        SetMoveStrategy(new SteeringBehaviorStrategy(collisionManager));
    }

    @Override
    public void update(List<IAttackable> targets, float dt) {
        // Déplacer l'ennemi en fonction des cibles
        for (IAttackable target : targets) {
            if (collisionManager.isCollision(getBbox(), target.getBbox())) {
                isColliding = true;
                break;
            }else isColliding = false;
        }
        if (!isColliding) move(targets, dt);

        // Attaquer les cibles à portée
        attack(targets);
    }

    @Override
    public void attack(List<IAttackable> targets) {
        for (IAttackable target : targets) {
            if (collisionManager.isCollision(getBbox(), target.getBbox())) {
                delay++;
                if (delay > 30) {
                    setAttack();
                    AudioManager.getInstance().playSound("zombie", 1f);
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
        // Prioriser la première cible dans la liste
        if (!targets.isEmpty()) {
            IAttackable target = targets.get(0); // Exemple simpliste : viser la première cible

            Vector2 targetPosition = new Vector2(target.getBbox().x , target.getBbox().y);
            direction = moveStrategy.followPath(this, targetPosition);
            nextPosition.x = position.x + direction.x * speed * dt;
            nextPosition.y = position.y + direction.y * speed * dt;
            setPosition(nextPosition);
        }
    }
}
