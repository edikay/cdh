package biz.lazysoft.cdh;

import java.util.Stack;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.engine.camera.Camera;
import org.anddev.andengine.engine.handler.timer.ITimerCallback;
import org.anddev.andengine.engine.handler.timer.TimerHandler;
import org.anddev.andengine.engine.options.EngineOptions;
import org.anddev.andengine.engine.options.EngineOptions.ScreenOrientation;
import org.anddev.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.anddev.andengine.entity.IEntity;
import org.anddev.andengine.entity.scene.CameraScene;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.sprite.AnimatedSprite;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.input.touch.TouchEvent;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.anddev.andengine.util.Debug;

import android.view.KeyEvent;
import biz.lazysoft.cdh.andengine.AssetPool;
import biz.lazysoft.cdh.andengine.BaseGame;
import biz.lazysoft.cdh.monsters.Monster;
import biz.lazysoft.cdh.monsters.Octopus;
import biz.lazysoft.cdh.monsters.Rectangl;
import biz.lazysoft.cdh.monsters.Spider;
import biz.lazysoft.cdh.monsters.Walus;
import biz.lazysoft.cdh.towers.Tower;

public class Level extends BaseGame {

	private static final int CAMERA_WIDTH = 1200;
	private static final int CAMERA_HEIGHT = 720;

	private Camera mCamera;
	private Scene scene;
	private CameraScene mPauseScene;	
	public static LevelManager lm;

	public Scene getScene() {
		return scene;
	}

	public void removeEntity(final IEntity entity) {
		runOnUpdateThread(new Runnable() {
			@Override
			public void run() {
				/* Now it is save to remove the entity! */
				scene.detachChild(entity);
			}
		});
	}

	@Override
	public Engine onLoadEngine() {
		mCamera = new Camera(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT);
		EngineOptions options = new EngineOptions(true,
				ScreenOrientation.LANDSCAPE, new RatioResolutionPolicy(
						CAMERA_WIDTH, CAMERA_HEIGHT), mCamera);
		options.setNeedsSound(true);
		options.setNeedsMusic(true);
		return new Engine(options);

	}

	@Override
	public void onLoadResources() {
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
		// Initialize
		AssetPool pool = AssetPool.getInstance();
		pool.init(getApplication(), getEngine());

		// Tiled
		pool.loadTiledTextureRegion(Names.walus, "monsters/walus_tiled.png",
				512, 512, 1, 4);
		pool.loadTiledTextureRegion(Names.spider, "monsters/spider_tiled.png",
				1024, 1024, 1, 4);
		pool.loadTiledTextureRegion(Names.rectangl,
				"monsters/rectangl_tiled.png", 1024, 1024, 1, 4);
		pool.loadTiledTextureRegion(Names.octopus,
				"monsters/octopus_tiled.png", 1024, 1024, 1, 4);
		pool.loadTiledTextureRegion(Names.cannon, "towers/cannon.png", 1024,
				1024, 1, 2);
		pool.loadTiledTextureRegion(Names.bullet, "misc/bullets.png", 1024,
				1024, 1, 3);
		pool.loadTiledTextureRegion(Names.towerspot, "misc/tower_spot.png",
				1024, 1024, 1, 1);
		pool.loadTiledTextureRegion(Names.towermenucolors,
				"misc/tower_menu_elems.png", 1024, 1024, 4, 4);
		pool.loadTiledTextureRegion(Names.el1, "misc/bullet_red.png", 1024,
				1024, 1, 3);
		pool.loadTiledTextureRegion(Names.el2, "misc/bullet_purple.png", 1024,
				1024, 1, 3);
		pool.loadTiledTextureRegion(Names.el3, "misc/bullet_blue.png", 1024,
				1024, 1, 3);
		pool.loadTiledTextureRegion(Names.closeicon, "misc/close_icon.png",
				2048, 1024, 1, 3);
		pool.loadTiledTextureRegion(Names.cannonicon, "misc/cannon_icon.png",
				2048, 1024, 1, 3);
		pool.loadTiledTextureRegion(Names.touchCatcher,
				"misc/touch_catcher.png", 2048, 1024, 1, 1);
		pool.loadTiledTextureRegion(Names.sell, "misc/sell.png", 2048, 1024, 1,
				3);
		pool.loadTiledTextureRegion(Names.upgrade, "misc/upgrade.png", 2048,
				1024, 1, 3);

		// Image
		pool.loadTextureRegion(Names.map0, "levels/level1map.png", 2048, 1024);
		pool.loadTextureRegion(Names.range, "misc/range.png", 2048, 1024);
		pool.loadTextureRegion(Names.towerspotmenu, "misc/tower_spot_menu.png",
				2048, 1024);
		pool.loadTextureRegion(Names.towermenubg, "misc/tower_menu.png", 2048,
				1024);
		pool.loadTextureRegion(Names.touchCatcher, "misc/touch_catcher.png",
				2048, 1024);

		
		
	}

	@Override
	public Scene onLoadScene() {

		AssetPool pool = AssetPool.getInstance();

		scene = new Scene();

		lm = new LevelManager(this);

		final WayPoint[] mapTrack = new WayPoint[] { new WayPoint(865, 0, 180),
				new WayPoint(865, 225, 270), new WayPoint(330, 225, 180),
				new WayPoint(330, 495, 90), new WayPoint(865, 495, 180),
				new WayPoint(865, 720, 180) };

		Sprite background = new Sprite(0, 0, pool.getTR(Names.map0));
		scene.attachChild(background);

		final Stack<Monster> monsters = new Stack<Monster>();
		monsters.add(new Walus());
		monsters.add(new Spider());
		monsters.add(new Spider());
		monsters.add(new Spider());
		monsters.add(new Spider());
		monsters.add(new Spider());
		monsters.add(new Spider());
		monsters.add(new Spider());
		monsters.add(new Spider());
		monsters.add(new Spider());
		monsters.add(new Spider());
		monsters.add(new Spider());
		monsters.add(new Spider());
		monsters.add(new Spider());
		monsters.add(new Spider());
		monsters.add(new Spider());
		monsters.add(new Spider());
		monsters.add(new Spider());
		monsters.add(new Spider());
		monsters.add(new Spider());
		monsters.add(new Spider());
		monsters.add(new Spider());
		monsters.add(new Octopus());
		monsters.add(new Rectangl());
		
		
		
		
		for(Monster mon : monsters)
		{
			mon.setTrack(mapTrack);
			lm.add(mon);
		}
		
		
		
		AnimatedSprite bt1 = new AnimatedSprite(0, 0, pool.getTTR(Names.towerspot)) {
			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
					float pTouchAreaLocalX, float pTouchAreaLocalY) {
				if(!monsters.isEmpty())
					monsters.pop().move();
					
				return super.onAreaTouched(pSceneTouchEvent, pTouchAreaLocalX,
						pTouchAreaLocalY);
			}
		};
		scene.registerTouchArea(bt1);

		

		TimerHandler timer = new TimerHandler(0.1f, true, new ITimerCallback() {

			@Override
			public void onTimePassed(TimerHandler pTimerHandler) {
				Debug.d("Money= " + lm.getMoney());
				for (Tower tower : lm.getTowers()) {
					tower.work(lm.getMonsters());
				}

			}
		});
		scene.registerUpdateHandler(timer);

		lm.addObject(Objects.TowerSpot, "E4");
		lm.addObject(Objects.TowerSpot, "e5");
		lm.addObject(Objects.TowerSpot, "C5");
		scene.registerTouchArea(background);
		
		
		//pause
		this.mPauseScene = new CameraScene(this.mCamera);
		final Sprite pausedSprite = new Sprite(0, 0, pool.getTR(Names.range));
		this.mPauseScene.attachChild(pausedSprite);
		this.mPauseScene.setBackgroundEnabled(false);
		
		
		return scene;
	}

	@Override
	public void onLoadComplete() {

	}
	
	@Override
	public void onPauseGame() {
		Debug.d("KURWA: "+mEngine.isRunning());
		
            this.scene.setChildScene(this.mPauseScene, false, true, true);
            this.mEngine.stop();
    
		super.onPauseGame();
	}
	
	@Override
	public void onResume() {
		Debug.d("KURWA1: "+mEngine.isRunning());
		super.onResume();
		Debug.d("KURWA2: "+mEngine.isRunning());
		
	}
	
	@Override
    public boolean onKeyDown(final int pKeyCode, final KeyEvent pEvent) {
            if(pKeyCode == KeyEvent.KEYCODE_MENU && pEvent.getAction() == KeyEvent.ACTION_DOWN) {
                    if(this.mEngine.isRunning()) {
                            this.scene.setChildScene(this.mPauseScene, false, true, true);
                            this.mEngine.stop();
                    } else {
                            this.scene.clearChildScene();
                            this.mEngine.start();
                    }
                    return true;
            } else {
                    return super.onKeyDown(pKeyCode, pEvent);
            }
    }


}