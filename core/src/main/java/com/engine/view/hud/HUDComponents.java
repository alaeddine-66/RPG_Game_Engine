package com.engine.view.hud;

public interface HUDComponents {

    /**
     * Méthode qui doit être implémentée pour définir le rendu spécifique du HUD.
     * <p>
     * Chaque composant du HUD (par exemple, barre de santé, score) doit fournir sa propre
     * implémentation de cette méthode pour afficher les informations pertinentes à l'écran.
     * </p>
     */
    void render();
}
