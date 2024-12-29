package com.game.model.entity.player;

import com.engine.model.entity.player.ICombat;
import com.engine.model.weapon.model.AbstractWeapon;
import com.engine.model.resource.AudioManager;


public class PlayerCombat implements ICombat {
    private AbstractWeapon weapon;
    private boolean isReloading = false;
    private int reloadDelay = 0;
    private int attackDelay = 0;

    public PlayerCombat(AbstractWeapon weapon) {
        this.weapon = weapon;
    }

    @Override
    public void handleAttack(boolean attackPressed) {
        attackDelay++;

        if (isReloading && !attackPressed) {
            handleReload();
        } else if (!isReloading && attackPressed && attackDelay > weapon.getFire_rate()) {
            if (weapon.canShoot()) {
                AudioManager.getInstance().playSound("weaponAttack", 0.05f);
                weapon.attack(weapon.getweaponPos(), weapon.getRotationAngle());
                attackDelay = 0;
            } else {
                startReloading();
            }
        }
    }

    private void startReloading() {
        isReloading = true;
        reloadDelay = 0;
        AudioManager.getInstance().playSound("reload", 0.5f);
    }

    private void handleReload() {
        reloadDelay++;
        if (reloadDelay >= weapon.getReloadTime()) {
            weapon.reload();
            isReloading = false;
            reloadDelay = 0;
        }
    }

    @Override
    public AbstractWeapon getWeapon() {
        return weapon;
    }

    @Override
    public void setWeapon(AbstractWeapon weapon) {
        this.weapon = weapon;
    }
}

