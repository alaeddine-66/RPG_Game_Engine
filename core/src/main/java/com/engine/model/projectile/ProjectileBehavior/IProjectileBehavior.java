package com.engine.model.projectile.ProjectileBehavior;

import com.engine.model.entity.IAttackable;
import com.engine.model.entity.IAttacker;
import com.engine.model.projectile.model.AbstractProjectile;

import java.util.List;

/**
 * Interface représentant le comportement d'un projectile.
 * Cette interface définit une méthode pour gérer la mise à jour de l'état d'un projectile,
 * en tenant compte des dégâts, du temps écoulé et des interactions avec les ennemis et le joueur.
 */
public interface IProjectileBehavior {

    /**
     * Met à jour l'état d'un projectile selon son comportement spécifique.
     *
     * @param projectile Le projectile à mettre à jour.
     * @param damage     Les dégâts infligés par le projectile.
     * @param targets    La liste des ennemis présents dans le jeu, utilisée pour vérifier les collisions ou interactions.
     * @param attacker     Le joueur, permettant d'effectuer des interactions si nécessaire.
     */
    void updateProjectile(AbstractProjectile projectile, float damage, List<IAttackable> targets, IAttacker attacker);
}
