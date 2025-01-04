package com.engine.model.weapon;

import com.engine.model.data.WeaponData;

public interface BehaviorFactory<T> {
    T create(WeaponData data);
}
