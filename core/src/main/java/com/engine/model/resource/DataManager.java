package com.engine.model.resource;

import com.badlogic.gdx.utils.Json;
import com.engine.model.collectible.builder.AbstractItemsBuilder;
import com.engine.model.data.EnemyData;
import com.engine.model.data.ItemData;
import com.engine.model.data.ProjectileData;
import com.engine.model.data.WeaponData;
import com.engine.model.entity.enemy.factory.AbstractEnemyBuilder;
import com.engine.model.weapon.factory.AbstractWeaponBuilder;
import com.engine.model.projectile.factory.IProjectileFactory;

import java.util.HashMap;
import java.util.Map;

public class DataManager {

    private static volatile DataManager instance ;
    private final HashMap<String, EnemyData> enemyDataMap;
    private final Map<String, ProjectileData> projectileMap;
    private final Map<String , IProjectileFactory> projectilefactory;
    private final Map<String , AbstractEnemyBuilder> enemyFactory;
    private final HashMap<String, WeaponData> weaponDataMap;
    private final Map<String, AbstractWeaponBuilder> weaponFactory;
    private final Map<String, ItemData> itemsData;
    private final Map<String, AbstractItemsBuilder> itemsBuilderMap;

    private DataManager( ) {
        enemyDataMap = new HashMap<>();
        projectileMap = new HashMap<>();
        projectilefactory = new HashMap<>();
        weaponDataMap = new HashMap<>();
        enemyFactory = new HashMap<>();
        weaponFactory = new HashMap<>();
        itemsBuilderMap = new HashMap<>();
        itemsData = new HashMap<>();

    }

    public static DataManager getInstance() {
        if (DataManager.instance == null) {
            synchronized(DataManager.class) {
                if (DataManager.instance == null) {
                    DataManager.instance = new DataManager();
                }
            }
        }
        return DataManager.instance;
    }

    //Enemy
    public void loadEnemyData(String type,String path){
        enemyDataMap.put(type , loadJsonData(path , EnemyData.class));
    }

    public EnemyData getEnemyData(String type) {
        return enemyDataMap.get(type);
    }

    public HashMap<String, EnemyData> getAllEnemyData() {
        return enemyDataMap;
    }

    public void loadEnemyFactory(String type,AbstractEnemyBuilder factory){
        enemyFactory.put(type , factory);
    }

    public AbstractEnemyBuilder getEnemyFactory(String type){
        return enemyFactory.get(type);
    }

    //Projectile
    public void loadProjectileData(String type,String path){
        projectileMap.put(type , loadJsonData(path,ProjectileData.class));
    }

    public ProjectileData getProjectileData(String type) {
        return projectileMap.get(type);
    }

    public void loadProjectileFactory(String type , IProjectileFactory factory) {
        projectilefactory.put(type,factory);
    }

    public IProjectileFactory getProjectileFactory(String type) {
        return projectilefactory.get(type);
    }

    //Weapon
    public void loadWeaponData(String type , String path){
        weaponDataMap.put(type , loadJsonData(path , WeaponData.class));
    }

    public WeaponData getWeaponData(String type ){
        return weaponDataMap.get(type);
    }

    public HashMap<String, WeaponData> getAllWeaponData(){
        return weaponDataMap ;
    }

    public void loadWeaponFactory(String type , AbstractWeaponBuilder factory ){
        weaponFactory.put(type , factory);
    }

    public AbstractWeaponBuilder getWeaponFactory(String type ){
        return weaponFactory.get(type);
    }

    //Items
    public void loadItemsData(String type , String path){
            itemsData.put(type , loadJsonData( path, ItemData.class));
    }

    public ItemData getItemData(String type){
        return itemsData.get(type);
    }

    public void loadItemsFactories(String type , AbstractItemsBuilder factory){
        itemsBuilderMap.put(type, factory);
    }

    public AbstractItemsBuilder getItemFactory(String name){return itemsBuilderMap.get(name);}

    //Load Data
    public static <T> T loadJsonData(String filePath, Class<T> classType) {
        Json json = new Json();
        return json.fromJson(classType, com.badlogic.gdx.Gdx.files.internal(filePath));
    }


}
