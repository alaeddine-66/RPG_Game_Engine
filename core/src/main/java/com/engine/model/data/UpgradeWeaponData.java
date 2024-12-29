package com.engine.model.data;

public class UpgradeWeaponData {
    private String name;
    private String description;
    private String effect; // Type d'effet (ex. "damage_increase", "homing")
    private float value;   // Valeur numérique pour certains effets

    // Getters
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getEffect() {
        return effect;
    }

    public float getValue() {
        return value;
    }
}
