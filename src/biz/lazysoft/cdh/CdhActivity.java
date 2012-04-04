package biz.lazysoft.cdh;

import java.util.ArrayList;

import monsters.Monster;
import monsters.Spider;
import monsters.Walus;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.engine.camera.Camera;
import org.anddev.andengine.engine.handler.timer.ITimerCallback;
import org.anddev.andengine.engine.handler.timer.TimerHandler;
import org.anddev.andengine.engine.options.EngineOptions;
import org.anddev.andengine.engine.options.EngineOptions.ScreenOrientation;
import org.anddev.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.anddev.andengine.entity.IEntity;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.sprite.AnimatedSprite;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.input.touch.TouchEvent;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;

import towers.Cannon;
import towers.Tower;

public class CdhActivity extends  BaseGame{
   
	private static final int CAMERA_WIDTH = 1200;
	private static final int CAMERA_HEIGHT = 720;

	private Camera mCamera;
	public static Scene scene;
	static LevelManager lm;

	
	@Override
	public Engine onLoadEngine() {

		this.mCamera = new Camera(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT);
		return new Engine(new EngineOptions(true, ScreenOrientation.LANDSCAPE,
				new RatioResolutionPolicy(CAMERA_WIDTH, CAMERA_HEIGHT),
				this.mCamera).setNeedsSound(true));

	}

	@Override
	public void onLoadResources() {
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");

		TM.add(Names.spider,loadTiledTextureRegion("monsters/spider_tiled.png", 1024, 1024,1,4));
		TM.add(Names.walus,loadTiledTextureRegion("monsters/walus_tiled.png", 1024, 1024,1,4));
		TM.add(Names.rectangl,loadTiledTextureRegion("monsters/rectangl_tiled.png", 1024, 1024,1,4));
		TM.add(Names.octopus,loadTiledTextureRegion("monsters/octopus_tiled.png", 1024, 1024,1,4));
		TM.add(Names.map0,loadTextureRegion("levels/level1map.png", 2048, 1024));
		TM.add(Names.cannon, loadTiledTextureRegion("towers/cannon.png", 1024, 1024, 1,2));
		TM.add(Names.bullet, loadTiledTextureRegion("misc/bullets.png", 1024, 1024, 1,3));
		TM.add(Names.range,loadTextureRegion("misc/range.png", 2048, 1024));
		TM.add(Names.towermenubg,loadTextureRegion("misc/tower_menu.png", 2048, 1024));
		TM.add(Names.towerspotmenu,loadTextureRegion("misc/tower_spot_menu.png", 2048, 1024));
		TM.add(Names.towerspot,loadTiledTextureRegion("misc/tower_spot.png", 1024, 1024,1,1));
		TM.add(Names.cannonicon,loadTextureRegion("misc/cannon_icon.png", 2048, 1024));
		TM.add(Names.towermenucolors, loadTiledTextureRegion("misc/tower_menu_elems.png", 1024, 1024, 4,4));

	}

	@Override
	public Scene onLoadScene() {
		
		
		scene = new Scene();
		lm = new LevelManager(scene);
		Sprite background = new Sprite(0, 0, TM.getTR(Names.map0));
		scene.attachChild(background);
		
		Walus spider1 = new Walus();
		final Spider spider2 = new Spider();
		lm.addMonster(spider1);
		lm.addMonster(spider2);		
		
		Cannon cannon1 = new Cannon();
		cannon1.setPosition(50, 360);		
		cannon1.setLevel(1);
		lm.addTower(cannon1);
		
		final Track track = new Track();
		track.setTrack(new WayPoint(220, 0, 180),new WayPoint(220, 600, 90));
		
		AnimatedSprite bt1 = new AnimatedSprite(0, 0, TM.getTTR(Names.spider))
		{
			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
					float pTouchAreaLocalX, float pTouchAreaLocalY) {
				spider2.move(track);
				return super
						.onAreaTouched(pSceneTouchEvent, pTouchAreaLocalX, pTouchAreaLocalY);
			}
		};
		scene.registerTouchArea(bt1);
		
		
		
		
		
		spider1.move(track);	
		spider1.setPosition(0, 0);		
		
		TimerHandler timer = new TimerHandler(0.1f, true,
				new ITimerCallback() {

					@Override
					public void onTimePassed(TimerHandler pTimerHandler) {
						
						
					
						for(Tower tower:lm.getTowers())
						{
							tower.checkFire(lm.getMonsters());
						}
						

					}
				});
		scene.registerUpdateHandler(timer);
		
		TowerSpot towerSpot1 = new TowerSpot();
		towerSpot1.setPosition(200, 5);
		lm.addTowerSpot(towerSpot1);
		
		return scene;
	}

	@Override
	public void onLoadComplete() {		
		
	}
	
	
}