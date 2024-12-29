package com.engine.model.entity.components;

import com.engine.model.entity.Item;

import java.util.ArrayList;
import java.util.List;

public class Inventory implements IComponent{
    private final List<Item> items;

    public Inventory() {
        this.items = new ArrayList<>();
    }

    public void addItemToInventory(Item item) {
        if (item != null) {
            items.add(item);
        }
    }

    public boolean removeItem(Item item) {
        return items.remove(item);
    }

    public boolean containsItem(Item item) {
        return items.contains(item);
    }

    public List<Item> getInventory() {
        return new ArrayList<>(items); // Retourne une copie pour éviter une modification directe
    }

    public int size() {
        return items.size();
    }
}

