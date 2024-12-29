package com.engine.view.ui;

import com.badlogic.gdx.scenes.scene2d.ui.Table;

public interface IMenuSection {
    void createTable();
    void update();
    Table getTable();
    String getName();
}
