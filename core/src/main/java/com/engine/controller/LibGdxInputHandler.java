package com.engine.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

import java.util.HashMap;
import java.util.Map;

public class LibGdxInputHandler implements IInputHandler{

    private final Map<String, Integer> actionKeyMap = new HashMap<>();

    // Associer une action à une touche via son nom
    public void bindAction(String action, String keyName) {
        int keyCode = Input.Keys.valueOf(keyName);
        if (keyCode == -1) {
            throw new IllegalArgumentException("Key name '" + keyName + "' is not valid.");
        }
        actionKeyMap.put(action, keyCode);
    }

    @Override
    public boolean isKeyPressed(String action) {
        Integer key = actionKeyMap.get(action);
        return key != null && Gdx.input.isKeyPressed(key);
    }

    @Override
    public boolean isKeyJustPressed(String action) {
        Integer key = actionKeyMap.get(action);
        return key != null && Gdx.input.isKeyJustPressed(key);
    }

}
