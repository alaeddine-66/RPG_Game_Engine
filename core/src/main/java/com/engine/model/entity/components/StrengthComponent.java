package com.engine.model.entity.components;

import com.badlogic.gdx.math.MathUtils;
import com.engine.model.ObserverPattern.Event;
import com.engine.model.ObserverPattern.Observer;

public class StrengthComponent implements IComponent , ILevelUpComponent, Observer {
    private int minDmgIncrease;
    private int maxDmgIncrease;
    private int strength ;

    public StrengthComponent(int strength , int minDmgIncrease, int maxDmgIncrease) {
        this.minDmgIncrease = minDmgIncrease;
        this.maxDmgIncrease = maxDmgIncrease;
        this.strength = strength;
    }

    public StrengthComponent(int damage) {
        this.strength = damage;
    }

    @Override
    public void onLevelUp(int remainder) {
        int dmgMean = MathUtils.random(minDmgIncrease, maxDmgIncrease);
        strength += dmgMean;
        minDmgIncrease += (dmgMean - MathUtils.random(1));
        maxDmgIncrease += (dmgMean + MathUtils.random(1));
    }

    @Override
    public void update(Event event) {
        // Déléguer la gestion directement à l'événement
        event.handle(this);
    }

    public void boostStrength(int amount ){
        strength += amount ;
    }

    public int getMinDmgIncrease() {
        return minDmgIncrease;
    }

    public int getMaxDmgIncrease() {
        return maxDmgIncrease;
    }

    public int getStrength(){
        return strength;
    }

    public void setStrength(int strength){
        this.strength = strength ;
    }

}
