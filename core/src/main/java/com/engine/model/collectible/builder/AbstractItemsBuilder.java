package com.engine.model.collectible.builder;

import com.engine.model.data.ItemData;

/**
 * Abstract builder class for creating items that implement the {@link com.engine.model.collectible.IDroppable} interface.
 * <p>
 * This class provides a base implementation for building items with specific properties such as value and data.
 * Concrete subclasses must implement the {@link IDroppableFactory#build()} method to create specific instances of {@link com.engine.model.collectible.IDroppable}.
 * </p>
 */
public abstract class AbstractItemsBuilder implements IDroppableFactory {

    /**
     * The value of the item to be built.
     */
    protected int value;

    /**
     * The data associated with the item to be built.
     */
    protected ItemData data;

    /**
     * Sets the value of the item.
     *
     * @param value the value to assign to the item.
     * @return the current instance of {@code AbstractItemsBuilder} for method chaining.
     */
    public AbstractItemsBuilder withValue(int value) {
        this.value = value;
        return this;
    }

    /**
     * Sets the data of the item.
     *
     * @param data the {@link ItemData} to associate with the item.
     * @return the current instance of {@code AbstractItemsBuilder} for method chaining.
     */
    public AbstractItemsBuilder withData(ItemData data) {
        this.data = data;
        return this;
    }
}
