package com.engine.model.weapon.factory;

import com.engine.model.data.WeaponData;
import com.engine.model.projectile.ProjectileBehavior.IProjectileBehavior;
import com.engine.model.projectile.effect.IEffectBehavior;
import com.engine.model.weapon.fire.IFireBehavior;

public abstract class AbstractWeaponBuilder implements IWeaponFactory {
    protected IFireBehavior fireBehavior;
    protected IEffectBehavior effectBehavior;
    protected IProjectileBehavior projectileBehavior;
    protected WeaponData data ;

    public AbstractWeaponBuilder withFireBehavior(IFireBehavior fireBehavior ){
        this.fireBehavior = fireBehavior ;
        return this;
    }

    public AbstractWeaponBuilder withEffectBehavior(IEffectBehavior effectBehavior ){
        this.effectBehavior = effectBehavior ;
        return this;
    }

    public AbstractWeaponBuilder withProjectileBehavior(IProjectileBehavior projectileBehavior ){
        this.projectileBehavior = projectileBehavior ;
        return this;
    }

    public AbstractWeaponBuilder withWeaponData(WeaponData data ){
        this.data = data ;
        return this;
    }
}
