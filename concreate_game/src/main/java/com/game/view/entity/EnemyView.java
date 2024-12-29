package com.game.view.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.engine.model.entity.enemy.model.HasProjectile;
import com.engine.model.resource.ResourceManager;
import com.engine.model.entity.enemy.model.Enemy;
import com.engine.model.projectile.model.AbstractProjectile;
import com.engine.view.entity.AbstractEntityView;


import java.util.List;

/**
 * La classe <code>EnemyView</code> est responsable de l'affichage visuel des ennemis dans le jeu.
 * Elle gère la mise à jour des textures pour l'animation des ennemis, le rendu de la barre de santé,
 * et la gestion des ennemis morts.
 * <p>
 * Cette classe utilise un <code>SpriteBatch</code> pour dessiner les textures des ennemis et un
 * <code>ShapeRenderer</code> pour dessiner la barre de santé. Elle met également à jour les animations
 * des ennemis en fonction du temps écoulé.
 * </p>
 */
public class EnemyView extends AbstractEntityView {

    private Texture enemyTexture;
    private float counter = 0f;
    private float animationTimer = 0;
    private final float ANIMATION_SPEED = 0.2f; // Changer de texture toutes les 0.2 secondes
    private final List<Enemy> enemies;

    /**
     * Constructeur de <code>EnemyView</code>.
     *
     * @param rm Le <code>ResourceManager</code> utilisé pour récupérer les textures des ennemis.
     */
    public EnemyView(List<Enemy> enemies, ResourceManager rm, SpriteBatch batch) {
        super(batch, rm);
        this.enemies = enemies;
    }

    /**
     * Renders all enemies in the given list, updating their animations, health bars, and handling death.
     * This method is called each frame to draw the enemies and manage their state.
     * <p>
     * The enemy's texture is updated based on their current state (idle, moving, or attacking), and the health
     * bar is drawn above them to show their current HP.
     * </p>
     *
     */
    @Override
    public void render() {
        updateAnimationTimer();

        // Start SpriteBatch once
        batch.begin();
        for( Enemy enemy : enemies){
            load_texture(enemy);

            renderEnemy(enemy);       // Draw the enemy
            renderProjectiles(enemy); // Draw the projectiles
            renderHealthBar(enemy);   // Draw the health bar

        }
        batch.end();
    }

    @Override
    public void dispose() {
        enemyTexture.dispose();
    }

    private void renderEnemy(Enemy enemy) {
        batch.draw(
            enemyTexture,
            enemy.getPosition().x, enemy.getPosition().y,
            enemy.getBbox().width, enemy.getBbox().height
        );

    }

    private void renderProjectiles(Enemy enemy) {

        if (enemy instanceof HasProjectile) { // Vérifiez si l'ennemi est un Magicien
            HasProjectile enemy1 = (HasProjectile) enemy;
            // Parcourir la liste des projectile
            for (AbstractProjectile projectile : enemy1.getProjectileList()) {
                batch.draw(
                    rm.getResource("TextureRegion",projectile.getData().getPath()),
                    projectile.getPosition().x,
                    projectile.getPosition().y,
                    projectile.getWidth() / 2, projectile.getHeight() / 2, // Point de pivot
                    projectile.getWidth(),
                    projectile.getHeight(),
                    1, 1,
                    projectile.getRotationAngle()
                );
            }
        }
    }

    private void load_texture(Enemy enemy) {
        if (enemy.isAttacking()) {
            enemyTexture = (counter % 2 == 0) ?
                rm.getResource("Texture",enemy.getData().getAttackPath()) :
                rm.getResource("Texture",enemy.getData().getIdlePath());
        } else {
            enemyTexture = (counter % 2 == 0) ?
                rm.getResource("Texture",enemy.getData().getMovePath()) :
                rm.getResource("Texture",enemy.getData().getIdlePath());
        }
    }

    private void updateAnimationTimer() {
        animationTimer += Gdx.graphics.getDeltaTime();
        if (animationTimer >= ANIMATION_SPEED) {
            counter++;
            animationTimer = 0;
        }
    }

    /**
     * Dessine la barre de santé d'un ennemi au-dessus de sa position.
     * La barre est dessinée en rouge et sa longueur est proportionnelle à la santé restante de l'ennemi.
     *
     * @param enemy L'ennemi dont la barre de santé doit être dessinée.
     */
    private void renderHealthBar(Enemy enemy) {
        float healthBarX = enemy.getPosition().x;
        float healthBarY = enemy.getPosition().y + enemy.getBbox().height + 5;
        float barWidth = enemy.getBbox().width;
        float barHeight = 5;

        // Calcul de la largeur actuelle de la barre de santé
        float currentHealthWidth = barWidth * enemy.getHealthComponent().getHp() / enemy.getHealthComponent().getMaxHp();

        // Dessiner la barre de santé en rouge
        batch.setColor(1, 0, 0, 1); // Rouge
        batch.draw((TextureRegion) rm.getResource("TextureRegion","concreate_game/src/assets/bg/White.png"), healthBarX, healthBarY, currentHealthWidth, barHeight);

        // Réinitialiser la couleur
        batch.setColor(1, 1, 1, 1); // Blanc (par défaut)
    }
}

