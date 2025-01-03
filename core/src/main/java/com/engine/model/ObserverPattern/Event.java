package com.engine.model.ObserverPattern;

import com.engine.model.entity.components.ILevelUpComponent;

public abstract class Event {
    private final String type;

    public Event(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    // Méthode abstraite pour traiter un événement
    public abstract void handle(ILevelUpComponent component);

}
