package com.engine.model.entity.components.hitBox;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.List;

public class RectangleHitBox extends HitBox {
    private final Rectangle rectangle;

    public RectangleHitBox(Vector2 position, Vector2 size, CollisionStrategy collisionStrategy) {
        super(position, size, collisionStrategy);
        this.rectangle = new Rectangle(position.x, position.y, size.x, size.y);
    }

    @Override
    public boolean contains(Vector2 point) {
        return rectangle.contains(point.x, point.y);
    }

    @Override
    public HitBox copy(float x, float y) {
        return new RectangleHitBox(new Vector2(x, y),getSize() , collisionStrategy);
    }

    @Override
    public List<int[]> getOccupiedTiles(int tileWidth, int tileHeight) {
        List<int[]> objectCoordinates = new ArrayList<>();
        int tileCountX = (int) Math.ceil(rectangle.width / tileWidth);
        int tileCountY = (int) Math.ceil(rectangle.height / tileHeight);

        for (int i = 0; i < tileCountX; i++) {
            for (int j = 0; j < tileCountY; j++) {
                int colIndex = (int) Math.floor((rectangle.x + i * tileWidth) / tileWidth);
                int rowIndex = (int) Math.floor((rectangle.y + j * tileHeight) / tileHeight);

                objectCoordinates.add(new int[]{colIndex, rowIndex});
            }
        }
        return objectCoordinates;
    }

    @Override
    public void setPosition(Vector2 position) {
        rectangle.setPosition(position.x, position.y);
    }

    @Override
    public void setSize(Vector2 size) {
        rectangle.setSize(size.x, size.y);
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

}
