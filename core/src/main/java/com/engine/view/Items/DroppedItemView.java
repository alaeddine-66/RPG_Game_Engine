package com.engine.view.Items ;


import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Texture;
import com.engine.model.collectible.DroppedItem;
import com.engine.model.collectible.DroppedItemManager;

import java.util.List;


public class DroppedItemView  {
    private final SpriteBatch batch ;
    private final List<DroppedItem>  droppedItems;

    public DroppedItemView(SpriteBatch batch , List<DroppedItem> droppedItems){
        this.batch = batch;
        this.droppedItems = droppedItems;
    }

    public void render() {
        batch.begin();
        for (DroppedItem item : droppedItems) {
            Texture texture = new Texture(item.getdrop().getItemData().getPath());
            if (!item.isCollected()) {
                batch.draw(texture, item.getPosition().x, item.getPosition().y,
                    item.getdrop().getItemData().getWidth() , item.getdrop().getItemData().getHeight());
            }
        }
        batch.end();
    }

}
