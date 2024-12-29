package com.engine.model.entity.effects;

import com.engine.model.entity.player.Player;

public class HealEffect implements IEffect {
    private final int healAmount;
    private final Player player ;

    public HealEffect(Player player , int healAmount) {
        this.healAmount = healAmount;
        this.player = player ;
    }

    @Override
    public void applyEffect() {
        player.getHealthComponent().heal(healAmount);
    }
}
