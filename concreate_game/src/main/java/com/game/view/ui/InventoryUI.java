package com.game.view.ui;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.engine.model.entity.Item;
import com.engine.model.entity.player.Player;
import com.engine.model.entity.components.Inventory;
import com.engine.view.ui.IMenuSection;

/**
 * La classe <code>InventoryUI</code> gère l'affichage de l'interface utilisateur de l'inventaire
 * du joueur. Elle utilise une <code>Table</code> pour organiser les éléments de l'inventaire sous forme de boutons.
 * Chaque bouton représente un objet de l'inventaire du joueur. La table est mise à jour à chaque modification de l'inventaire.
 */
public class InventoryUI implements IMenuSection {
    private Table table;
    private Player player;
    private Skin skin;
    private final String name ;

    /**
     * Constructeur de la classe <code>InventoryUI</code>.
     *
     * @param player L'objet représentant le joueur, qui contient l'inventaire à afficher.
     * @param skin La peau (style) utilisée pour les éléments de l'interface.
     */
    public InventoryUI(Player player, Skin skin) {
        this.player = player;
        this.skin = skin;
        table = new Table();
        this.name = "Inventory";
        createTable(); // Initialisation de l'inventaire
    }

    /**
     * Crée et organise les éléments de l'inventaire dans la table. Chaque objet dans l'inventaire du joueur
     * est représenté par un bouton, et l'ensemble des éléments est ajouté à la table.
     */
    @Override
    public void createTable() {
        Label inventoryLabel = new Label(name , skin);
        table.add(inventoryLabel).pad(10).row();

        for (Item item : player.getComponent(Inventory.class).getInventory()) {
            TextButton itemButton = new TextButton(item.getName(), skin);
            table.add(itemButton).pad(10).row();
        }
    }

    /**
     * Met à jour l'interface utilisateur de l'inventaire. La table est reconstruite pour refléter les
     * changements dans l'inventaire du joueur.
     */
    @Override
    public void update() {
        // Reconstruire la table si nécessaire
        table.clear();
        createTable();
    }

    /**
     * Retourne la table contenant les éléments de l'inventaire.
     *
     * @return La table représentant l'interface de l'inventaire.
     */
    @Override
    public Table getTable() {
        return table;
    }

    @Override
    public String getName(){return name;}

}
