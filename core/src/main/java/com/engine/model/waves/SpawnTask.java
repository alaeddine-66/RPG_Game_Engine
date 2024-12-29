package com.engine.model.waves;

public class SpawnTask {
    private EnemyGroup enemyGroup;
    private float spawnTime; // Moment où le spawn doit se produire, en secondes

    public SpawnTask(EnemyGroup enemyGroup, float delayOffset) {
        this.enemyGroup = enemyGroup;
        this.spawnTime = delayOffset + enemyGroup.getDelaytoSpawn() / 1000f; // Calcul du temps en secondes
    }

    public boolean isReadyToSpawn(float elapsedTime) {
        return elapsedTime >= spawnTime;
    }

    public EnemyGroup getEnemyGroup() {
        return enemyGroup;
    }
}
