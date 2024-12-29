package com.engine.model.entity.enemy.model;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.engine.model.entity.IAttackable;
import com.engine.model.entity.IAttacker;
import com.engine.model.entity.components.HealthComponent;
import com.engine.model.entity.components.StrengthComponent;
import com.engine.model.map.IMapCollisionChecker;
import com.engine.model.entity.Entity;
import com.engine.model.data.EnemyData;
import com.engine.model.pathFinder.IMoveStrategy;

import java.util.List;

/**
 * An Entity that the Player can battle if encountered
 */
public abstract class Enemy extends Entity implements IAttackable , IAttacker {

    protected IMapCollisionChecker collisionManager;
    protected IMoveStrategy moveStrategy;
    protected Vector2 direction ;
    private final EnemyData data;
    protected Vector2 nextPosition ;
    protected HealthComponent health ;
    protected StrengthComponent strengthComponent;

    public Enemy(Vector2 position ,  IMapCollisionChecker collisionManager ,  EnemyData data) {
        super(data.getId() ,position, new Vector2 (data.getWidth() , data.getHeight() ));
        this.boundingBox = new Rectangle(position.x, position.y, data.getWidth(), data.getHeight());
        this.collisionManager = collisionManager;
        this.data = data ;
        this.nextPosition = new Vector2();
        this.speed = data.getSpeed();
        this.health = new HealthComponent(data.getmaxHp());
        this.strengthComponent = new StrengthComponent(data.getDamage());
    }

    /**
     * Met à jour l'état de l'ennemi.
     *
     * @param targets Liste des cibles potentielles.
     * @param dt      Temps écoulé depuis la dernière mise à jour.
     */
    public abstract void update(List<IAttackable> targets, float dt);

    /**
     * Permet à l'ennemi d'attaquer.
     *
     * @param targets Liste des cibles potentielles.
     */
    public abstract void attack(List<IAttackable> targets);

    /**
     * Permet à l'ennemi de se déplacer.
     *
     * @param targets Liste des cibles potentielles.
     * @param dt      Temps écoulé depuis la dernière mise à jour.
     */
    public abstract void move(List<IAttackable> targets, float dt);

    public void setPosition(Vector2 nextPosition) {
        updateBoundingBox();
        if (!collisionManager.isInRestrictedZone(boundingBox)) {
            this.position = nextPosition;
        } else {
            collisionManager.resolveCollisions(position, direction, (int) boundingBox.width, (int) boundingBox.height);
        }
    }

    public Vector2 getDirection(){
        return direction;
    }

    public void setdirection(Vector2 direction){
        this.direction = direction ;
    }

    public EnemyData getData(){
        return data;
    }

    public void SetMoveStrategy(IMoveStrategy moveStrategy) {
        this.moveStrategy = moveStrategy;
    }

    @Override
    public HealthComponent getHealthComponent() {
        return health;
    }

    @Override
    public StrengthComponent getStrengthComponent(){
        return strengthComponent ;
    }
}
