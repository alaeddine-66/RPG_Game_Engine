package com.engine.model.waves;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;
import com.engine.model.data.EnemyData;
import com.engine.model.entity.enemy.model.Enemy;
import com.engine.model.entity.enemy.manager.EnemyManager;

import java.util.List;
import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;
import java.util.HashMap;

public class WaveManager {
    private final Json json;
    private WaveSet waveSet;
    private final List<Enemy> spawnedEnemies;
    private final Queue<SpawnTask> spawnQueue;
    private float elapsedTime;
    private int waveNumber = 0;
    private HashMap<String, EnemyData> enemyData;
    private EnemyManager enemyManager;

    public WaveManager(HashMap<String, EnemyData> enemyData , EnemyManager enemyManager) {
        json = new Json();
        spawnedEnemies = new ArrayList<>();
        spawnQueue = new LinkedList<>();
        elapsedTime = 0;
        this.enemyData = enemyData;
        this.enemyManager = enemyManager;
    }

    public void loadWave(String directoryPath) {
        FileHandle directory = Gdx.files.internal(directoryPath);

        List<Wave> waves = new ArrayList<>();
        for (FileHandle file : directory.list(".json")) { // Filtrer uniquement les fichiers .json
            Wave wave = json.fromJson(Wave.class, file);
            waves.add(wave);
            waveNumber++;
        }

        // Trier les vagues par leur numéro
        waves.sort((w1, w2) -> Integer.compare(w1.getWaveNumber(), w2.getWaveNumber()));
        waveSet = new WaveSet(waves);


        loadCurrentWave();
    }

    private void loadCurrentWave() {
        Wave currentWave = waveSet.getCurrentWave();
        if (currentWave != null) {
            spawnQueue.clear();
            elapsedTime = 0;
            for (EnemyGroup enemyGroup : currentWave.getEnemies()) {
                spawnQueue.add(new SpawnTask(enemyGroup, elapsedTime));
                elapsedTime += enemyGroup.getDelaytoSpawn() / 1000f;
            }
            elapsedTime = 0; // Réinitialisation pour le suivi réel
        }
    }

    public void update(float delta) {
        elapsedTime += delta;

        // Gestion des spawns pour la vague actuelle
        while (!spawnQueue.isEmpty() && spawnQueue.peek().isReadyToSpawn(elapsedTime)) {
            SpawnTask task = spawnQueue.poll();
            List<Enemy> enemies = enemyManager.generateEnemies(
                task.getEnemyGroup().getQuantity(),
                enemyData.get(task.getEnemyGroup().getId())
            );
            spawnedEnemies.addAll(enemies);
        }

    }
    public boolean isWaveCompleted() {
        return spawnQueue.isEmpty() && spawnedEnemies.isEmpty();
    }

    public boolean hasNextWave() {
        return waveSet.hasNextWave();
    }

    public void proceedToNextWave() {
        if (hasNextWave()) {
            waveSet.nextWave();
            loadCurrentWave();
        }else {
        System.out.println("Toutes les vagues ont été complétées !");
    }
    }

    public Wave getCurrentWave() {
        return waveSet != null ? waveSet.getCurrentWave() : null;
    }

    public List<Enemy> getEnemies() {
        return spawnedEnemies;
    }

    public int getTotalWaves() {
        return waveNumber;
    }

}
