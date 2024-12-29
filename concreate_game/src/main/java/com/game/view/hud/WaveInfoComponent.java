package com.game.view.hud;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.engine.view.hud.AbstractHUDComponents;
import com.engine.view.hud.HUDDataProvider;


public class WaveInfoComponent extends AbstractHUDComponents {

    public WaveInfoComponent(SpriteBatch batch, OrthographicCamera camera, HUDDataProvider dataProvider) {
        super(batch, camera, dataProvider);
    }

    @Override
    public void render() {
        float waveX = camera.position.x + camera.viewportWidth / 2 - 130;
        float waveY = camera.position.y + camera.viewportHeight / 2 - 10;
        font.getData().setScale(1.5f); // Double la taille du texte

        batch.begin();
        font.draw(batch, "Wave: " + dataProvider.getCurrentWave() + " / " + dataProvider.getWaveNumber(), waveX, waveY);
        batch.end();

        font.getData().setScale(1f); // Double la taille du texte

    }
}
