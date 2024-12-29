package com.engine.model.collectible;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.engine.model.entity.player.Player;

public class DroppedItem {
    private final Vector2 position; // Position dans le monde
    private final IDroppable droppable; // L'objet associé (ex. : Coin, Experience, etc.)
    private boolean isCollected; // Indique si l'objet a été ramassé

    public DroppedItem(Vector2 position, IDroppable droppable) {
        this.position = position;
        this.droppable = droppable;
        this.isCollected = false;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void moveTo(Vector2 target){
        Vector2 direction = target.cpy().sub(position).nor();
        position.x += direction.x * droppable.getItemData().getSpeed() * Gdx.graphics.getDeltaTime() ;
        position.y += direction.y * droppable.getItemData().getSpeed() * Gdx.graphics.getDeltaTime() ;
    }

    public IDroppable getdrop(){
        return droppable;
    }

    public boolean isCollected() {
        return isCollected;
    }

    public void collect(Player player) {
        if (!isCollected) {
            droppable.applyEffect(player); // Appliquer l'effet au joueur
            isCollected = true;
        }
    }
}
