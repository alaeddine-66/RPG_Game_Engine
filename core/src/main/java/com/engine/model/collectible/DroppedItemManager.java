package com.engine.model.collectible;

import com.badlogic.gdx.math.Vector2;
import com.engine.model.entity.player.Player;
import com.engine.model.collectible.builder.AbstractItemsBuilder;
import com.engine.model.resource.DataManager;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


/**
 * Manages dropped items in the game world.
 * <p>
 * This class handles the creation, tracking, and interaction of dropped items
 * such as coins, experience points, or other collectible objects. It also manages
 * their behavior when interacting with the player.
 * </p>
 */
public class DroppedItemManager {

    /**
     * The list of all dropped items currently in the game world.
     */
    private final List<DroppedItem> droppedItems = new ArrayList<>();

    /**
     * Adds a new dropped item to the manager.
     *
     * @param item the {@link DroppedItem} to add.
     */
    public void addDroppedItem(DroppedItem item) {
        droppedItems.add(item);
    }

    /**
     * Drops items from a defeated enemy at a specified position.
     * <p>
     * For each drop defined in the {@code drops} map, a corresponding {@link DroppedItem}
     * is created using the appropriate factory and added to the manager.
     * </p>
     *
     * @param position the position where the items are dropped.
     * @param drops    a map where keys are item types and values are their quantities.
     */
    public void dropItemsFromEnemy(Vector2 position, Map<String, Integer> drops) {
        for (Map.Entry<String, Integer> entry : drops.entrySet()) {
            String type = entry.getKey();
            int value = entry.getValue();

            AbstractItemsBuilder factory = DataManager.getInstance().getItemFactory(type);
            if (factory != null && value > 0) {
                IDroppable droppable = factory
                    .withValue(value)
                    .withData(DataManager.getInstance().getItemData(type))
                    .build();
                addDroppedItem(new DroppedItem(position, droppable));
            }
        }
    }

    /**
     * Updates the state of all dropped items and checks for interactions with the player.
     * <p>
     * Dropped items move towards the player if they are within a certain distance.
     * If an item reaches the player, it is collected, its effects are applied, and it is removed.
     * </p>
     *
     * @param player the {@link Player} interacting with the dropped items.
     */
    public void update(Player player) {
        Iterator<DroppedItem> iterator = droppedItems.iterator();
        while (iterator.hasNext()) {
            DroppedItem item = iterator.next();
            if (item.getPosition().dst(player.getPosition()) < 100.0f) { // If near
                item.moveTo(player.getPosition());
                if (item.getPosition().dst(player.getPosition()) < 1.0f) {
                    item.collect(player);
                    iterator.remove();
                }
            }
        }
    }

    /**
     * Retrieves the list of all currently managed dropped items.
     *
     * @return a list of {@link DroppedItem} objects.
     */
    public List<DroppedItem> getDroppedItems() {
        return droppedItems;
    }
}
