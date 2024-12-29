package com.game.view.entity;

import com.engine.model.entity.player.Player;
import com.engine.model.resource.ResourceManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.engine.view.entity.AbstractEntityView;

/**
 * La classe <code>WeaponView</code> est responsable de l'affichage visuel de l'arme et de ses projectiles
 * associés dans le jeu. Elle utilise le <code>SpriteBatch</code> pour dessiner l'arme du joueur, en prenant
 * en compte son orientation et sa position par rapport au joueur, ainsi que pour dessiner les projectiles tirés.
 * <p>
 * Cette classe met également à jour la position de l'arme en fonction de l'angle de rotation du joueur,
 * et elle gère l'affichage des projectiles en fonction de leurs positions et angles de rotation.
 * </p>
 */
public class WeaponView extends AbstractEntityView {


    private final Player player;

    /**
     * Constructeur de la classe <code>WeaponView</code>.
     *
     * @param rm Le gestionnaire des ressources utilisé pour charger les textures des armes et projectiles.
     */
    public WeaponView(ResourceManager rm , SpriteBatch batch , Player player) {
        super(batch , rm);
        this.player = player ;
    }

    /**
     * Renders the player's weapon and its projectiles based on the player's current state.
     * The weapon's position is calculated based on the player's position and rotation angle, and the weapon
     * texture is drawn. Projectiles fired from the weapon are also rendered at their respective positions.
     * <p>
     * The method calculates the weapon's position based on the player's position and rotation angle, and then
     * it renders the weapon. It also draws each projectile in the player's weapon's projectile list.
     * </p>
     *
     */
    @Override
    public void render() {

        // Position du joueur (centre)
        float playerX = player.getPosition().x + player.getBbox().width / 2;
        float playerY = player.getPosition().y + player.getBbox().height / 2;

        // Rayon de rotation (distance entre le joueur et l'arme)
        float rotationRadius = player.getBbox().width; // Ajuste cette valeur pour changer la distance

        // Angle de rotation de l'arme (en radians)
        float angleInRadians = (float) Math.toRadians(player.getRotationAngle());

        // Calculer la position de l'arme
        float weaponX = playerX + rotationRadius * (float) Math.cos(angleInRadians) - player.getBbox().width / 2;
        float weaponY = playerY + rotationRadius * (float) Math.sin(angleInRadians) - player.getBbox().height / 2;

        player.getWeapon().setweaponPos(new Vector2(weaponX, weaponY));

        batch.begin();
        // Dessiner l'arme
        batch.draw(
            rm.getResource("TextureRegion",player.getWeapon().getPath()),
            weaponX,
            weaponY,
            player.getBbox().width / 2, player.getBbox().height / 2, // Point de pivot (centre)
            player.getBbox().width, player.getBbox().height,
            1, 1,
            player.getRotationAngle()
        );
        batch.end();

    }

    @Override
    public void dispose() {
        //
    }
}





