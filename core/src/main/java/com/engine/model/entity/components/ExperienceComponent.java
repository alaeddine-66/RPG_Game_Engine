package com.engine.model.entity.components;


import com.engine.model.ObserverPattern.Event;
import com.engine.model.ObserverPattern.IObservable;
import com.engine.model.ObserverPattern.Observable;
import com.engine.model.ObserverPattern.Observer;
import com.engine.model.entity.components.strategies.IExperienceCalculationStrategy;

import java.util.ArrayList;
import java.util.List;

public class ExperienceComponent extends Observable implements  IComponent, ILevelUpComponent, Observer {
    private int exp;
    private int maxExp;
    private int level;
    private boolean levelUpFlag = false ;
    private IExperienceCalculationStrategy calculationStrategy;

    public ExperienceComponent(IExperienceCalculationStrategy strategy , int maxExp) {
        this.level = 1;
        this.calculationStrategy = strategy;
        this.maxExp = maxExp;
        this.exp = 0;
    }

    public void addExp(int amount) {
        exp += amount;
        if (exp >= maxExp) {
            int remainder = exp - maxExp;
            notifyObservers(new LevelUpEvent(remainder));
        }
    }

    @Override
    public void onLevelUp(int remainder) {
        level++;
        setLeveledUpFlag();
        maxExp = calculationStrategy.calculateMaxExp(level, maxExp);
        this.exp = remainder;
    }

    @Override
    public void update(Event event) {
        // Déléguer la gestion directement à l'événement
        event.handle(this);
    }

    public int getLevel() {
        return level;
    }

    public int getExp() {
        return exp;
    }

    public int getMaxExp() {
        return maxExp;
    }

    public void setCalculationStrategy(IExperienceCalculationStrategy calculationStrategy){
        this.calculationStrategy = calculationStrategy ;
    }

    public boolean hasLeveledUp() {
        return levelUpFlag;
    }

    public void setLeveledUpFlag() {
        this.levelUpFlag = true;
    }

    public void resetLevelUpFlag(){
        this.levelUpFlag = false ;
    }

}
