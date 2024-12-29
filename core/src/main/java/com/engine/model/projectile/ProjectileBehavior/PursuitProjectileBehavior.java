package com.engine.model.projectile.ProjectileBehavior;

import com.engine.model.entity.IAttackable;
import com.engine.model.entity.IAttacker;
import com.engine.model.projectile.model.AbstractProjectile;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

import java.util.List;

public class PursuitProjectileBehavior implements IProjectileBehavior {

    /**
     * Met à jour l'état du projectile pour suivre l'entité attaquable la plus proche.
     *
     * @param projectile Le projectile à mettre à jour.
     * @param damage     Les dégâts infligés par le projectile lors de l'impact.
     * @param targets    La liste des cibles attaquables.
     * @param attacker   Le joueur, dont les attributs peuvent modifier les dégâts infligés.
     */
    @Override
    public void updateProjectile(AbstractProjectile projectile, float damage, List<IAttackable> targets, IAttacker attacker) {
        // Vérifier si des cibles sont disponibles ou si le projectile est inactif
        if (targets.isEmpty() || !projectile.isActive()) {
            return;
        }

        // Trouver la cible attaquable la plus proche
        IAttackable closestTarget = null;
        float minDistance = Float.MAX_VALUE;

        for (IAttackable target : targets) {
            float distance = projectile.getPosition().dst(new Vector2(target.getBbox().x , target.getBbox().y ));
            if (distance < minDistance) {
                minDistance = distance;
                closestTarget = target;
            }

        }

        // Ajuster la direction du projectile vers la cible la plus proche
        if (closestTarget != null) {
            Vector2 targetPos = new Vector2(closestTarget.getBbox().x , closestTarget.getBbox().y );
            Vector2 toTarget = targetPos.sub(projectile.getPosition());
            float distanceToTarget = toTarget.len();

            if (distanceToTarget > 1.0f) { // Distance seuil pour éviter des oscillations
                toTarget.nor(); // Normaliser la direction

                float turnRate = 5.0f; // Taux de virage pour ajuster progressivement la direction

                // Calculer une nouvelle direction avec interpolation linéaire
                Vector2 currentVelocity = projectile.getDirection().nor();
                Vector2 desiredVelocity = currentVelocity.lerp(toTarget, turnRate * Gdx.graphics.getDeltaTime()).nor();

                projectile.setDirection(desiredVelocity);
            }
        }

        // Mettre à jour la position du projectile
        projectile.update();

        // Vérifier les collisions avec les cibles
        for (IAttackable target : targets) {
            if (projectile.getRect().overlaps(target.getBbox())) {
                // Appliquer les dégâts à la cible
                int totalDamage = (int) (damage + attacker.getStrengthComponent().getStrength());
                target.getHealthComponent().damageHp(totalDamage);

                // Désactiver le projectile après impact
                projectile.deactivate();
                break; // Arrêter après le premier impact
            }
        }
    }
}
