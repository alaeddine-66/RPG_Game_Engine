package com.engine.view.hud;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.OrthographicCamera;

/**
 * Classe abstraite représentant les composants de l'interface utilisateur de type HUD (Heads-Up Display).
 * <p>
 * Cette classe sert de base pour les différents composants d'interface utilisateur (UI) qui affichent des informations
 * à l'écran, telles que des statistiques de jeu, des scores, des barres de santé, etc. Les classes dérivées doivent
 * implémenter la méthode {@link #render()} pour définir comment les informations sont affichées.
 * </p>
 */
public abstract class AbstractHUDComponents {

    protected OrthographicCamera camera; // La caméra utilisée pour l'affichage du HUD
    protected SpriteBatch batch; // Le SpriteBatch utilisé pour le rendu graphique
    protected HUDDataProvider dataProvider; // Le fournisseur de données pour les informations du HUD
    protected BitmapFont font; // La police utilisée pour l'affichage du texte

    /**
     * Constructeur de la classe AbstractHUDComponents.
     * <p>
     * Ce constructeur initialise la caméra, le SpriteBatch, le fournisseur de données et la police utilisée pour
     * afficher les informations du HUD.
     * </p>
     *
     * @param batch Le SpriteBatch utilisé pour dessiner les éléments graphiques.
     * @param camera La caméra orthographique utilisée pour afficher le HUD.
     * @param dataProvider Le fournisseur de données pour obtenir les informations du HUD.
     */
    public AbstractHUDComponents(SpriteBatch batch, OrthographicCamera camera, HUDDataProvider dataProvider) {
        this.camera = camera;
        this.batch = batch;
        this.dataProvider = dataProvider;
        this.font = new BitmapFont(); // Initialise une nouvelle police par défaut
    }

    /**
     * Méthode abstraite qui doit être implémentée pour définir le rendu spécifique du HUD.
     * <p>
     * Chaque composant du HUD (par exemple, barre de santé, score) doit fournir sa propre
     * implémentation de cette méthode pour afficher les informations pertinentes à l'écran.
     * </p>
     */
    public abstract void render();
}

