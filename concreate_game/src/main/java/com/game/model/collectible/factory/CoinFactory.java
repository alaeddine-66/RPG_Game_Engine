package com.game.model.collectible.factory;

import com.game.model.collectible.Coin;
import com.engine.model.collectible.IDroppable;
import com.engine.model.collectible.builder.AbstractItemsBuilder;

public class CoinFactory extends AbstractItemsBuilder {
    @Override
    public IDroppable build() {
        return new Coin(value , data);
    }
}
