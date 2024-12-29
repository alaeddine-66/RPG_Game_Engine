package com.engine.view.hud;


import java.util.ArrayList;
import java.util.List;

public class HUDManager {

    private final List<AbstractHUDComponents> components;

    public HUDManager() {
        this.components = new ArrayList<>();
    }

    public void addComponent(AbstractHUDComponents component) {
        components.add(component);
    }

    public void render() {
        for (AbstractHUDComponents component : components) {
            component.render();
        }
    }
}
