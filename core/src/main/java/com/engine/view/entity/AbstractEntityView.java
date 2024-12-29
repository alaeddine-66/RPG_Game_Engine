package com.engine.view.entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.engine.model.resource.ResourceManager;

public abstract class AbstractEntityView implements IEntityView {
    protected SpriteBatch batch;
    protected ResourceManager rm;

    public AbstractEntityView(SpriteBatch batch, ResourceManager rm) {
        this.batch = batch;
        this.rm = rm;
    }

}
