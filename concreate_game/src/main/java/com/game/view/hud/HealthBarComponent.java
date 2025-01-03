package com.game.view.hud;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.engine.model.entity.components.HealthComponent;
import com.engine.view.hud.AbstractHUDComponents;

public class HealthBarComponent extends AbstractHUDComponents {

    private ShapeRenderer shapeRenderer;
    private Texture heartTexture;
    private HealthComponent Health;

    public HealthBarComponent(SpriteBatch batch, OrthographicCamera camera, HealthComponent Health) {
        super(batch, camera);
        this.shapeRenderer = new ShapeRenderer();
        this.heartTexture = new Texture("concreate_game/src/assets/bg/heart.png");
        this.Health = Health;
    }

    @Override
    public void render() {
        float healthBarX = camera.position.x - camera.viewportWidth / 2 + 20;
        float healthBarY = camera.position.y - camera.viewportHeight / 2 + 30;
        float barWidth = camera.viewportWidth / 2 - camera.viewportWidth / 8;

        int currentHealth = Health.getHp();
        int maxHealth = Health.getMaxHp();

        // Fond de la barre
        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(new Color(60 / 255f, 0, 0, 1)); // Couleur de fond
        shapeRenderer.rect(healthBarX, healthBarY, barWidth, 16);

        // Barre de santé
        shapeRenderer.setColor(new Color(255 / 255f, 0, 0, 1)); // Couleur de la santé
        shapeRenderer.rect(healthBarX, healthBarY, barWidth * currentHealth / maxHealth, 16);
        shapeRenderer.end();

        // Icône et texte
        batch.begin();
        batch.draw(heartTexture, healthBarX - 16, healthBarY, 16, 16);
        font.draw(batch, currentHealth+ " / " + maxHealth, healthBarX +barWidth+ 10, healthBarY + 12);
        batch.end();
    }

}
