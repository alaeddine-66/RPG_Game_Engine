package com.engine.model.entity.components;

public class CoinPurse implements IComponent{
    private int coins;

    public CoinPurse() {
        this.coins = 0;
    }

    public void addCoins(int amount) {
        if (amount > 0) {
            coins += amount;
        }
    }

    public void removeCoins(int amount) {
        if (amount > 0 && coins >= amount) {
            coins -= amount;
        }
    }

    public int getCoins() {
        return coins;
    }

}
