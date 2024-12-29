package com.engine.model.collectible;

import com.engine.model.data.ItemData;
import com.engine.model.entity.player.Player;

public interface IDroppable {
    void applyEffect(Player player); // Applique les effets de l'objet au joueur
    ItemData getItemData();
}
