package com.engine.model.entity.components.hitBox;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;

import java.util.List;
import java.util.ArrayList;

public class CircleHitBox extends HitBox {
    private Vector2 center;
    private float radius;
    private Circle circle;

    public CircleHitBox(Vector2 center, float radius, CollisionStrategy collisionStrategy) {
        super(center, new Vector2(radius * 2, radius * 2), collisionStrategy);
        this.circle = new Circle(center , radius);
        this.radius = radius;
    }

    public Circle getCircle(){
        return circle;
    }

    @Override
    public HitBox copy(float x, float y) {
        return new CircleHitBox(new Vector2(x, y), this.radius , collisionStrategy);
    }

    @Override
    public List<int[]> getOccupiedTiles(int tileWidth, int tileHeight) {
        List<int[]> objectCoordinates = new ArrayList<>();

        // Déterminer les limites des tuiles à vérifier
        int minTileX = (int) Math.floor((circle.x - circle.radius) / tileWidth);
        int maxTileX = (int) Math.floor((circle.x + circle.radius) / tileWidth);
        int minTileY = (int) Math.floor((circle.y - circle.radius) / tileHeight);
        int maxTileY = (int) Math.floor((circle.y + circle.radius) / tileHeight);

        // Parcourir les tuiles dans la zone d'influence du cercle
        for (int tileX = minTileX; tileX <= maxTileX; tileX++) {
            for (int tileY = minTileY; tileY <= maxTileY; tileY++) {
                // Calculer les coordonnées du centre de la tuile
                float tileCenterX = (tileX + 0.5f) * tileWidth;
                float tileCenterY = (tileY + 0.5f) * tileHeight;

                // Vérifier si la tuile est à l'intérieur du cercle
                if (isTileInsideCircle(tileCenterX, tileCenterY, circle.x, circle.y, circle.radius)) {
                    objectCoordinates.add(new int[]{tileX, tileY});
                }
            }
        }

        return objectCoordinates;
    }

    /**
     * Vérifie si une tuile est à l'intérieur du cercle.
     *
     * @param tileCenterX Centre X de la tuile.
     * @param tileCenterY Centre Y de la tuile.
     * @param circleX     Centre X du cercle.
     * @param circleY     Centre Y du cercle.
     * @param radius      Rayon du cercle.
     * @return true si la tuile est à l'intérieur du cercle, false sinon.
     */
    private boolean isTileInsideCircle(float tileCenterX, float tileCenterY, float circleX, float circleY, float radius) {
        // Calculer la distance entre le centre de la tuile et le centre du cercle
        float dx = tileCenterX - circleX;
        float dy = tileCenterY - circleY;
        float distanceSquared = dx * dx + dy * dy;

        // Vérifier si la distance est inférieure ou égale au rayon
        return distanceSquared <= (radius * radius);
    }

    public Vector2 getCenter() {
        return center;
    }

    public float getRadius() {
        return radius;
    }

    @Override
    public boolean contains(Vector2 point) {
        return circle.contains(point);
    }

}
