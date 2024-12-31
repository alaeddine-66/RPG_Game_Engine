package com.engine.model.entity.components;

import com.badlogic.gdx.math.MathUtils;
import com.engine.model.ObserverPattern.Event;
import com.engine.model.ObserverPattern.Observer;

public class HealthComponent implements IComponent, ILevelUpComponent, Observer {
    private int hp;
    private int maxHp;
    private int minHpIncrease;
    private int maxHpIncrease;

    /**
     * Constructeur avec uniquement les points de vie maximum.
     *
     * @param maxHp les points de vie maximum.
     */
    public HealthComponent(int maxHp) {
        if (maxHp <= 0) {
            throw new IllegalArgumentException("maxHp must be positive.");
        }
        this.maxHp = maxHp;
        this.hp = maxHp;
    }

    /**
     * Constructeur avec paramètres d'augmentation des points de vie.
     *
     * @param maxHp         les points de vie maximum.
     * @param minHpIncrease augmentation minimale des points de vie.
     * @param maxHpIncrease augmentation maximale des points de vie.
     */
    public HealthComponent(int maxHp, int minHpIncrease, int maxHpIncrease) {
        if (maxHp <= 0 || minHpIncrease < 0 || maxHpIncrease < minHpIncrease) {
            throw new IllegalArgumentException("Invalid health or increase values.");
        }
        this.maxHp = maxHp;
        this.hp = maxHp;
        this.minHpIncrease = minHpIncrease;
        this.maxHpIncrease = maxHpIncrease;
    }

    public void damageHp(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Damage amount must be positive.");
        }
        setHp(hp - amount);
    }

    public void heal(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Heal amount must be positive.");
        }
        setHp(hp + amount);
    }

    public void increaseMaxHp(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Increase amount must be positive.");
        }
        maxHp += amount;
        // On pourrait ici décider si la santé actuelle reste inchangée ou augmente.
    }

    @Override
    public void onLevelUp(int remainder) {
        int healingAmount = MathUtils.random(minHpIncrease, maxHpIncrease);
        increaseMaxHp(healingAmount);
        setHp(getMaxHp()); // Remet à jour la santé à son maximum après guérison
    }

    @Override
    public void update(Event event) {
        // Déléguer la gestion directement à l'événement
        event.handle(this);
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
        clampHp();
    }

    public int getMaxHp() {
        return maxHp;
    }

    public void setMaxHp(int maxHp) {
        if (maxHp <= 0) {
            throw new IllegalArgumentException("maxHp must be positive.");
        }
        this.maxHp = maxHp;
        clampHp();
    }

    public boolean isDead() {
        return hp <= 0;
    }

    private void clampHp() {
        if (hp > maxHp) {
            hp = maxHp;
        } else if (hp < 0) {
            hp = 0;
        }
    }

}
