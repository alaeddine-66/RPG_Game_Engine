package com.engine.model.pathFinder;


import com.badlogic.gdx.math.Vector2;
import com.engine.model.entity.Entity;

public interface IMoveStrategy {
    Vector2 followPath(Entity enemy , Vector2 target);
}
