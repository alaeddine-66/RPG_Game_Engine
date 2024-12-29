package com.game.view.Screens;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.utils.Align;

import com.engine.model.waves.WaveManager;
import com.engine.view.ui.Menu;

public class WaveEndScreen {
    private final Stage stage;
    private final Skin skin;

    private Table rootTable;        // Table principale
    private Table buttonBar;        // Barre de boutons pour les actions
    private Stack contentStack;     // Conteneur pour les messages dynamiques
    private Table blackBackgroundTable;

    private final WaveManager waveManager;
    private final Menu menu ;

    private boolean isVisible = false;

    public WaveEndScreen(Stage stage, Skin skin , WaveManager waveManager , Menu menu) {
        this.stage = stage;
        this.skin = skin;
        this.waveManager = waveManager;
        this.menu = menu ;

    }

    private void createUI() {
        createBlackBackground(skin);

        rootTable = new Table();
        rootTable.setFillParent(true);

        // Barre de boutons en bas
        createButtonBar();

        // Contenu dynamique (Continue, Rewards, etc.)
        contentStack = new Stack();
        createContentSections();

        // Ajouter la barre de boutons et le contenu au tableau principal
        rootTable.add(contentStack).expand().fill().row();
        rootTable.add(buttonBar).align(Align.bottom).fillX(); // Barre de boutons

        buttonBar.setVisible(waveManager.hasNextWave());

        stage.addActor(rootTable);
    }

    public void update(){
        createUI();
    }

    private void createButtonBar() {
        buttonBar = new Table();

        // Créer des boutons
        TextButton continueButton = new TextButton("Continue", skin);
        TextButton hudButton = new TextButton("HUD Barre", skin);

        // Appliquer des tailles minimales identiques
        float buttonHeight = 40f; // Hauteur fixe des boutons
        continueButton.getLabel().setFontScale(1f); // Uniformiser la taille du texte
        hudButton.getLabel().setFontScale(1f);

        // Ajouter les boutons à la barre avec une taille définie
        buttonBar.add(continueButton).fill().height(buttonHeight).padBottom(100).space(10);
        buttonBar.add(hudButton).fill().height(buttonHeight).padBottom(100).space(10);

        // Configurer les listeners des boutons
        setupButtonListeners(continueButton, hudButton);
    }

    private void setupButtonListeners(TextButton continueButton, TextButton hudButton) {
        continueButton.addListener(new ClickListener() {
            @Override
            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
                toggleVisibility();
                waveManager.proceedToNextWave();
            }
        });

        hudButton.addListener(new ClickListener() {
            @Override
            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
                toggleVisibility();
                menu.toggleHUD();
                waveManager.proceedToNextWave();
            }
        });
    }

    private void createContentSections() {

        Table continueTable = new Table();

        Label endWaveLabel = new Label(getWaveEndMessage(), skin);
        endWaveLabel.setFontScale(waveManager.hasNextWave() ? 1.8f : 2f);
        continueTable.add(endWaveLabel).center().expand().row();

        if (waveManager.hasNextWave()) {
            Label continueLabel = new Label("Ready for the next wave?", skin);
            continueLabel.setFontScale(1.2f);
            continueTable.add(continueLabel).center().expand().padBottom(50);
        }

        contentStack.add(continueTable); // Ajouter à la pile
    }

    private String getWaveEndMessage() {
        return waveManager.hasNextWave()
            ? "Wave " + waveManager.getCurrentWave().getWaveNumber() + " Completed"
            : "Game Completed";
    }


    private void createBlackBackground(Skin skin) {
        blackBackgroundTable = new Table();
        blackBackgroundTable.setFillParent(true);
        blackBackgroundTable.setBackground(createBlackDrawable(skin));
        stage.addActor(blackBackgroundTable);
    }

    private Drawable createBlackDrawable(Skin skin) {
        NinePatch patch = new NinePatch(skin.getRegion("white"), 0, 0, 0, 0);
        patch.setColor(Color.BLACK);
        return new NinePatchDrawable(patch);
    }

    public void toggleVisibility() {
        isVisible = !isVisible;
        rootTable.setVisible(isVisible);
        blackBackgroundTable.setVisible(isVisible);
    }

    public boolean isVisible() {
        return isVisible;
    }

}
