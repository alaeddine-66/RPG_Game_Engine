package com.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.Input;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

import com.engine.controller.IInputHandler;
import com.engine.controller.LibGdxInputHandler;
import com.engine.model.collectible.DroppedItemManager;
import com.engine.model.ObserverPattern.Observer;
import com.engine.model.resource.*;
import com.game.model.collectible.factory.CoinFactory;
import com.game.model.collectible.factory.ExperienceFactory;
import com.engine.model.entity.IAttackable;
import com.engine.model.entity.player.ICombat;
import com.engine.model.entity.components.*;
import com.game.model.entity.components.strategies.ExponentialExperienceStrategy;
import com.engine.model.entity.enemy.spawn.BorderSpawnStrategy;
import com.engine.model.entity.enemy.spawn.SpawnPositionStrategy;
import com.engine.model.entity.player.IMovement;
import com.engine.model.map.CollisionManager;
import com.engine.model.weapon.WeaponManager;
import com.engine.model.weapon.upgrade.UpgradeManager;
import com.engine.model.map.MapLoader;
import com.engine.model.map.IMapCollisionChecker;
import com.engine.model.entity.player.Player;
import com.engine.model.data.PlayerData;
import com.engine.model.entity.enemy.manager.EnemyManager;
import com.engine.controller.EnemyController;
import com.engine.model.data.EnemyData;
import com.engine.model.waves.WaveManager;
import com.engine.controller.GameController;
import com.engine.model.entity.enemy.manager.EnemyDataLoader;
import com.engine.view.Items.DroppedItemView;
import com.engine.view.entity.MapRenderer;
import com.engine.view.hud.*;
import com.engine.model.weapon.model.AbstractWeapon;
import com.engine.view.ui.*;

import com.game.model.entity.components.StatsComponent;
import com.game.model.entity.enemy.factory.BatFactory;
import com.game.model.entity.enemy.factory.MagicienFactory;
import com.game.model.entity.enemy.factory.NecromancerFactory;
import com.game.model.entity.enemy.factory.NormalEnemyFactory;
import com.game.model.entity.player.BasicPlayer;
import com.game.model.entity.player.PlayerMovement;
import com.game.model.entity.player.PlayerCombat;
import com.game.model.projectile.factory.BulletFactory;
import com.game.model.weapon.factory.GunFactory;
import com.game.model.weapon.factory.MagicStickFactory;
import com.game.model.weapon.upgrade.*;
import com.game.view.entity.EnemyView;
import com.game.view.entity.PlayerView;
import com.game.view.entity.ProjectileView;
import com.game.view.entity.WeaponView;
import com.game.view.hud.*;
import com.game.view.Screens.*;
import com.game.view.ui.*;


public class Main extends ApplicationAdapter {

    // Core game components
    private SpriteBatch batch;
    private Player Mainplayer;
    private EnemyManager enemyManager;
    private HashMap<String,EnemyData> enemytype;
    private MapLoader gameMap;
    private IMapCollisionChecker collisionManager;
    private WaveManager waveManager;
    private AbstractWeapon weapon;
    private List<Player> heros;
    private DroppedItemManager droppedItemManager;

    // Views
    private PlayerView playerView;
    private MapRenderer mapRenderer;
    private EnemyView enemyRenderer;
    private WeaponView weaponView;
    private ProjectileView projectileView;
    private WaveEndScreen waveEndScreen;
    private UpgradeManager upgradeManager;
    private LevelUpScreen levelUpPopup;
    private DroppedItemView droppedItemsView;


    //Controller
    private IInputHandler inputHandler ;
    private EnemyController enemyController;
    private GameController gameController;

    // Resources
    private ResourceManager rm;
    private HUDManager hudManager;
    private OrthographicCamera cam;
    private Stage stage;
    private Skin skin ;
    private Menu menu;

    public void initilaizeData(){
        //Load Enemies Data
        DataManager.getInstance().loadEnemyData("Normal" , "concreate_game/src/resources/data/enemies/Normal.json");
        DataManager.getInstance().loadEnemyData("Magician" , "concreate_game/src/resources/data/enemies/Magician.json");
        DataManager.getInstance().loadEnemyData("Necromancer" , "concreate_game/src/resources/data/enemies/Necromancer.json");
        DataManager.getInstance().loadEnemyData("Bat" , "concreate_game/src/resources/data/enemies/Bat.json");

        //Load Enemies Factories
        DataManager.getInstance().loadEnemyFactory("Normal",new NormalEnemyFactory());
        DataManager.getInstance().loadEnemyFactory("Magician",new MagicienFactory());
        DataManager.getInstance().loadEnemyFactory("Necromancer",new NecromancerFactory());
        DataManager.getInstance().loadEnemyFactory("Bat",new BatFactory());

        //Load Projectiles Data
        DataManager.getInstance().loadProjectileData("Bullet", "concreate_game/src/resources/data/Projectile/Bullet.json");
        DataManager.getInstance().loadProjectileData("FireBall", "concreate_game/src/resources/data/Projectile/FireBall.json");

        //Load Projectiles Factories
        DataManager.getInstance().loadProjectileFactory("Bullet",new BulletFactory(DataManager.getInstance().getProjectileData("Bullet")));
        DataManager.getInstance().loadProjectileFactory("FireBall",new BulletFactory(DataManager.getInstance().getProjectileData("FireBall")));

        //Load Weapons Data
        DataManager.getInstance().loadWeaponData("Gun" , "concreate_game/src/resources/data/weapons/gun.json");
        DataManager.getInstance().loadWeaponData("Magic Stick" , "concreate_game/src/resources/data/weapons/magic_stick.json");
        DataManager.getInstance().loadWeaponData("Basic Crossbow" , "concreate_game/src/resources/data/weapons/crossbow.json");

        //Load Weapons factories
        DataManager.getInstance().loadWeaponFactory("Gun" , new GunFactory());
        DataManager.getInstance().loadWeaponFactory("Magic Stick" , new MagicStickFactory());

        //Load Items Data
        DataManager.getInstance().loadItemsData("coin", "concreate_game/src/resources/data/collectible/Coin.json");
        DataManager.getInstance().loadItemsData("exp", "concreate_game/src/resources/data/collectible/exp.json");

        //Load Items factories
        DataManager.getInstance().loadItemsFactories("coin" , new CoinFactory());
        DataManager.getInstance().loadItemsFactories("exp" , new ExperienceFactory());

        //Load BackgroundMusic
        AudioManager.getInstance().loadBackGroundMusic("concreate_game/src/resources/sounds/Dark Descent.mp3");
        AudioManager.getInstance().loadSounds("reload" ,"concreate_game/src/resources/sounds/reload.mp3" );
        AudioManager.getInstance().loadSounds("zombie" ,"concreate_game/src/resources/sounds/zombie-1.mp3" );
        AudioManager.getInstance().loadSounds("weaponAttack" ,"concreate_game/src/resources/sounds/weaponAttack.mp3" );

    }

    @Override
    public void create () {

        // Initialize resource manager
        rm = new ResourceManager();

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        skin = new Skin(Gdx.files.internal("concreate_game/src/resources/data/Skins/uiskin.json"));
        AudioManager.getInstance().playMusic();
        initilaizeData();

        // create an orthographic camera
        cam = new OrthographicCamera();
        cam.setToOrtho(false, 600, 400);
        inputHandler = new LibGdxInputHandler();


        batch = new SpriteBatch();

        gameMap = new MapLoader("assets/maps/maps2/map.tmx"); // Charger une carte
        collisionManager = new CollisionManager(gameMap);


        // Load player and Weapon data
        PlayerData playerData = DataManager.loadJsonData("concreate_game/src/resources/data/player.json", PlayerData.class);
        weapon = WeaponManager.createWeapon(playerData.getStartWeapon(),DataManager.getInstance().getAllWeaponData());

        IMovement playerMovement = new PlayerMovement(playerData.getSpeed(), collisionManager);
        ICombat playerCombat = new PlayerCombat(weapon);
        Mainplayer = new BasicPlayer(playerData , inputHandler , playerMovement , playerCombat);
        playerView = new PlayerView(Mainplayer , playerData.getimages() , rm , batch);
        Mainplayer.addComponent(new HealthComponent(playerData.getmaxHp() , playerData.getMinHpIncrease(), playerData.getMaxHpIncrease()));
        Mainplayer.addComponent(new StrengthComponent(playerData.getStrength() , playerData.getMinDmgIncrease(), playerData.getMaxDmgIncrease()));
        Mainplayer.addComponent(new StatsComponent(playerData));
        Mainplayer.addComponent(new ExperienceComponent(new ExponentialExperienceStrategy() , playerData.getmaxExp()));
        Mainplayer.addComponent(new Inventory());
        Mainplayer.addComponent(new CoinPurse());

        for (IComponent component : Mainplayer.getComponents().values()) {
            if (component instanceof Observer) {
                Mainplayer.getComponent(ExperienceComponent.class).addObserver((Observer) component);
            }
        }

        heros = new ArrayList<>();
        heros.add(Mainplayer);

        droppedItemManager = new DroppedItemManager();
        droppedItemsView = new DroppedItemView(batch, droppedItemManager.getDroppedItems());

        upgradeManager = new UpgradeManager();
        upgradeManager.loadUpgrades("concreate_game/src/resources/data/weapons/weapon_upgrades.json"); // Charge les améliorations
        upgradeManager.addEffect("damage_increase" , new DamageIncreaseEffect());
        upgradeManager.addEffect("fire_rate" , new FireRateEffect());
        upgradeManager.addEffect("increase_shot_count" , new MultiShotEffect());
        upgradeManager.addEffect("piercing" , new PiercingEffect());
        upgradeManager.addEffect("pursuit_projectiles" , new PursuitProjectilesEffect());
        upgradeManager.addEffect("increase_magazine_size" , new IncreaseMagazineEffect());
        upgradeManager.addEffect("quick_reload" , new ReloadIncreaseEffect());


        enemytype = EnemyDataLoader.loadEnemyDataFromDirectory("concreate_game/src/resources/data/enemies");
        SpawnPositionStrategy spawnStrategy = new BorderSpawnStrategy(collisionManager);
        enemyManager = new EnemyManager(collisionManager,spawnStrategy);

        waveManager = new WaveManager();
        waveManager.loadWave("concreate_game/src/resources/data/waves");

        enemyRenderer = new EnemyView(waveManager.getEnemies() ,rm , batch );
        enemyController = new EnemyController(droppedItemManager);

        weaponView = new WeaponView(rm , batch ,Mainplayer);
        projectileView = new ProjectileView(rm ,batch, weapon.getProjectiles());

        // Créer le contrôleur
        gameController = new GameController(Mainplayer , cam );

        List<IMenuSection> sections = new ArrayList<>();
        sections.add(new InventoryUI(Mainplayer, skin));
        //sections.add(new Store(Mainplayer, skin));
        sections.add(new StatsUI(Mainplayer, skin));

        menu = new Menu(stage, skin , sections);

        mapRenderer = new MapRenderer(gameMap.getMap() , cam );
        waveEndScreen = new WaveEndScreen(stage, skin , waveManager , menu);

        levelUpPopup = new LevelUpScreen(stage, skin, upgradeManager );

        HUDDataProvider dataProvider = new HUDDataProvider(Mainplayer, waveManager);
        hudManager = new HUDManager();

        hudManager.addComponent(new HealthBarComponent(batch, cam, dataProvider));
        hudManager.addComponent(new ExpBarComponent(batch, cam, dataProvider));
        hudManager.addComponent(new WaveInfoComponent(batch , cam , dataProvider));
        hudManager.addComponent(new CurrencyInfoComponent(batch , cam , dataProvider));

    }

    public void update(float dt){
        gameController.update(dt);
        waveManager.update(dt,  enemytype , enemyManager);
        enemyController.updateEnemies(waveManager.getEnemies() , new ArrayList<IAttackable>(heros) , dt);
        Mainplayer.update(new ArrayList<IAttackable>(waveManager.getEnemies()),dt);
        // Conversion  de List<Enemy> en List<Attackable>
        weapon.update(new ArrayList<IAttackable>(waveManager.getEnemies()), collisionManager, Mainplayer);
        droppedItemManager.update(Mainplayer);
    }

    @Override
    public void render() {
        ScreenUtils.clear(Color.BLACK);
        float deltaTime = Gdx.graphics.getDeltaTime();
        mapRenderer.render();
        menu.update();
        stage.act(deltaTime);
        stage.draw();

        if (Gdx.input.isKeyJustPressed(Input.Keys.I) && !waveEndScreen.isVisible() && !levelUpPopup.levelUpWindowOpened()) {
            menu.toggleHUD();
        }
        if (waveManager.isWaveCompleted() && !waveEndScreen.isVisible()) {
            waveEndScreen.update();
            waveEndScreen.toggleVisibility();
        }

        if (Mainplayer.getComponent(ExperienceComponent.class).hasLeveledUp()) {
            levelUpPopup.showLevelUpWindow(Mainplayer.getWeapon());
            Mainplayer.getComponent(ExperienceComponent.class).resetLevelUpFlag(); // Si nécessaire
        }

        if (!menu.isHudVisible() && !waveEndScreen.isVisible() && !levelUpPopup.levelUpWindowOpened()) {
            update(deltaTime);
            batch.setProjectionMatrix(cam.combined);
            droppedItemsView.render();
            enemyRenderer.render();
            playerView.render();
            weaponView.render();
            projectileView.render();
        }

        hudManager.render();
    }

    @Override
    public void dispose () {
        batch.dispose();
        playerView.dispose();
    }
}