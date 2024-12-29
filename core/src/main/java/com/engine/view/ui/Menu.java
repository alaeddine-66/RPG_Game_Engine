package com.engine.view.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.utils.Align;

import java.util.List;

/**
 * La classe <code>Menu</code> gère l'affichage de l'interface principale du jeu,
 * qui inclut un système de navigation entre différentes sections (Store, Stats, Inventory).
 * Elle contient des méthodes pour gérer l'affichage du HUD, de la barre de boutons de navigation,
 * ainsi que de l'arrière-plan noir qui apparaît lorsque le HUD est visible.
 */
public class Menu {
    private Stage stage;
    private Skin skin;

    private Table rootTable;    // Table principale
    private Table buttonBar;    // Barre de boutons pour changer de section
    private Stack contentStack; // Conteneur pour les sections dynamiques
    private Table blackBackgroundTable;

    private boolean hudVisible = false;
    private List<IMenuSection> sections;


    /**
     * Constructeur de la classe <code>Menu</code>.
     *
     * @param stage Le stage sur lequel l'interface sera affichée.
     * @param skin La peau (style) utilisée pour les éléments de l'interface.
     */
    public Menu(Stage stage, Skin skin , List<IMenuSection> userSections) {
        this.stage = stage;
        this.skin = skin;
        this.sections = userSections;

        createHUD();

        // Masquer le HUD et l'arrière-plan noir au démarrage
        rootTable.setVisible(hudVisible);
        blackBackgroundTable.setVisible(hudVisible);
    }

    /**
     * Méthode de mise à jour de l'interface. Elle met à jour les sections du menu (Store, Stats, Inventory).
     */
    public void update() {
        for(IMenuSection section : sections){
            section.update();
        }
    }

    /**
     * Crée l'interface utilisateur du HUD, incluant la barre de boutons et le conteneur pour les sections dynamiques.
     */
    private void createHUD() {
        createBlackBackground(skin);

        rootTable = new Table();
        rootTable.setFillParent(true);

        // Barre de boutons en haut
        createButtonBar();

        // Contenu dynamique (Store, Stats, Inventory)
        contentStack = new Stack();
        createContentSections();

        // Ajouter la barre de boutons et le contenu au tableau principal
        rootTable.add(buttonBar).align(Align.top).fillX().height(50); // Barre de boutons
        rootTable.row();
        rootTable.add(contentStack).expand().fill(); // Sections dynamiques

        stage.addActor(rootTable);
        rootTable.setVisible(hudVisible);
    }

    /**
     * Crée la barre de boutons permettant de changer entre les sections du menu.
     * Chaque bouton permet d'afficher une section différente (Store, Stats, Inventory).
     */

    private void createButtonBar() {
        buttonBar = new Table();
        for (IMenuSection section : sections) {
            TextButton button = new TextButton(section.getName(), skin);
            button.addListener(event -> {
                if (!event.isHandled()) return false;
                showSection(section.getTable());
                return true;
            });
            buttonBar.add(button).fill().expandX();
        }
    }

    /**
     * Crée les sections du menu : Store, Stats et Inventory.
     * Chaque section est ajoutée au conteneur <code>contentStack</code>.
     */
    private void createContentSections(){
        for(IMenuSection section : sections ){
            contentStack.add(section.getTable());
        }
        showSection(sections.get(0).getTable());
    }

    /**
     * Affiche une section donnée et masque les autres sections.
     *
     * @param tableToShow La table (section) à afficher.
     */
    private void showSection(Table tableToShow) {
        for (Actor section : contentStack.getChildren()) {
            section.setVisible(section == tableToShow);
        }
    }

    /**
     * Crée un arrière-plan noir couvrant toute l'interface.
     *
     * @param skin La peau utilisée pour créer l'arrière-plan.
     */
    private void createBlackBackground(Skin skin) {
        blackBackgroundTable = new Table(); // Utilisez la variable d'instance ici
        blackBackgroundTable.setFillParent(true);
        blackBackgroundTable.setBackground(createBlackDrawable(skin));
        stage.addActor(blackBackgroundTable); // Ajouter l'arrière-plan avant tout
    }

    /**
     * Crée un fond noir pour l'arrière-plan.
     *
     * @param skin La peau utilisée pour créer le fond.
     * @return Un <code>Drawable</code> représentant un fond noir.
     */
    private Drawable createBlackDrawable(Skin skin) {
        NinePatch patch = new NinePatch(skin.getRegion("white"), 0, 0, 0, 0);
        patch.setColor(Color.BLACK);
        return new NinePatchDrawable(patch);
    }

    /**
     * Permet d'afficher ou de masquer le HUD.
     */
    public void toggleHUD() {
        hudVisible = !hudVisible;
        rootTable.setVisible(hudVisible);
        blackBackgroundTable.setVisible(hudVisible); // Synchroniser avec le HUD
    }

    /**
     * Indique si le HUD est actuellement visible.
     *
     * @return <code>true</code> si le HUD est visible, <code>false</code> sinon.
     */
    public boolean isHudVisible() {
        return hudVisible;
    }

}
