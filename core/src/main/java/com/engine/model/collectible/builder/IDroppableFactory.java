package com.engine.model.collectible.builder;

import com.engine.model.collectible.IDroppable;

/**
 * Interface for creating instances of {@link IDroppable}.
 * <p>
 * This factory provides a method to build objects implementing the {@link IDroppable}
 * interface. It ensures that the creation process is encapsulated and can be extended
 * to support different types of droppable objects.
 * </p>
 */
public interface IDroppableFactory {

    /**
     * Builds an instance of {@link IDroppable}.
     *
     * @return a new instance of an object implementing {@link IDroppable}.
     */
    IDroppable build();
}
