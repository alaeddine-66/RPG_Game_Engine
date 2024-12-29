package com.engine.model.resource;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class TextureRegionLoader implements ResourceLoader<TextureRegion> {
    @Override
    public TextureRegion load(String path) {
        return new TextureRegion(new Texture(path));
    }
}
