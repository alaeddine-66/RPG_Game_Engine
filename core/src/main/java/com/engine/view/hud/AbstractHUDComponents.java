package com.engine.view.hud;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.OrthographicCamera;


public abstract class AbstractHUDComponents {

    protected OrthographicCamera camera;
    protected SpriteBatch batch;
    protected HUDDataProvider dataProvider;
    protected BitmapFont font;

    public AbstractHUDComponents(SpriteBatch batch, OrthographicCamera camera, HUDDataProvider dataProvider) {
        this.camera = camera;
        this.batch = batch;
        this.dataProvider = dataProvider;
        font = new BitmapFont();
    }

    public abstract void render();
}

