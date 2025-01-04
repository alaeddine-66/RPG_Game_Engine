package com.engine.model.pathFinder;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.engine.model.entity.Entity;
import com.engine.model.entity.components.hitBox.HitBox;
import com.engine.model.map.IMapCollisionChecker;

public class SteeringBehaviorStrategy implements IMoveStrategy {
    private final IMapCollisionChecker collisionManager;

    public SteeringBehaviorStrategy(IMapCollisionChecker collisionManager) {
        this.collisionManager = collisionManager;
    }

    @Override
    public Vector2 followPath(Entity enemy, Vector2 target) {
        // Étape 1 : Calculer la direction vers le joueur
        Vector2 targetDirection = target.cpy().sub(enemy.getPosition()).nor();

        // Étape 2 : Calculer la prochaine position
        float AheadX = enemy.getPosition().x + targetDirection.x * enemy.getSpeed() * Gdx.graphics.getDeltaTime() ;
        float AheadY = enemy.getPosition().y + targetDirection.y * enemy.getSpeed() * Gdx.graphics.getDeltaTime();
        HitBox nextPosition = enemy.getBbox().copy(enemy.getPosition().x , enemy.getPosition().y);

        // Étape 3 : Vérifier les collisions et les bordures
        boolean collisionWithObstacle = collisionManager.checkCollisionWithObjects(nextPosition);
        boolean outOfBounds = collisionManager.isOutOfBounds(nextPosition);

        if (!collisionWithObstacle && !outOfBounds) {
            // Pas d'obstacle : Retourner la direction normale
            return targetDirection;
        } else {
            // Collision détectée : Calculer une direction d'évitement
            return calculateAvoidanceDirection(targetDirection);
        }
    }

    /**
     * Calculer une direction d'évitement en fonction de la direction actuelle.
     */
    private Vector2 calculateAvoidanceDirection(Vector2 currentDirection) {
        // Initialiser une direction d'évitement basique (à 90° de la direction actuelle)
        return new Vector2(-currentDirection.y, currentDirection.x);
    }
}
