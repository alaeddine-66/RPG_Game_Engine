package com.game.model.collectible;

import com.engine.model.collectible.Collectible;
import com.engine.model.data.ItemData;
import com.engine.model.entity.player.Player;
import com.engine.model.entity.components.CoinPurse;

public class Coin extends Collectible {

    public Coin(int value , ItemData data) {
        super(value,data);
    }

    @Override
    public void applyEffect(Player player) {
        player.getComponent(CoinPurse.class).addCoins(value); // Augmente l'argent du joueur
    }


}

