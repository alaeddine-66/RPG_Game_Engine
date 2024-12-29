package com.game.view.Screens;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.engine.model.data.UpgradeWeaponData;
import com.engine.model.weapon.model.AbstractWeapon;
import com.engine.model.weapon.upgrade.UpgradeManager;

public class LevelUpScreen {
    private final Stage stage;
    private final Skin skin;
    private final UpgradeManager upgradeManager;
    private boolean isWindowOpen;


    public LevelUpScreen(Stage stage, Skin skin, UpgradeManager upgradeManager) {
        this.stage = stage;
        this.skin = skin;
        this.upgradeManager = upgradeManager;
    }

    public void showLevelUpWindow(AbstractWeapon weapon) {

        isWindowOpen = true;

        // Récupérer deux upgrades aléatoires
        UpgradeWeaponData upgrade1 = upgradeManager.getRandomUpgrade();
        UpgradeWeaponData upgrade2 = upgradeManager.getRandomUpgrade();
        while (upgrade2.getName() == (upgrade1.getName())){
            upgrade2 = upgradeManager.getRandomUpgrade();
        }

        // Table principale
        Table windowTable = new Table();
        windowTable.setFillParent(true);

        // Ajouter une fenêtre contextuelle
        Window levelUpWindow = new Window("Level Up!", skin);

        // Ajouter un titre
        Label titleLabel = new Label("Choose Your Upgrade", skin);
        levelUpWindow.add(titleLabel).colspan(2).pad(10).row();

        // Ajouter les boutons des upgrades
        TextButton upgrade1Button = new TextButton(upgrade1.getName() + "\n" + upgrade1.getDescription(), skin);
        TextButton upgrade2Button = new TextButton(upgrade2.getName() + "\n" + upgrade2.getDescription(), skin);

        levelUpWindow.add(upgrade1Button).width(350).height(300).pad(20);
        levelUpWindow.add(upgrade2Button).width(350).height(300).pad(20).row();

        // Ajouter un gestionnaire de clics pour chaque bouton
        upgrade1Button.addListener(new ClickListener() {
            @Override
            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
                applyUpgrade(upgrade1 , weapon);
                closeWindow(levelUpWindow);
            }
        });

        UpgradeWeaponData finalUpgrade = upgrade2;
        upgrade2Button.addListener(new ClickListener() {
            @Override
            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
                applyUpgrade(finalUpgrade, weapon);
                closeWindow(levelUpWindow);
            }
        });

        // Ajouter la fenêtre au stage
        windowTable.add(levelUpWindow).center();
        stage.addActor(windowTable);
    }

    private void applyUpgrade(UpgradeWeaponData upgrade , AbstractWeapon weapon) {
        upgradeManager.applyUpgrade(upgrade , weapon);
    }

    private void closeWindow(Window window) {
        window.remove(); // Supprimer la fenêtre
        isWindowOpen = false;

    }

    public boolean levelUpWindowOpened(){
        return isWindowOpen;
    }
}
