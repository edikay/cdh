package biz.lazysoft.cdh;

import monsters.Spider;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.engine.camera.Camera;
import org.anddev.andengine.engine.options.EngineOptions;
import org.anddev.andengine.engine.options.EngineOptions.ScreenOrientation;
import org.anddev.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.sprite.AnimatedSprite;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.input.touch.TouchEvent;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.anddev.andengine.util.Debug;

import towers.Cannon;

public class CdhActivity extends  BaseGame{
   
	private static final int CAMERA_WIDTH = 1200;
	private static final int CAMERA_HEIGHT = 720;

	private Camera mCamera;
	Spider spider1;
	Spider spider2;
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

		TM.add(Names.spider,loadTiledTextureRegion("spider.png", 1024, 1024,1,1));
		TM.add(Names.map0,loadTextureRegion("map0.png", 2048, 1024));
		TM.add(Names.cannon, loadTiledTextureRegion("cannon.png", 1024, 1024, 1,1));

	}

	@Override
	public Scene onLoadScene() {

		Scene scene = new Scene();
		Sprite background = new Sprite(0, 0, TM.getTR(Names.map0));
		scene.attachChild(background);
		spider1 = new Spider();
		spider2 = new Spider();
		cannon1 = new Cannon();
		final Track track = new Track();
		track.setTrack(new WayPoint(0, 200, 0),new WayPoint(800, 200, 180),new WayPoint(800, 700, 90));
		
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
		scene.attachChild(spider1);
		scene.attachChild(spider2);
		scene.attachChild(cannon1);
		cannon1.setPosition(50, 360);
		cannon1.setRotation(90);
		scene.registerTouchArea(bt1);
		//spider1.move(track);	
		spider1.setPosition(250, 360);
		boolean b = cannon1.isInRange(spider1);
		Debug.d("Is in range: "+b);
		
		return scene;
	}

	@Override
	public void onLoadComplete() {
		// TODO Auto-generated method stub
		
	}
}