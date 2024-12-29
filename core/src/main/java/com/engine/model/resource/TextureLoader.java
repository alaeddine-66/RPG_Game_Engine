package com.engine.model.resource;

import com.badlogic.gdx.graphics.Texture;

public class TextureLoader implements ResourceLoader<Texture> {
    @Override
    public Texture load(String path) {
        return new Texture(path);
    }
}

