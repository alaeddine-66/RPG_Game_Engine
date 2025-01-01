package com.engine.controller;


public interface IInputHandler {
    void bindAction(String action, String keyName);
    boolean isKeyPressed(String action);
    boolean isKeyJustPressed(String action);
}
