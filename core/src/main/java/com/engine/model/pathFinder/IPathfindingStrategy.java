package com.engine.model.pathFinder;


import com.badlogic.gdx.math.Vector2;

import java.util.List;

public interface IPathfindingStrategy {
    List<Vector2> findPath(Node start, Node End, int entityWidth, int entityHeight);
}
