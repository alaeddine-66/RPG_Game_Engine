package com.engine.view.entity;


/**
 * Interface représentant une vue d'entité dans le moteur de jeu.
 * <p>
 * Cette interface définit les méthodes nécessaires pour la gestion visuelle des entités dans le jeu.
 * Elle est utilisée pour afficher et gérer la ressource graphique associée à une entité.
 * </p>
 */
public interface IEntityView {

    /**
     * Rendre l'entité à l'écran.
     * <p>
     * Cette méthode est utilisée pour dessiner l'entité à l'écran.
     * Elle doit être appelée à chaque cycle de rendu du jeu pour afficher l'entité avec ses caractéristiques visuelles.
     * </p>
     */
    void render();

    /**
     * Libérer les ressources utilisées par la vue de l'entité.
     * <p>
     * Cette méthode doit être appelée lorsque l'entité est supprimée ou lorsque la vue de l'entité n'est plus nécessaire.
     * Elle permet de libérer les ressources graphiques allouées (textures, buffers, etc.).
     * </p>
     */
    void dispose();
}
