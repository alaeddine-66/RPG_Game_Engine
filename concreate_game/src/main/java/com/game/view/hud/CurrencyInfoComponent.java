package com.game.view.hud;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.engine.view.hud.AbstractHUDComponents;
import com.engine.view.hud.HUDDataProvider;


public class CurrencyInfoComponent extends AbstractHUDComponents {

    private Texture coinTexture;

    public CurrencyInfoComponent(SpriteBatch batch, OrthographicCamera camera, HUDDataProvider dataProvider) {
        super(batch, camera, dataProvider);
        this.coinTexture =  new Texture("bg/coin.png");
    }

    @Override
    public void render() {
        float currencyX = camera.position.x + camera.viewportWidth / 2 - 50;
        float currencyY = camera.position.y - camera.viewportHeight / 2 + 20;

        batch.begin();
        batch.draw(coinTexture, currencyX, currencyY, 64, 64);
        font.draw(batch, "" + dataProvider.getCoins() , currencyX - 20, currencyY + 20 );
        batch.end();

    }
}
