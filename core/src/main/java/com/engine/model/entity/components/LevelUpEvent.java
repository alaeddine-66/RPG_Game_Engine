package com.engine.model.entity.components;

import com.engine.model.ObserverPattern.Event;

public class LevelUpEvent extends Event {
    private final int remainingExp;

    public LevelUpEvent(int remainingExp) {
        super("LevelUp");
        this.remainingExp = remainingExp;
    }

    public int getRemainingExp() {
        return remainingExp;
    }

    @Override
    public void handle(ILevelUpComponent component) {
        component.onLevelUp(remainingExp); // Appelle directement la méthode appropriée
    }

}
