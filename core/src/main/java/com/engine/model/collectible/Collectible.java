package com.engine.model.collectible;

import com.engine.model.data.ItemData;

/**
 * Abstract base class representing a collectible item in the game.
 * <p>
 * This class implements the {@link IDroppable} interface and provides common
 * functionality for all collectible items, such as storing the item's value
 * and associated data.
 * </p>
 */
public abstract class Collectible implements IDroppable {

    /**
     * The value of the collectible item.
     */
    protected final int value;

    /**
     * The data associated with the collectible item.
     */
    protected final ItemData data;

    /**
     * Constructs a new {@code Collectible} with the specified value and data.
     *
     * @param value the value of the collectible.
     * @param data  the {@link ItemData} associated with the collectible.
     */
    public Collectible(int value, ItemData data) {
        this.value = value;
        this.data = data;
    }

    /**
     * Retrieves the {@link ItemData} associated with the collectible.
     *
     * @return the item's {@link ItemData}.
     */
    @Override
    public ItemData getItemData() {
        return data;
    }

    /**
     * Retrieves the value of the collectible.
     *
     * @return the item's value.
     */
    public int getValue() {
        return value;
    }
}
