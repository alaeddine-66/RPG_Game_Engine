package com.game.model.entity.player;

import com.engine.controller.IInputHandler;
import com.engine.model.entity.IAttackable;
import com.engine.model.entity.player.ICombat;
import com.engine.model.data.PlayerData;
import com.engine.model.entity.player.IMovement;
import com.engine.model.entity.player.Player;

import java.util.List;

public class BasicPlayer extends Player {

    public BasicPlayer(PlayerData playerData, IInputHandler inputHandler, IMovement movement, ICombat combat) {
        super(playerData, inputHandler, movement, combat);
    }

    @Override
    public void move(float dt) {
        // Gestion des mouvements spécifiques au joueur basic
        float moveX = 0, moveY = 0;
        if (inputHandler.isMoveLeft()) moveX = -1;
        else if (inputHandler.isMoveRight()) moveX = 1;
        if (inputHandler.isMoveUp()) moveY = 1;
        else if (inputHandler.isMoveDown()) moveY = -1;

        if (moveX==1) facingRight = true;
        else if(moveX==-1) facingRight = false;

        setPosition(movement.move(position, moveX, moveY, dt, (int) boundingBox.width, (int) boundingBox.height));
        getWeapon().setRotationAngle(getRotationAngle());
    }

    @Override
    public void attack(List<IAttackable> targets) {
        // Gestion spécifique des attaques pour le joueur humain
        combat.handleAttack(inputHandler.isShooting());
    }
}
