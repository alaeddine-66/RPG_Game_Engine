package com.engine.model.collectible;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.engine.model.entity.player.Player;


/**
 * Represents an item dropped in the game world that can be collected by the player.
 * <p>
 * A {@code DroppedItem} has a position in the game world, an associated {@link IDroppable} object
 * (such as coins or experience points), and a state indicating whether it has been collected.
 * </p>
 */
public class DroppedItem {

    /**
     * The position of the dropped item in the game world.
     */
    private final Vector2 position;

    /**
     * The droppable object associated with this dropped item (e.g., coin, experience).
     */
    private final IDroppable droppable;

    /**
     * Indicates whether the item has been collected.
     */
    private boolean isCollected;

    /**
     * Constructs a new {@code DroppedItem} with the specified position and droppable object.
     *
     * @param position  the position of the item in the game world.
     * @param droppable the {@link IDroppable} object associated with this item.
     */
    public DroppedItem(Vector2 position, IDroppable droppable) {
        this.position = position;
        this.droppable = droppable;
        this.isCollected = false;
    }

    /**
     * Retrieves the position of the dropped item.
     *
     * @return the item's position as a {@link Vector2}.
     */
    public Vector2 getPosition() {
        return position;
    }

    /**
     * Moves the dropped item towards a target position.
     * <p>
     * The movement speed is determined by the speed specified in the item's {@link IDroppable#getItemData()}.
     * The movement is frame-dependent, ensuring smooth animation.
     * </p>
     *
     * @param target the target position to move towards.
     */
    public void moveTo(Vector2 target) {
        Vector2 direction = target.cpy().sub(position).nor();
        position.x += direction.x * droppable.getItemData().getSpeed() * Gdx.graphics.getDeltaTime();
        position.y += direction.y * droppable.getItemData().getSpeed() * Gdx.graphics.getDeltaTime();
    }

    /**
     * Retrieves the droppable object associated with this item.
     *
     * @return the associated {@link IDroppable}.
     */
    public IDroppable getdrop() {
        return droppable;
    }

    /**
     * Checks if the item has been collected.
     *
     * @return {@code true} if the item has been collected, {@code false} otherwise.
     */
    public boolean isCollected() {
        return isCollected;
    }

    /**
     * Collects the item and applies its effect to the specified player.
     * <p>
     * Once collected, the item's effect is applied to the player, and its state is set as collected.
     * </p>
     *
     * @param player the {@link Player} collecting the item.
     */
    public void collect(Player player) {
        if (!isCollected) {
            droppable.applyEffect(player); // Apply the effect to the player.
            isCollected = true;
        }
    }
}
