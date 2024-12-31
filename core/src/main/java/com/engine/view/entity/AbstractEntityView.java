package com.engine.view.entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.engine.model.resource.ResourceManager;

/**
 * Classe abstraite représentant la vue d'une entité dans le moteur de jeu.
 * <p>
 * Cette classe fournit une base commune pour les vues d'entités spécifiques dans le jeu,
 * en gérant des ressources communes telles que le {@link SpriteBatch} pour le rendu
 * et le {@link ResourceManager} pour la gestion des ressources.
 * </p>
 */
public abstract class AbstractEntityView implements IEntityView {

    protected SpriteBatch batch; // Le SpriteBatch utilisé pour le rendu des sprites
    protected ResourceManager rm; // Le gestionnaire de ressources pour charger et gérer les ressources

    /**
     * Constructeur de la vue d'entité.
     *
     * @param batch Le {@link SpriteBatch} utilisé pour le rendu des graphiques.
     * @param rm Le {@link ResourceManager} pour la gestion des ressources de l'entité.
     */
    public AbstractEntityView(SpriteBatch batch, ResourceManager rm) {
        this.batch = batch;
        this.rm = rm;
    }

}
