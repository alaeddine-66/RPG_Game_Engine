package com.engine.controller ;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;


public class LibGdxInputHandler implements IInputHandler {

    @Override
    public boolean isMoveLeft() {
        return Gdx.input.isKeyPressed(Input.Keys.LEFT);
    }

    @Override
    public boolean isMoveRight() {
        return Gdx.input.isKeyPressed(Input.Keys.RIGHT);
    }

    @Override
    public boolean isMoveUp() {
        return Gdx.input.isKeyPressed(Input.Keys.UP);
    }

    @Override
    public boolean isMoveDown() {
        return Gdx.input.isKeyPressed(Input.Keys.DOWN);
    }

    @Override
    public boolean isShooting() {
        return Gdx.input.isKeyPressed(Input.Keys.SPACE);
    }
}
