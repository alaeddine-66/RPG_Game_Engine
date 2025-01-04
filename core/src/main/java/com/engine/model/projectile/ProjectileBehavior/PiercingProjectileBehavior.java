package com.engine.model.projectile.ProjectileBehavior;

import com.engine.model.entity.IAttackable;
import com.engine.model.entity.IAttacker;
import com.engine.model.projectile.model.AbstractProjectile;


import java.util.List;

public class PiercingProjectileBehavior implements IProjectileBehavior {

    @Override
    public void updateProjectile(AbstractProjectile projectile, float damage, List<IAttackable> targets, IAttacker attacker) {
        // Mise à jour de la position du projectile
        projectile.update();

        // Si le projectile est inactif, ne rien faire
        if (!projectile.isActive()) {
            return;
        }

        // Parcours des cibles attaquables
        for (IAttackable target : targets) {
            // Vérifie si la cible est touchée par le projectile et si elle n'a pas déjà été touchée
            if (target.getBbox().intersects(projectile.getBbox()) && !projectile.hasHitAttackable(target)) {
                // Inflige des dégâts à la cible
                target.getHealthComponent().damageHp((int) (damage + attacker.getStrengthComponent().getStrength()));
                // Enregistre la cible comme étant touchée
                projectile.registerHit(target);
            }
        }
    }
}


