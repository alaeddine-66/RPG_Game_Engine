package com.game.view.ui;

import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.utils.Align;
import com.engine.model.entity.player.Player;
import com.engine.model.entity.components.ExperienceComponent;
import com.game.model.entity.components.StatsComponent;
import com.engine.view.ui.IMenuSection;

public class StatsUI implements IMenuSection {
    private final Table table;
    private final Player player;
    private final Skin skin;
    private String name ;

    public StatsUI(Player player, Skin skin) {
        this.player = player;
        this.skin = skin;
        table = new Table();
        createTable();
        this.name = "Stats" ;
    }

    @Override
    public void createTable() {
        table.setFillParent(true); // Remplit tout l'écran
        table.align(Align.top); // Alignement en haut

        // Titre principal
        Label titleLabel = new Label(name, skin);
        titleLabel.setAlignment(Align.center);
        table.add(titleLabel).colspan(2).pad(10).row();

        // Dessiner un rectangle pour Level, Job, Title
        Table levelJobTitleTable = new Table();
        levelJobTitleTable.setBackground(skin.newDrawable("white", 0.2f, 0.2f, 0.2f, 1)); // Rectangle gris
        levelJobTitleTable.add(new Label("Level: " + player.getComponent(ExperienceComponent.class).getLevel(), skin)).pad(5).row();
        levelJobTitleTable.add(new Label("Job: " + player.getComponent(StatsComponent.class).getJob(), skin)).pad(5).row();
        levelJobTitleTable.add(new Label("Title: " + player.getComponent(StatsComponent.class).getTitle(), skin)).pad(5);

        table.add(levelJobTitleTable).colspan(2).pad(10).fillX().row();

        // Dessiner un rectangle pour HP, MP, Fatigue
        Table resourceTable = new Table();
        resourceTable.setBackground(skin.newDrawable("white", 0.2f, 0.2f, 0.2f, 1)); // Rectangle gris
        resourceTable.add(new Label("HP "+ player.getHealthComponent().getHp() + " / " + player.getHealthComponent().getMaxHp(), skin)).pad(5);
        resourceTable.add(new Label("MP "+player.getComponent(StatsComponent.class).getMp() + " / " + player.getComponent(StatsComponent.class).getMaxMp(), skin)).pad(5).row();
        resourceTable.add(new Label("Fatigue "+player.getComponent(StatsComponent.class).getFatigue() , skin)).align(Align.center);

        table.add(resourceTable).colspan(2).pad(10).fillX().row();

        // Dessiner un rectangle pour les stats (STR, AGI, Luck)
        Table statsTable = new Table();
        statsTable.setBackground(skin.newDrawable("white", 0.2f, 0.2f, 0.2f, 1)); // Rectangle gris
        addStatWithIncreaseButton(statsTable, "STR", player.getStrengthComponent().getStrength());
        addStatWithIncreaseButton(statsTable, "AGI", player.getSpeed());
        addStatWithIncreaseButton(statsTable, "Luck", player.getComponent(StatsComponent.class).getLuck());

        table.add(statsTable).colspan(2).pad(10).fillX().row();

        // Dessiner un rectangle pour les points disponibles
        Table pointsTable = new Table();
        pointsTable.setBackground(skin.newDrawable("white", 0.2f, 0.2f, 0.2f, 1)); // Rectangle gris
        Label pointsLabel = new Label("Available Points: " + player.getComponent(StatsComponent.class).getStatPoints(), skin);
        pointsLabel.setAlignment(Align.bottomRight);
        pointsTable.add(pointsLabel).pad(10);

        table.add(pointsTable).colspan(2).pad(10).fillX().row();
    }

    private void addStatWithIncreaseButton(Table statsTable, String statName, int statValue) {
        // Ajouter le label pour la statistique
        Label statLabel = new Label(statName + ": " + statValue, skin);

        // Ajouter un bouton pour augmenter la statistique
        TextButton increaseButton = new TextButton("+", skin);
        increaseButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (player.getComponent(StatsComponent.class).getStatPoints() > 0) {
                    // Met à jour la statistique et réduit les points disponibles
                    switch (statName) {
                        case "STR":
                            player.getStrengthComponent().setStrength(player.getStrengthComponent().getStrength() + 1);
                            break;
                        case "AGI":
                            player.setSpeed(player.getSpeed() + 1);
                            break;
                        case "Luck":
                            player.getComponent(StatsComponent.class).setLuck(player.getComponent(StatsComponent.class).getLuck() + 1);
                            break;
                    }

                    player.getComponent(StatsComponent.class).setStatPoints(player.getComponent(StatsComponent.class).getStatPoints() - 1);

                    // Met à jour l'interface
                    update();
                }
            }
        });

        statsTable.add(statLabel).pad(5).align(Align.left);
        statsTable.add(increaseButton).pad(5).align(Align.right).row();
    }

    @Override
    public void update() {
        // Met à jour les statistiques affichées
        table.clear();
        createTable();
    }

    @Override
    public Table getTable() {
        return table;
    }

    @Override
    public String getName(){
        return name;
    }
}
