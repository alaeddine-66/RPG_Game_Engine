package com.engine.view.hud;


import com.engine.model.entity.components.CoinPurse;
import com.engine.model.entity.components.ExperienceComponent;
import com.engine.model.waves.WaveManager;
import com.engine.model.entity.player.Player;

public class HUDDataProvider {

    private final Player player;
    private final WaveManager waveManager;

    public HUDDataProvider(Player player, WaveManager waveManager) {
        this.player = player;
        this.waveManager = waveManager;
    }

    public int getCurrentHealth() {
        return player.getHealthComponent().getHp();
    }

    public int getMaxHealth() {
        return player.getHealthComponent().getMaxHp();
    }

    public int getCurrentExp() {
        return player.getComponent(ExperienceComponent.class).getExp();
    }

    public int getMaxExp() {
        return player.getComponent(ExperienceComponent.class).getMaxExp();
    }

    public int getCurrentLevel(){
        return player.getComponent(ExperienceComponent.class).getLevel();
    }

    public int getCurrentWave() {
        return waveManager.getCurrentWave().getWaveNumber();
    }

    public int getWaveNumber() {
        return waveManager.getTotalWaves();
    }

    public int getCoins() {
        return player.getComponent(CoinPurse.class).getCoins();
    }

    // Ajoutez d'autres méthodes nécessaires si le HUD requiert plus de données.
}
