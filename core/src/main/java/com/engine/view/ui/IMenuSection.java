package com.engine.view.ui;

import com.badlogic.gdx.scenes.scene2d.ui.Table;


/**
 * Interface représentant une section d'un menu dans l'interface utilisateur du jeu.
 * <p>
 * Cette interface est utilisée pour créer et gérer des sections de menus dans l'interface utilisateur.
 * Chaque section de menu peut contenir une table de widgets et avoir une logique de mise à jour.
 * </p>
 */
public interface IMenuSection {

    /**
     * Méthode pour créer la table de la section de menu.
     * <p>
     * Cette méthode est utilisée pour initialiser les éléments graphiques (widgets) de la section
     * en les ajoutant à une table qui sera ensuite affichée à l'écran.
     * </p>
     */
    void createTable();

    /**
     * Méthode pour mettre à jour la section de menu.
     * <p>
     * Cette méthode permet de mettre à jour l'état de la section de menu, comme les entrées utilisateur
     * ou d'autres logiques liées à l'interactivité.
     * </p>
     */
    void update();

    /**
     * Méthode pour obtenir la table de la section de menu.
     * <p>
     * Cette méthode renvoie la table contenant les widgets de la section de menu, prête à être affichée.
     * </p>
     *
     * @return La table contenant les widgets de la section de menu.
     */
    Table getTable();

    /**
     * Méthode pour obtenir le nom de la section de menu.
     * <p>
     * Cette méthode permet d'identifier la section de menu en renvoyant un nom unique.
     * </p>
     *
     * @return Le nom de la section de menu.
     */
    String getName();
}
