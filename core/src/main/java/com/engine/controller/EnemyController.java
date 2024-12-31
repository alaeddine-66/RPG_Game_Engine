package com.engine.controller;

import com.engine.model.collectible.DroppedItemManager;
import com.engine.model.entity.IAttackable;
import com.engine.model.entity.components.HealthComponent;
import com.engine.model.entity.enemy.model.Enemy;
import com.engine.model.entity.enemy.model.Hasminions;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class EnemyController {
    private final DroppedItemManager dropsManager;

    public EnemyController(DroppedItemManager dropsManager){
        this.dropsManager = dropsManager;
    }

    public void updateEnemies(List<Enemy> enemies, List<IAttackable> targets, float dt) {
        List<Enemy> newEnemies = new ArrayList<>(); // Temporary list to store new enemies

        Iterator<Enemy> it = enemies.iterator();
        while(it.hasNext()){
            Enemy enemy = it.next();
            enemy.update(targets, dt);
            addEnnemies(enemy, newEnemies);

            // Vérifie si l'ennemi est mort et le retire de la liste si c'est le cas
            if (enemy.getComponent(HealthComponent.class).isDead()) {
                dropsManager.dropItemsFromEnemy(enemy.getPosition(),enemy.getData().getDrops());
                it.remove(); // Retirer l'ennemi de la liste
                //enemy.onEnemyDeath(player); // Gérer la mort de l'ennemi (par exemple, en informant le joueur)
            }
        }

        enemies.addAll(newEnemies); // Add new enemies after iteration
    }

    /**
     * Ajoute les minions de l'ennemi à la liste des nouveaux ennemis si l'ennemi a des minions.
     * @param enemy L'ennemi à vérifier.
     * @param newEnemies La liste dans laquelle ajouter les minions.
     */
    private static void addEnnemies(Enemy enemy, List<Enemy> newEnemies) {
        if (enemy instanceof Hasminions) {
            newEnemies.addAll(((Hasminions) enemy).getMinions());
            ((Hasminions) enemy).clearMinions();
        }
    }

}
