package com.engine.model.entity.enemy.manager;

import com.engine.model.data.EnemyData;
import com.engine.model.resource.DataManager;

import java.io.File;
import java.util.HashMap;

public class EnemyDataLoader {

    // Chargement des données des ennemis
    public  static HashMap<String, EnemyData> loadEnemyDataFromDirectory(String directoryPath) {
        HashMap<String, EnemyData> enemyDataMap = new HashMap<>();
        File dir = new File(directoryPath);
        if (dir.exists() && dir.isDirectory()) {
            File[] files = dir.listFiles((dir1, name) -> name.endsWith(".json"));
            if (files != null) {
                for (File file : files) {
                    try {
                        EnemyData data = DataManager.loadJsonData(file.getAbsolutePath(), EnemyData.class);
                        if (data != null) {
                            enemyDataMap.put(data.getId(), data);
                        }
                    } catch (Exception e) {
                        throw new IllegalStateException("Erreur lors du chargement du fichier: " + file.getName());
                    }
                }

            }
        }
        return enemyDataMap;
    }
}
