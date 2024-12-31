package com.engine.view.hud;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe qui gère l'affichage des composants HUD (Heads-Up Display) dans le jeu.
 * <p>
 * Le {@code HUDManager} est responsable de l'ajout et du rendu des différents composants HUD,
 * comme des barres de santé, des scores, ou d'autres informations visuelles affichées à l'écran.
 * Cette classe permet d'organiser et de gérer facilement l'affichage de plusieurs composants HUD.
 * </p>
 */
public class HUDManager {

    private final List<AbstractHUDComponents> components; // Liste des composants HUD à afficher

    /**
     * Constructeur de la classe {@code HUDManager}.
     * <p>
     * Ce constructeur initialise une liste vide de composants HUD, prête à recevoir des composants à afficher.
     * </p>
     */
    public HUDManager() {
        this.components = new ArrayList<>();
    }

    /**
     * Ajoute un composant HUD à la liste de ceux à afficher.
     * <p>
     * Cette méthode permet d'ajouter un nouveau composant à la gestion du HUD,
     * afin qu'il soit rendu à chaque mise à jour du jeu.
     * </p>
     *
     * @param component Le composant HUD à ajouter (ex. : barre de santé, score, etc.).
     */
    public void addComponent(AbstractHUDComponents component) {
        components.add(component);
    }

    /**
     * Rendu de tous les composants HUD ajoutés.
     * <p>
     * Cette méthode parcourt la liste des composants HUD et appelle la méthode {@link AbstractHUDComponents#render()}
     * pour chaque composant, afin de les afficher à l'écran.
     * </p>
     */
    public void render() {
        for (AbstractHUDComponents component : components) {
            component.render(); // Rendre chaque composant HUD
        }
    }
}
