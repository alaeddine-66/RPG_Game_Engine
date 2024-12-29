package com.engine.model.pathFinder;

import com.badlogic.gdx.math.Vector2;
import com.engine.model.entity.Entity;
import com.engine.model.map.IMapCollisionChecker;

import java.util.List;

public class PathfindingMoveStrategy implements IMoveStrategy {
    private  IPathfindingStrategy pathfindingStrategy;
    private final IMapCollisionChecker collisionChecker ;
    private Vector2 nextStep;

    public PathfindingMoveStrategy(IPathfindingStrategy pathfindingStrategy , IMapCollisionChecker collisionChecker) {
        this.pathfindingStrategy = pathfindingStrategy;
        this.collisionChecker = collisionChecker ;
        nextStep = new Vector2();
    }

    @Override
    public Vector2 followPath(Entity enemy, Vector2 target) {
        List<Vector2> path = getPath(enemy, target);

        if (path == null || path.size()<2) {
            return new Vector2(0, 0); // Pas de déplacement si aucun chemin.
        }

        nextStep = path.get(1);

        nextStep.x *= collisionChecker.getTileWidth();
        nextStep.y *= collisionChecker.getTileHeight();

        // Calculer la direction normalisée
        return nextStep.sub(enemy.getPosition().cpy()).nor();
    }


    public List<Vector2> getPath(Entity enemy, Vector2 target) {
        Node startNode = new Node(
            (int) (enemy.getPosition().cpy().x / collisionChecker.getTileWidth()),
            (int) (enemy.getPosition().cpy().y / collisionChecker.getTileHeight())
        );
        Node endNode = new Node(
            (int) (target.x / collisionChecker.getTileWidth()),
            (int) (target.y / collisionChecker.getTileHeight())
        );

        return pathfindingStrategy.findPath(startNode, endNode, (int) enemy.getBbox().width, (int) enemy.getBbox().height);
    }


    public void setPathfindingMoveStrategy(IPathfindingStrategy pathfindingStrategy){
        this.pathfindingStrategy = pathfindingStrategy ;
    }
}
