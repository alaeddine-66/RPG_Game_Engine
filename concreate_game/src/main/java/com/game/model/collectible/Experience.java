package com.game.model.collectible;

import com.engine.model.collectible.Collectible;
import com.engine.model.entity.player.Player;
import com.engine.model.data.ItemData;
import com.engine.model.entity.components.ExperienceComponent;


public class Experience extends Collectible {

    public Experience(int value, ItemData data) {
        super(value, data);
    }

    @Override
    public void applyEffect(Player player) {
        player.getComponent(ExperienceComponent.class).addExp(value); // Ajouter de l'expérience au joueur
    }

}
