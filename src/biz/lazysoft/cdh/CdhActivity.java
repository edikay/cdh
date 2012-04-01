package biz.lazysoft.cdh;

import java.util.ArrayList;

import monsters.Monster;
import monsters.Octopus;
import monsters.Spider;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.engine.camera.Camera;
import org.anddev.andengine.engine.handler.timer.ITimerCallback;
import org.anddev.andengine.engine.handler.timer.TimerHandler;
import org.anddev.andengine.engine.options.EngineOptions;
import org.anddev.andengine.engine.options.EngineOptions.ScreenOrientation;
import org.anddev.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.sprite.AnimatedSprite;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.input.touch.TouchEvent;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;

import towers.Cannon;

public class CdhActivity extends  BaseGame{
   
	private static final int CAMERA_WIDTH = 1200;
	private static final int CAMERA_HEIGHT = 720;

	private Camera mCamera;
	ArrayList<Monster> monsters;	
	Cannon cannon1;
	

	
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
		TM.add(Names.bullet, loadTiledTextureRegion("bullet.png", 1024, 1024, 1,1));
		TM.add(Names.range,loadTextureRegion("range.png", 2048, 1024));

	}

	@Override
	public Scene onLoadScene() {

		monsters = new ArrayList<Monster>();
		Scene scene = new Scene();
		Sprite background = new Sprite(0, 0, TM.getTR(Names.map0));
		scene.attachChild(background);
		Octopus spider1 = new Octopus();
		final Spider spider2 = new Spider();
		monsters.add(spider1);
		monsters.add(spider2);
		scene.attachChild(spider1);
		scene.attachChild(spider2);
		cannon1 = new Cannon();
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
		
		scene.attachChild(cannon1);
		cannon1.setPosition(50, 360);
		cannon1.setRotation(0);
		scene.registerTouchArea(bt1);
		spider1.move(track);	
		spider1.setPosition(0, 0);		
		
		TimerHandler timer = new TimerHandler(0.1f, true,
				new ITimerCallback() {

					@Override
					public void onTimePassed(TimerHandler pTimerHandler) {
						
						
						
						cannon1.checkFire(monsters);
						

					}
				});
		scene.registerUpdateHandler(timer);
		
		//Sprite range = new Sprite(500, 500, 90, 90, TM.getTR(Names.range));
		//scene.attachChild(range);
		
		
		return scene;
	}

	@Override
	public void onLoadComplete() {
		// TODO Auto-generated method stub
		
	}
}