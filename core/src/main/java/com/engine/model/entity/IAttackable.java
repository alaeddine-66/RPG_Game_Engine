package com.engine.model.entity;

import com.badlogic.gdx.math.Rectangle;
import com.engine.model.entity.components.HealthComponent;

public interface IAttackable {
    HealthComponent getHealthComponent();
    Rectangle getBbox();
}
