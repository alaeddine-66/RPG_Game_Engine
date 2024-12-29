package com.engine.model.collectible.builder;

import com.engine.model.data.ItemData;

public abstract class AbstractItemsBuilder implements IDroppableFactory{
    protected int value ;
    protected ItemData data ;

    public AbstractItemsBuilder withValue(int value){
        this.value = value;
        return this;
    }

    public AbstractItemsBuilder withData(ItemData data){
        this.data = data;
        return this;
    }

}
