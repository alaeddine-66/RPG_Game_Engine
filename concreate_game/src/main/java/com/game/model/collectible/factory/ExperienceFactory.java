package com.game.model.collectible.factory;

import com.engine.model.collectible.IDroppable;
import com.game.model.collectible.Experience;
import com.engine.model.collectible.builder.AbstractItemsBuilder;

public class ExperienceFactory extends AbstractItemsBuilder {
    @Override
    public IDroppable build() {
        return new Experience(value , data);
    }
}
