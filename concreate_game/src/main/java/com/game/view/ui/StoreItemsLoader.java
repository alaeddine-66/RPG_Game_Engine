package com.game.view.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;

import java.util.HashMap;
import java.util.Map;

public class StoreItemsLoader {
    private final String filePath;
    private JsonValue items;

    public StoreItemsLoader(String filePath) {
        this.filePath = filePath;
        loadItems();
    }

    private void loadItems() {
        JsonReader jsonReader = new JsonReader();
        items = jsonReader.parse(Gdx.files.internal(filePath));
    }

    public Map<String, Map<String, Object>> getItems() {
        Map<String, Map<String, Object>> parsedItems = new HashMap<>();

        for (JsonValue item : items) {
            Map<String, Object> itemData = new HashMap<>();
            itemData.put("value", item.getInt("value", 0));
            itemData.put("category", item.getString("category", ""));
            itemData.put("Description", item.getString("Description", ""));
            itemData.put("effect", item.get("effect"));

            parsedItems.put(item.name(), itemData);
        }
        return parsedItems;
    }
}
