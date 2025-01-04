package com.engine.model.map.MapObjects;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.engine.model.entity.components.hitBox.CollisionStrategy;
import com.engine.model.entity.components.hitBox.HitBox;
import com.engine.model.entity.components.hitBox.RectangleCollisionStrategy;
import com.engine.model.entity.components.hitBox.RectangleHitBox;

public class RectangleHitBoxGenerationStrategy implements HitBoxGenerationStrategy {

    @Override
    public HitBox createHitBox(MapObject mapObject) {
        RectangleMapObject rectangleMapObject = (RectangleMapObject) mapObject;
        Rectangle rect = rectangleMapObject.getRectangle();
        CollisionStrategy collisionStrategy = new RectangleCollisionStrategy();
        return new RectangleHitBox(new Vector2(rect.x,rect.y) , new Vector2(rect.width , rect.height) , collisionStrategy);
    }


}
