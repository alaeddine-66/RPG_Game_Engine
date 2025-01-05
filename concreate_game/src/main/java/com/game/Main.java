package com.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

import com.engine.controller.IInputHandler;
import com.engine.controller.LibGdxInputHandler;
import com.engine.model.collectible.DroppedItemManager;
import com.engine.model.ObserverPattern.Observer;
import com.engine.model.entity.components.hitBox.CollisionStrategy;
import com.engine.model.entity.components.hitBox.HitBox;
import com.engine.model.entity.components.hitBox.RectangleCollisionStrategy;
import com.engine.model.entity.components.hitBox.RectangleHitBox;
import com.engine.model.entity.components.hitBox.factory.CircleHitBoxFactory;
import com.engine.model.entity.components.hitBox.factory.HitBoxFactory;
import com.engine.model.entity.components.hitBox.factory.RectangleHitBoxFactory;
import com.engine.model.entity.enemy.factory.AbstractEnemyBuilder;
import com.engine.model.map.MapObjects.HitBoxGenerationStrategyRegistry;
import com.engine.model.map.MapObjects.RectangleHitBoxGenerationStrategy;
import com.engine.model.projectile.ProjectileBehavior.DefaultProjectileBehavior;
import com.engine.model.projectile.ProjectileBehavior.IProjectileBehavior;
import com.engine.model.projectile.ProjectileBehavior.factory.DefaultProjectileBehaviorFactory;
import com.engine.model.projectile.ProjectileBehavior.factory.PiercingProjectileBehaviorFactory;
import com.engine.model.projectile.ProjectileBehavior.factory.PursuitProjectileBehaviorFactory;
import com.engine.model.projectile.effect.IEffectBehavior;
import com.engine.model.projectile.effect.PowerBoostEffectFactory;
import com.engine.model.resource.FactoryProvider;
import com.engine.model.map.MapManager;
import com.engine.model.resource.*;
import com.engine.model.weapon.BehaviorRegistry;
import com.engine.model.weapon.fire.IFireBehavior;
import com.engine.model.weapon.fire.MultiShotFireBehaviorFactory;
import com.engine.model.weapon.fire.SingleShotFireBehaviorFactory;
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
    private MapManager gameMap;
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
        DataManager.getInstance().loadEnemyData("Normal" , "concreate_game/resources/data/enemies/Normal.json");
        DataManager.getInstance().loadEnemyData("Magician" , "concreate_game/resources/data/enemies/Magician.json");
        DataManager.getInstance().loadEnemyData("Necromancer" , "concreate_game/resources/data/enemies/Necromancer.json");
        DataManager.getInstance().loadEnemyData("Bat" , "concreate_game/resources/data/enemies/Bat.json");

        //Load Projectiles Data
        DataManager.getInstance().loadProjectileData("Bullet", "concreate_game/resources/data/Projectile/Bullet.json");
        DataManager.getInstance().loadProjectileData("FireBall", "concreate_game/resources/data/Projectile/FireBall.json");

        //Load Weapons Data
        DataManager.getInstance().loadWeaponData("Gun" , "concreate_game/resources/data/weapons/gun.json");
        DataManager.getInstance().loadWeaponData("Magic Stick" , "concreate_game/resources/data/weapons/magic_stick.json");
        DataManager.getInstance().loadWeaponData("Basic Crossbow" , "concreate_game/resources/data/weapons/crossbow.json");

        //Load Weapons factories
        DataManager.getInstance().loadWeaponFactory("Gun" , new GunFactory());
        DataManager.getInstance().loadWeaponFactory("Magic Stick" , new MagicStickFactory());

        //Load Items Data
        DataManager.getInstance().loadItemsData("coin", "concreate_game/resources/data/collectible/Coin.json");
        DataManager.getInstance().loadItemsData("exp", "concreate_game/resources/data/collectible/exp.json");

        //Load Items factories
        DataManager.getInstance().loadItemsFactories("coin" , new CoinFactory());
        DataManager.getInstance().loadItemsFactories("exp" , new ExperienceFactory());

        //Load BackgroundMusic
        AudioManager.getInstance().loadBackGroundMusic("concreate_game/resources/sounds/Dark Descent.mp3");
        AudioManager.getInstance().loadSounds("reload" ,"concreate_game/resources/sounds/reload.mp3" );
        AudioManager.getInstance().loadSounds("zombie" ,"concreate_game/resources/sounds/zombie-1.mp3" );
        AudioManager.getInstance().loadSounds("weaponAttack" ,"concreate_game/resources/sounds/weaponAttack.mp3" );

    }

    @Override
    public void create () {

        // Initialize resource manager
        rm = new ResourceManager();

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        skin = new Skin(Gdx.files.internal("concreate_game/resources/data/Skins/uiskin.json"));
        AudioManager.getInstance().playMusic();
        initilaizeData();

        // create an orthographic camera
        cam = new OrthographicCamera();
        cam.setToOrtho(false, 600, 400);
        inputHandler = new LibGdxInputHandler();
        inputHandler.bindAction("right" , "Right");
        inputHandler.bindAction("left" , "Left");
        inputHandler.bindAction("up" , "Up");
        inputHandler.bindAction("down" , "Down");
        inputHandler.bindAction("shoot" , "Space");
        inputHandler.bindAction("inventory" , "I");


        batch = new SpriteBatch();

        HitBoxGenerationStrategyRegistry ObstaclesRegistry = new HitBoxGenerationStrategyRegistry();
        ObstaclesRegistry.registerStrategy(RectangleMapObject.class , new RectangleHitBoxGenerationStrategy());
        gameMap = new MapManager("concreate_game/assets/maps/map1/map.tmx" , ObstaclesRegistry); // Charger une carte
        collisionManager = new CollisionManager(gameMap.getCurrentMapLoader());

        FactoryProvider<HitBoxFactory> factoryHitBoxRegistery = new FactoryProvider();
        factoryHitBoxRegistery.registerFactory("rectangle",new RectangleHitBoxFactory());
        factoryHitBoxRegistery.registerFactory("circle",new CircleHitBoxFactory());


        // Load player and Weapon data
        PlayerData playerData = DataManager.loadJsonData("concreate_game/resources/data/player.json", PlayerData.class);
        //Load Projectiles Factories
        DataManager.getInstance().loadProjectileFactory("Bullet",new BulletFactory(DataManager.getInstance().getProjectileData("Bullet") ,
            factoryHitBoxRegistery.getFactory(DataManager.getInstance().getProjectileData("Bullet").getHitBoxType())));
        DataManager.getInstance().loadProjectileFactory("FireBall",new BulletFactory(DataManager.getInstance().getProjectileData("FireBall"),
            factoryHitBoxRegistery.getFactory(DataManager.getInstance().getProjectileData("FireBall").getHitBoxType())));

        BehaviorRegistry<IFireBehavior> fireBehaviorRegistry = new BehaviorRegistry<>();
        fireBehaviorRegistry.register("SingleShotFireBehavior", new SingleShotFireBehaviorFactory());
        fireBehaviorRegistry.register("MultiShotFireBehavior", new MultiShotFireBehaviorFactory());

        BehaviorRegistry<IEffectBehavior> EffectBehaviorRegistry = new BehaviorRegistry<>();
        EffectBehaviorRegistry.register("PowerBoostEffect", new PowerBoostEffectFactory());


        BehaviorRegistry<IProjectileBehavior> projectileBehaviorRegistry = new BehaviorRegistry<>();
        projectileBehaviorRegistry.register("Default", new DefaultProjectileBehaviorFactory());
        projectileBehaviorRegistry.register("Piercing", new PiercingProjectileBehaviorFactory());
        projectileBehaviorRegistry.register("Pursuit", new PursuitProjectileBehaviorFactory());

        WeaponManager weaponManager = new WeaponManager(fireBehaviorRegistry,EffectBehaviorRegistry,projectileBehaviorRegistry);
        weapon = weaponManager.createWeapon(playerData.getStartWeapon(),DataManager.getInstance().getAllWeaponData());

        Vector2 PlayerPosition = new Vector2(playerData.getStartPosition().getX(), playerData.getStartPosition().getY());
        Vector2 PlayerSize = new Vector2(playerData.getWidth() , playerData.getHeight());
        HitBox PlayerHitBox = factoryHitBoxRegistery.getFactory(playerData.getHitBoxType()).createHitBox(PlayerPosition , PlayerSize);
        IMovement playerMovement = new PlayerMovement(PlayerHitBox,playerData.getSpeed(), collisionManager );
        ICombat playerCombat = new PlayerCombat(weapon);
        Mainplayer = new BasicPlayer(playerData , inputHandler , playerMovement , playerCombat , PlayerHitBox);
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
        upgradeManager.loadUpgrades("concreate_game/resources/data/weapons/weapon_upgrades.json"); // Charge les améliorations
        upgradeManager.addEffect("damage_increase" , new DamageIncreaseEffect());
        upgradeManager.addEffect("fire_rate" , new FireRateEffect());
        upgradeManager.addEffect("increase_shot_count" , new MultiShotEffect());
        upgradeManager.addEffect("piercing" , new PiercingEffect());
        upgradeManager.addEffect("pursuit_projectiles" , new PursuitProjectilesEffect());
        upgradeManager.addEffect("increase_magazine_size" , new IncreaseMagazineEffect());
        upgradeManager.addEffect("quick_reload" , new ReloadIncreaseEffect());

        enemytype = EnemyDataLoader.loadEnemyDataFromDirectory("concreate_game/resources/data/enemies");
        SpawnPositionStrategy spawnStrategy = new BorderSpawnStrategy(collisionManager);
        //Load Enemies Factories
        FactoryProvider<AbstractEnemyBuilder> factoryRegistery = new FactoryProvider();
        factoryRegistery.registerFactory("Normal",new NormalEnemyFactory(collisionManager,enemytype.get("Normal"),factoryHitBoxRegistery.getFactory(enemytype.get("Normal").getHitBoxType())));
        factoryRegistery.registerFactory("Magician",new MagicienFactory(collisionManager,enemytype.get("Magician"),factoryHitBoxRegistery.getFactory(enemytype.get("Magician").getHitBoxType())));
        factoryRegistery.registerFactory("Necromancer",new NecromancerFactory(collisionManager,enemytype.get("Necromancer"),factoryHitBoxRegistery.getFactory(enemytype.get("Necromancer").getHitBoxType())));
        factoryRegistery.registerFactory("Bat",new BatFactory(collisionManager,enemytype.get("Bat"),factoryHitBoxRegistery.getFactory(enemytype.get("Bat").getHitBoxType())));
        enemyManager = new EnemyManager(collisionManager,spawnStrategy , factoryRegistery , factoryHitBoxRegistery);

        waveManager = new WaveManager(enemytype , enemyManager);
        waveManager.loadWave("concreate_game/resources/data/waves");

        enemyRenderer = new EnemyView(waveManager.getEnemies() ,rm , batch );
        enemyController = new EnemyController(droppedItemManager);

        weaponView = new WeaponView(rm , batch ,Mainplayer);
        projectileView = new ProjectileView(rm ,batch, weapon.getProjectiles());

        // Créer le contrôleur
        gameController = new GameController(Mainplayer , cam , collisionManager );

        List<IMenuSection> sections = new ArrayList<>();
        sections.add(new InventoryUI(Mainplayer, skin));
        //sections.add(new Store(Mainplayer, skin));
        sections.add(new StatsUI(Mainplayer, skin));

        menu = new Menu(stage, skin , sections);

        mapRenderer = new MapRenderer(gameMap.getCurrentMapLoader().getMap() , cam );
        waveEndScreen = new WaveEndScreen(stage, skin , waveManager , menu);

        levelUpPopup = new LevelUpScreen(stage, skin, upgradeManager );

        hudManager = new HUDManager();

        hudManager.addComponent(new HealthBarComponent(batch, cam, Mainplayer.getHealthComponent()));
        hudManager.addComponent(new ExpBarComponent(batch, cam, Mainplayer.getComponent(ExperienceComponent.class)));
        hudManager.addComponent(new WaveInfoComponent(batch , cam , waveManager));
        hudManager.addComponent(new CurrencyInfoComponent(batch , cam , Mainplayer.getComponent(CoinPurse.class).getCoins()));

    }

    public void update(float dt){
        gameController.update();
        waveManager.update(dt);
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

        if ( inputHandler.isKeyJustPressed("inventory") && !waveEndScreen.isVisible() && !levelUpPopup.levelUpWindowOpened()) {
            menu.toggleHUD();
        }

        if (waveManager.isWaveCompleted() && !waveEndScreen.isVisible()) {
            waveEndScreen.update();
            waveEndScreen.toggleVisibility();
        }

        if (Mainplayer.getComponent(ExperienceComponent.class).hasLeveledUp()) {
            levelUpPopup.showLevelUpWindow(Mainplayer.getWeapon());
            Mainplayer.getComponent(ExperienceComponent.class).resetLevelUpFlag();
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
        enemyRenderer.dispose();
        mapRenderer.dispose();
    }
}
