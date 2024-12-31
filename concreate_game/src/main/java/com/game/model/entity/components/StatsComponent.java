package com.game.model.entity.components;

import com.engine.model.ObserverPattern.Event;
import com.engine.model.ObserverPattern.Observer;
import com.engine.model.data.PlayerData;
import com.engine.model.entity.components.IComponent;
import com.engine.model.entity.components.ILevelUpComponent;
import com.engine.model.entity.components.LevelUpEvent;

public class StatsComponent implements IComponent, ILevelUpComponent, Observer {
    private int mp, maxMp, fatigue, luck , statPoints;
    private String job , title;
    public static final int DEFAULT_STAT_POINTS = 3;


    public StatsComponent(PlayerData data) {

        this.mp = data.getmaxMp();
        this.maxMp = data.getmaxMp();
        this.fatigue = 0;
        this.luck = 1;
        this.statPoints = 0;
        this.job = null;
        this.title = null;
    }

    @Override
    public void onLevelUp(int remainder) {
        increaseStatPoints();
    }

    @Override
    public void update(Event event) {
        event.handle(this);
    }

    public int getStatPoints(){
        return statPoints;
    }

    public void setStatPoints(int statPoints){
        this.statPoints = statPoints;
    }

    public void addStatPoints(int amount){
        this.statPoints +=amount;
    }

    public void increaseStatPoints(){
        this.statPoints +=DEFAULT_STAT_POINTS;
    }

    public int getMp() {
        return mp;
    }

    public void setMp(int mp) {
        this.mp = mp;
    }

    public int getMaxMp() {
        return maxMp;
    }

    public void setMaxMp(int maxMp){
        this.maxMp = maxMp;
    }

    public int getFatigue() {
        return fatigue;
    }

    public void setFatigue(int fatigue) {
        this.fatigue = fatigue;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getLuck() {
        return luck;
    }

    public void setLuck(int luck) {
        this.luck = luck;
    }

}
