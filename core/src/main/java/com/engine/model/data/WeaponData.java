package com.engine.model.data;

import java.util.HashMap;


public class WeaponData {
    private String name;
    private String type;
    private String path;
    private String projectile;
    private String projectileBehavior;
    private float damage;
    private float fireRate;
    private int magazineCapacity;
    private int reloadTime;
    private String fireBehavior;
    private String effectBehavior;
    private HashMap<String, Integer> extra; // Use HashMap for LibGDX compatibility

    // No-arg constructor for deserialization
    public WeaponData() {
        this.extra = new HashMap<>(); // Initialize the map to avoid null pointers during deserialization
    }

    // Getters
    public String getName() { return name; }

    public String getType() { return type; }

    public String getPath() { return path; }

    public float getDamage() { return damage; }

    public float getFireRate() { return fireRate; }

    public int getMagazineCapacity(){return magazineCapacity;}

    public int getReloadTime(){return reloadTime;}

    public String getFireBehavior() { return fireBehavior; }

    public String getEffectBehavior() { return effectBehavior; }

    public HashMap<String, Integer> getExtra() { return extra; }

    public String getProjectile() {
        return projectile;
    }

    public String getProjectileBehavior() {
        return projectileBehavior;
    }

}
