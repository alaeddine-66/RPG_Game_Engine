package com.engine.model.projectile.ProjectileBehavior;

import com.engine.model.entity.IAttackable;
import com.engine.model.entity.IAttacker;
import com.engine.model.projectile.model.AbstractProjectile;

import java.util.List;


public class DefaultProjectileBehavior implements IProjectileBehavior {

    @Override
    public void updateProjectile(AbstractProjectile projectile, float damage, List<IAttackable> targets, IAttacker attacker) {

        // Mettre à jour le projectile si actif
        if (projectile.isActive()) {
            projectile.update();
        } else {
            return;
        }

        // Parcourir les cibles pour vérifier une collision
        for (IAttackable target : targets) {
            if (projectile.getBbox().intersects(target.getBbox())) {
                // Appliquer les dégâts en ajoutant la force du joueur
                int totalDamage = (int) (damage + attacker.getStrengthComponent().getStrength());
                target.getHealthComponent().damageHp(totalDamage);

                // Désactiver le projectile après avoir touché la cible
                projectile.deactivate();
                break; // Arrêter après avoir touché une cible
            }
        }
    }
}

