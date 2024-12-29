package com.engine.model.collectible;

import com.badlogic.gdx.math.Vector2;
import com.engine.model.entity.player.Player;
import com.engine.model.collectible.builder.AbstractItemsBuilder;
import com.engine.model.resource.DataManager;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class DroppedItemManager {
    private final List<DroppedItem> droppedItems = new ArrayList<>();


    public void addDroppedItem(DroppedItem item) {
        droppedItems.add(item);
    }

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


    public void update(Player player) {
        Iterator<DroppedItem> iterator = droppedItems.iterator();
        while (iterator.hasNext()) {
            DroppedItem item = iterator.next();
            if (item.getPosition().dst(player.getPosition()) < 100.0f) { // Si proche
                item.moveTo(player.getPosition());
                if (item.getPosition().dst(player.getPosition()) < 1.0f) {
                    item.collect(player);
                    iterator.remove();
                }
            }
        }
    }

    public List<DroppedItem> getDroppedItems() {
        return droppedItems;
    }
}

