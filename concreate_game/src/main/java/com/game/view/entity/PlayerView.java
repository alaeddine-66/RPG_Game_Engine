package com.game.view.entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.engine.model.entity.player.Player;
import com.badlogic.gdx.graphics.Texture;

import com.engine.model.data.PlayerData.Images;
import com.engine.model.resource.ResourceManager;
import com.engine.view.entity.AbstractEntityView;

/**
 * La classe <code>PlayerView</code> est responsable de l'affichage visuel du joueur dans le jeu.
 * Elle gère l'affichage de la texture du joueur, y compris le changement d'orientation en fonction de sa direction,
 * et utilise un <code>SpriteBatch</code> pour dessiner le joueur sur l'écran.
 * <p>
 * Cette classe met également à jour la texture du joueur en fonction de son état (par exemple, s'il attaque ou s'il se déplace).
 * </p>
 */
public class PlayerView extends AbstractEntityView {
    private Player player;
    private Images playerTexture;
    private boolean facingRight = false;
    private Texture currentTexture;

    public PlayerView(Player player, Images playerTexture, ResourceManager rm, SpriteBatch batch) {
        super(batch, rm);
        this.player = player;
        this.playerTexture = playerTexture;
        this.currentTexture = new Texture(playerTexture.getidleD());
    }

    @Override
    public void render() {
        float x = player.getPosition().x;
        float y = player.getPosition().y;

        if (player.isFacingRight() && !facingRight) {
            currentTexture = rm.getResource("Texture",playerTexture.getidleD());
            facingRight = true;
        } else if (!player.isFacingRight() && facingRight) {
            currentTexture = rm.getResource("Texture",playerTexture.getidleG());
            facingRight = false;
        }

        batch.begin();
        batch.draw(currentTexture, x, y);
        batch.end();
    }

    @Override
    public void dispose() {
        currentTexture.dispose();
    }
}
