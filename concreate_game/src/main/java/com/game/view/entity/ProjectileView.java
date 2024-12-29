package com.game.view.entity;

import com.engine.model.resource.ResourceManager;
import com.engine.model.projectile.model.AbstractProjectile;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.engine.view.entity.AbstractEntityView;

import java.util.List;

/**
 * La classe <code>WeaponView</code> est responsable de l'affichage visuel de l'arme et de ses projectiles
 * associés dans le jeu. Elle utilise le <code>SpriteBatch</code> pour dessiner l'arme du joueur, en prenant
 * en compte son orientation et sa position par rapport au joueur, ainsi que pour dessiner les projectiles tirés.
 * <p>
 * Cette classe met également à jour la position de l'arme en fonction de l'angle de rotation du joueur,
 * et elle gère l'affichage des projectiles en fonction de leurs positions et angles de rotation.
 * </p>
 */
public class ProjectileView extends AbstractEntityView {

    private final List<AbstractProjectile> projectiles;

    /**
     * Constructeur de la classe <code>ProjectileView</code>.
     *
     */
    public ProjectileView(ResourceManager rm , SpriteBatch batch , List<AbstractProjectile> projectiles) {
        super(batch ,rm);
        this.projectiles = projectiles;
    }

    /**
     * Projectiles fired from the weapon are also rendered at their respective positions.
     */
    public void render() {
        // Dessiner les projectiles
        batch.begin();
        for (AbstractProjectile projectile : projectiles) {
            batch.draw(
                rm.getResource("TextureRegion",projectile.getData().getPath()),
                projectile.getPosition().x,
                projectile.getPosition().y,
                projectile.getWidth() / 2, projectile.getHeight() / 2, // Point de pivot (centre)
                projectile.getWidth(),
                projectile.getHeight(),
                1, 1,
                projectile.getRotationAngle()
            );
        }
        batch.end();

    }

    @Override
    public void dispose() {
        //
    }
}





