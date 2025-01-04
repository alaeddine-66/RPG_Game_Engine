package com.game.model.entity.enemy.model;

import com.badlogic.gdx.math.Vector2;
import com.engine.model.entity.components.hitBox.HitBox;
import com.engine.model.entity.enemy.model.Enemy;
import com.engine.model.entity.enemy.model.HasProjectile;
import com.engine.model.map.IMapCollisionChecker;
import com.engine.model.data.EnemyData;
import com.engine.model.pathFinder.SteeringBehaviorStrategy;
import com.engine.model.resource.DataManager;
import com.engine.model.projectile.model.AbstractProjectile;
import com.engine.model.projectile.factory.ProjectileFactory;
import com.engine.model.entity.IAttackable;


import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

public class Magician extends Enemy implements HasProjectile {

    private int fireballCooldown = 0;
    private final List<AbstractProjectile> fireballlist;

    public Magician(Vector2 position, IMapCollisionChecker collisionManager, EnemyData data, HitBox hitBox) {
        super(position, collisionManager, data, hitBox);
        this.fireballlist = new ArrayList<>();
        SetMoveStrategy(new SteeringBehaviorStrategy(collisionManager));
    }

    @Override
    public void update(List<IAttackable> targets, float dt) {
        move(targets, dt);
        attack(targets);
    }

    @Override
    public void attack(List<IAttackable> targets) {
        fireballCooldown++;
        updateProjectile(targets);
        if (fireballCooldown > 60) {
            for (IAttackable target : targets) {
                launchFireball(target.getBbox().getPosition());
            }
            fireballCooldown = 0;
        }
    }

    @Override
    public void move(List<IAttackable> targets, float dt) {
        if (!targets.isEmpty()) {
            IAttackable target = targets.get(0);
            Vector2 targetPosition = target.getBbox().getPosition().cpy();
            float distance = position.cpy().dst(targetPosition);
            float MIN_DISTANCE = 300;

            if (distance < MIN_DISTANCE) {
                direction = position.cpy().sub(targetPosition).nor();
            } else {
                direction = moveStrategy.followPath(this, targetPosition);
            }

            nextPosition.x = position.cpy().x + direction.x * speed * dt;
            nextPosition.y = position.cpy().y + direction.y * speed * dt;
            setPosition(nextPosition);
        }
    }

    public void updateProjectile(List<IAttackable> targets ){
        for (IAttackable target : targets) {
            Iterator<AbstractProjectile> iterator = fireballlist.iterator();
            while (iterator.hasNext()) {
                AbstractProjectile projectile = iterator.next();

                if (projectile.isActive()) {
                    // Appliquer le comportement du projectile
                    projectile.update();
                }

                if (target.getBbox().intersects(projectile.getBbox())) {
                    target.getHealthComponent().damageHp(getStrengthComponent().getStrength()); // Utilisez la valeur damage de l'arme
                    projectile.deactivate();
                }

                if (collisionManager.checkCollisionWithObjects(projectile.getBbox()) || collisionManager.isOutOfBounds(projectile.getBbox())) {
                    projectile.deactivate();
                }

                // Retirer le projectile s'il est désactivé
                if (!projectile.isActive()) {
                    iterator.remove();
                }
            }
        }
    }

    private void launchFireball(Vector2 targetPosition) {
        Vector2 projectileDirection = new Vector2(targetPosition.cpy().sub(position.cpy()).nor());
        ProjectileFactory factory = DataManager.getInstance().getProjectileFactory("FireBall");
        Vector2 fireBallSize = new Vector2(factory.getData().getWidth(), factory.getData().getHeight());
        AbstractProjectile fireball = factory.createProjectile(position.cpy(), projectileDirection , factory.getHitBoxFactory().createHitBox(position , fireBallSize ));
        fireball.setSpeed(fireball.getSpeed() / 2);
        fireballlist.add(fireball);
    }

    @Override
    public List<AbstractProjectile> getProjectileList() {
        return fireballlist;
    }
}

