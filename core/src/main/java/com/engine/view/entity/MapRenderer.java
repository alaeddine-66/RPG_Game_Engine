package com.engine.view.entity;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

/**
 * Classe responsable du rendu d'une carte de type orthogonale dans le moteur de jeu.
 * <p>
 * Cette classe utilise un {@link OrthogonalTiledMapRenderer} pour afficher une carte basée sur des tiles (tuiles)
 * avec un rendu orthographique à l'écran. Elle gère également la caméra pour s'assurer que la carte
 * est correctement affichée selon la vue définie par la caméra.
 * </p>
 */
public class MapRenderer {

    private final OrthogonalTiledMapRenderer mapRenderer; // Le moteur de rendu de la carte
    private final OrthographicCamera cam; // La caméra utilisée pour afficher la carte

    /**
     * Constructeur de la classe MapRenderer.
     * <p>
     * Ce constructeur initialise le moteur de rendu de la carte avec la carte donnée
     * et la caméra orthographique pour le rendu.
     * </p>
     *
     * @param Map La carte à afficher (de type {@link TiledMap}).
     * @param cam La caméra orthographique utilisée pour l'affichage de la carte.
     */
    public MapRenderer(TiledMap Map, OrthographicCamera cam) {
        this.cam = cam;
        this.mapRenderer = new OrthogonalTiledMapRenderer(Map, 1); // Échelle des tiles
    }

    /**
     * Rendre la carte à l'écran.
     * <p>
     * Cette méthode configure la vue de la carte selon la caméra et effectue le rendu de la carte.
     * Elle doit être appelée dans la boucle de rendu pour afficher la carte correctement.
     * </p>
     */
    public void render() {
        mapRenderer.setView(cam); // Met à jour la vue avec la caméra
        mapRenderer.render(); // Effectue le rendu de la carte
    }

    public void dispose() {
        mapRenderer.dispose();
    }
}

