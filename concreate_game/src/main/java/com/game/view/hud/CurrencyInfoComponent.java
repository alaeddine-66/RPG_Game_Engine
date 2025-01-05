package com.game.view.hud;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.engine.view.hud.AbstractHUDComponents;


public class CurrencyInfoComponent extends AbstractHUDComponents {

    private Texture coinTexture;
    private int coins ;

    public CurrencyInfoComponent(SpriteBatch batch, OrthographicCamera camera , int coins) {
        super(batch, camera);
        this.coins = coins ;
        this.coinTexture =  new Texture("concreate_game/assets/bg/coin.png");
    }

    @Override
    public void render() {
        float currencyX = camera.position.x + camera.viewportWidth / 2 - 50;
        float currencyY = camera.position.y - camera.viewportHeight / 2 + 20;

        batch.begin();
        batch.draw(coinTexture, currencyX, currencyY, 64, 64);
        font.draw(batch, "" + coins , currencyX - 20, currencyY + 20 );
        batch.end();

    }
}
