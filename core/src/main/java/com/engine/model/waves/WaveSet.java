package com.engine.model.waves;

import java.util.List;

public class WaveSet {
    private List<Wave> waves;
    private int currentWaveIndex;

    public WaveSet(List<Wave> waves) {
        this.waves = waves;
        this.currentWaveIndex = 0;
    }

    public Wave getCurrentWave() {
        if (currentWaveIndex < waves.size()) {
            return waves.get(currentWaveIndex);
        }
        return null; // Aucune vague restante
    }

    public boolean hasNextWave() {
        return currentWaveIndex < waves.size() - 1;
    }

    public void nextWave() {
        if (hasNextWave()) {
            currentWaveIndex++;
        }
    }

    public int getTotalWaves() {
        return waves.size();
    }

    public int getCurrentWaveNumber() {
        return currentWaveIndex + 1; // Les vagues commencent à 1
    }
}
