package com.engine.view.Items ;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.engine.model.collectible.DroppedItem;

import java.util.List;

/**
 * Classe responsable de l'affichage des objets tombés dans le jeu.
 * <p>
 * Le {@code DroppedItemView} gère le rendu des objets tombés (comme des pièces ou de l'expérience) à l'écran.
 * Elle parcourt la liste des objets tombés, vérifie s'ils ont été collectés ou non, et les rend en conséquence.
 * </p>
 */
public class DroppedItemView {

    private final SpriteBatch batch; // SpriteBatch utilisé pour le rendu
    private final List<DroppedItem> droppedItems; // Liste des objets tombés à afficher

    /**
     * Constructeur de la classe {@code DroppedItemView}.
     * <p>
     * Ce constructeur initialise le {@code SpriteBatch} et la liste des objets tombés à afficher.
     * </p>
     *
     * @param batch Le SpriteBatch utilisé pour dessiner les textures.
     * @param droppedItems La liste des objets tombés à afficher.
     */
    public DroppedItemView(SpriteBatch batch, List<DroppedItem> droppedItems) {
        this.batch = batch;
        this.droppedItems = droppedItems;
    }

    /**
     * Méthode responsable du rendu des objets tombés.
     * <p>
     * Cette méthode parcourt la liste des objets tombés et les dessine à l'écran si l'objet n'a pas été collecté.
     * </p>
     * <p>
     * Chaque objet est dessiné à sa position, avec la texture correspondant à son type (par exemple, une pièce ou une expérience),
     * et les dimensions spécifiées par ses données d'objet.
     * </p>
     */
    public void render() {
        batch.begin(); // Démarre la phase de rendu
        for (DroppedItem item : droppedItems) {
            Texture texture = new Texture(item.getdrop().getItemData().getPath()); // Chargement de la texture
            if (!item.isCollected()) { // Vérifie si l'objet a été collecté
                // Rendu de l'objet tombé à sa position
                batch.draw(texture, item.getPosition().x, item.getPosition().y,
                    item.getdrop().getItemData().getWidth(), item.getdrop().getItemData().getHeight());
            }
        }
        batch.end(); // Fin du rendu
    }
}

