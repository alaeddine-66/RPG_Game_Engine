package com.engine.model.weapon.model;

public class AmmoManager {
    private int magazineCapacity;
    private int currentAmmo;

    public AmmoManager(int magazineCapacity) {
        this.magazineCapacity = magazineCapacity;
        this.currentAmmo = magazineCapacity;
    }

    public boolean canShoot() {
        return currentAmmo > 0;
    }

    public void shoot() {
        if (currentAmmo > 0) currentAmmo--;
    }

    public void reload() {
        currentAmmo = magazineCapacity;
    }

    public int getCurrentAmmo() {
        return currentAmmo;
    }
}

