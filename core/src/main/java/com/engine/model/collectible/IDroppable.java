package com.engine.model.collectible;

import com.engine.model.data.ItemData;
import com.engine.model.entity.player.Player;

/**
 * Interface représentant un objet ramassable dans le jeu.
 * <p>
 * Un objet qui implémente cette interface peut être ramassé par le joueur.
 * Il peut appliquer des effets au joueur et fournir des données sur l'objet (par exemple, son type, sa valeur, etc.).
 * </p>
 */
public interface IDroppable {

    /**
     * Applique les effets de l'objet ramassé au joueur.
     * <p>
     * Cette méthode est appelée lorsque le joueur ramasse l'objet. Les effets peuvent
     * inclure des changements de statistiques, l'ajout d'items, etc.
     * </p>
     *
     * @param player le joueur qui ramasse l'objet et auquel les effets seront appliqués.
     */
    void applyEffect(Player player);

    /**
     * Récupère les données associées à l'objet ramassé.
     * <p>
     * Les données peuvent inclure des informations comme le type de l'objet,
     * sa valeur, sa description, sa vitesse, etc.
     * </p>
     *
     * @return un objet {@link ItemData} contenant les informations de l'objet.
     */
    ItemData getItemData();
}
