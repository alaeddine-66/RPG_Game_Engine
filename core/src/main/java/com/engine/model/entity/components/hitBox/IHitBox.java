package com.engine.model.entity.components.hitBox;

import com.badlogic.gdx.math.Vector2;

import java.util.List;

public interface IHitBox {

    boolean intersects(HitBox other);

    boolean contains(Vector2 point);

    HitBox copy(float x, float y);

    List<int[]> getOccupiedTiles(int tileWidth, int tileHeight);


}
