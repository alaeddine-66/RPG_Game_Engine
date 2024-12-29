package com.game.view.hud;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Color;
import com.engine.view.hud.AbstractHUDComponents;
import com.engine.view.hud.HUDDataProvider;


public class ExpBarComponent extends AbstractHUDComponents {

    private ShapeRenderer shapeRenderer;
    private Texture xpTexture;


    public ExpBarComponent(SpriteBatch batch, OrthographicCamera camera, HUDDataProvider dataProvider) {
        super(batch, camera, dataProvider);
        this.shapeRenderer = new ShapeRenderer();
        this.xpTexture = new Texture("concreate_game/src/assets/bg/xp.png");
    }

    @Override
    public void render() {
        float expBarX = camera.position.x - camera.viewportWidth / 2 + 20;
        float expBarY = camera.position.y - camera.viewportHeight / 2 + 5;
        float barWidth = camera.viewportWidth / 2 - camera.viewportWidth / 8;

        shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(new Color(0, 40 / 255f, 0, 1)); // Background color
        shapeRenderer.rect(expBarX, expBarY, barWidth, 16);

        shapeRenderer.setColor(new Color(0, 255 / 255f, 0, 1)); // XP bar color
        shapeRenderer.rect(expBarX, expBarY, barWidth * dataProvider.getCurrentExp() / dataProvider.getMaxExp(), 16);
        shapeRenderer.end();

        batch.begin();
        batch.draw(xpTexture, expBarX - 16, expBarY, 16, 16);
        font.draw(batch, "to level " + (dataProvider.getCurrentLevel() + 1), expBarX +barWidth+ 10, expBarY + 12);
        batch.end();
    }
}
