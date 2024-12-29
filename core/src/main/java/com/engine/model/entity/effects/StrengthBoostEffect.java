package com.engine.model.entity.effects;

import com.engine.model.entity.player.Player;

public class StrengthBoostEffect implements IEffect {
    private final int boostAmount;
    private final Player player ;

    public StrengthBoostEffect(int boostAmount, Player player) {
        this.boostAmount = boostAmount;
        this.player = player ;
    }

    @Override
    public void applyEffect() {
        player.getStrengthComponent().boostStrength(boostAmount);
    }
}
