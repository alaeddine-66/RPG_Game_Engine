package com.engine.model.entity;

import java.util.List;

import com.engine.model.entity.effects.IEffect;

public class Item {
    private final String name;
    private final String category;
    private final String description;
    private final List<IEffect> effects; // Liste des effets de l'item

    public Item(String name, String category, int value, String description, List<IEffect> effects) {
        this.name = name;
        this.category = category;
        this.description = description;
        this.effects = effects;
    }

    public void use() {
        for (IEffect effect : effects) {
            effect.applyEffect();
        }
        System.out.println(name + " a été utilisé.");
    }

    // Getters pour accéder aux propriétés
    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }
}
