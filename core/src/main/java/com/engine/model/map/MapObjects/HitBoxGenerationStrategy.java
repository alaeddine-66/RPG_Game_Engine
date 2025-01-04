package com.engine.model.map.MapObjects;

import com.badlogic.gdx.maps.MapObject;
import com.engine.model.entity.components.hitBox.HitBox;

public interface HitBoxGenerationStrategy {
    HitBox createHitBox(MapObject mapObject);

}
