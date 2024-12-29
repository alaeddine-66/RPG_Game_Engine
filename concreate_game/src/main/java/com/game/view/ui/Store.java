package com.game.view.ui;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.utils.Align;
import com.engine.model.entity.Item;
import com.engine.model.entity.player.Player;
import com.engine.model.entity.components.CoinPurse;
import com.engine.model.entity.components.Inventory;
import com.engine.model.entity.effects.IEffect;
import com.engine.view.ui.IMenuSection;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//public class Store implements IMenuSection {
//    private final Table storeTable;
//    private final Player player;
//    private final Skin skin;
//    private final Map<String, Map<String, Object>> allItems = new HashMap<>();
//    private Label descriptionLabel;
//    private final String name ;
//
//    public Store(Player player, Skin skin) {
//        this.player = player;
//        this.skin = skin;
//        this.storeTable = new Table();
//        loadAllFiles();
//        createTable();
//        this.name = "Store";
//    }
//
//    private void loadAllFiles() {
//        allItems.putAll(new StoreItemsLoader("concreate_game/src/resources/data/ui/Store/potion.json").getItems());
//        allItems.putAll(new StoreItemsLoader("concreate_game/src/resources/data/ui/Store/Armor.json").getItems());
//        allItems.putAll(new StoreItemsLoader("concreate_game/src/resources/data/ui/Store/Weapons.json").getItems());
//        allItems.putAll(new StoreItemsLoader("concreate_game/src/resources/data/ui/Store/Skills.json").getItems());
//    }
//
//    @Override
//    public void createTable() {
//        storeTable.setFillParent(true);
//        storeTable.align(Align.center);
//
//        // Titre principal
//        Label titleLabel = new Label(name, skin, "default");
//        titleLabel.setAlignment(Align.top);
//        storeTable.add(titleLabel).colspan(5).row();
//
//        // Zone de description
//        descriptionLabel = new Label("Hover over an item to see its description.", skin);
//        descriptionLabel.setAlignment(Align.center);
//
//        Table descriptionTable = new Table();
//        descriptionTable.add(descriptionLabel).center().pad(10);
//
//        // Ajouter la description sur une ligne entière
//        storeTable.add(descriptionTable).colspan(5).row();
//
//        // Créer des tables pour chaque catégorie
//        storeTable.add(createCategoryTable("Weapons", filterItems(allItems, "weapons")));
//        storeTable.add(createCategoryTable("Armor", filterItems(allItems, "Armor")));
//        storeTable.add(createCategoryTable("Potions", filterItems(allItems, "potions")));
//        storeTable.add(createCategoryTable("Skills", filterItems(allItems, "Skill")));
//    }
//
//    @Override
//    public void update() {
//
//    }
//
//    private Table createCategoryTable(String categoryName, Map<String, Map<String, Object>> categoryItems) {
//        Table categoryTable = new Table();
//
//        // Titre de la catégorie
//        Label categoryLabel = new Label(categoryName, skin, "default");
//        categoryLabel.setAlignment(Align.center);
//        categoryTable.add(categoryLabel).pad(5).row();
//
//        // Ajouter les objets de la catégorie
//        for (Map.Entry<String, Map<String, Object>> entry : categoryItems.entrySet()) {
//            String itemName = entry.getKey();
//            Map<String, Object> itemData = entry.getValue();
//
//            addItemToCategory(categoryTable, itemName , itemData);
//        }
//        return categoryTable;
//
//    }
//
//    private void addItemToCategory(Table categoryTable, String itemName, Map<String, Object> data) {
//        TextButton buyButton = new TextButton(itemName, skin);
//        int price = (int) data.get("value");
//        String description = (String) data.get("Description");
//        List<IEffect> effects = (List<IEffect>) data.get("Effect");
//
//        // Listener pour afficher la description
//        buyButton.addListener(new ClickListener() {
//            @Override
//            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
//                descriptionLabel.setText(itemName + "\nPrice: " +  price + " Gold\nDescription: " +  description);
//            }
//        });
//
//        // Listener pour le bouton d'achat
//        buyButton.addListener(new ClickListener() {
//            @Override
//            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
//                if (player.getComponent(CoinPurse.class).getCoins() >= price) {
//                    player.getComponent(CoinPurse.class).removeCoins(price);
//                    player.getComponent(Inventory.class).addItemToInventory(new Item(itemName , categoryTable.getName(), price , description,effects));
//                    System.out.println(itemName + " acheté !");
//                } else {
//                    System.out.println("Pas assez d'or pour acheter " + itemName + " !");
//                }
//            }
//        });
//
//        categoryTable.add(buyButton).pad(5);
//        categoryTable.row();
//    }
//
//    private Map<String, Map<String, Object>> filterItems(Map<String, Map<String, Object>> allItems, String category) {
//        Map<String, Map<String, Object>> filtered = new HashMap<>();
//        for (Map.Entry<String, Map<String, Object>> entry : allItems.entrySet()) {
//            Map<String, Object> itemData = entry.getValue();
//            String itemCategory = (String) itemData.get("category"); // Récupérer la catégorie depuis JSON
//            if (itemCategory != null && itemCategory.equalsIgnoreCase(category)) {
//                filtered.put(entry.getKey(), itemData);
//            }
//        }
//        return filtered;
//    }
//
//    @Override
//    public Table getTable() {
//        return storeTable;
//    }
//
//    @Override
//    public String getName(){
//        return name;
//    }
//}
