package com.engine.model.collectible;

import com.engine.model.data.ItemData;

public abstract class Collectible implements IDroppable{
    protected final int value;
    protected final ItemData data;

    public Collectible(int value , ItemData data) {
        this.value = value;
        this.data = data;
    }

    @Override
    public ItemData getItemData(){
        return data;
    }

    public int getValue() {
        return value;
    }

}
