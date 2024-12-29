package com.engine.model.waves;

import java.util.List;

public class Wave {
    private int waveNumber;
    private List<EnemyGroup> enemies;

    public int getWaveNumber() {
        return waveNumber;
    }

    public List<EnemyGroup> getEnemies() {
        return enemies;
    }
}
