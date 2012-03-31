package biz.lazysoft.cdh;

import monsters.Spider;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.engine.camera.Camera;
import org.anddev.andengine.engine.options.EngineOptions;
import org.anddev.andengine.engine.options.EngineOptions.ScreenOrientation;
import org.anddev.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.input.touch.TouchEvent;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.anddev.andengine.opengl.texture.region.TextureRegion;



import android.os.Bundle;

public class CdhActivity extends  BaseGame{
   
	private static final int CAMERA_WIDTH = 1200;
	private static final int CAMERA_HEIGHT = 720;

	private Camera mCamera;

	TextureRegion bmp;
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

		TM.add(Names.monster1,loadTiledTextureRegion("spider.png", 1024, 1024,1,1));
		

	}

	@Override
	public Scene onLoadScene() {

		Scene scene = new Scene();
		Spider spider1 = new Spider();
		scene.attachChild(spider1);
		
		return scene;
	}

	@Override
	public void onLoadComplete() {
		// TODO Auto-generated method stub
		
	}
}