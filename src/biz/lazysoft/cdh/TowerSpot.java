package biz.lazysoft.cdh;

import org.anddev.andengine.entity.sprite.AnimatedSprite;
import org.anddev.andengine.input.touch.TouchEvent;
import org.anddev.andengine.opengl.texture.region.TiledTextureRegion;
import org.anddev.andengine.util.Debug;

import andengine.AssetPool;

public class TowerSpot extends AnimatedSprite{

	TowerSpotMenu towerSpotMenu;
	public TowerSpot() {
		super(0, 0,90,90,AssetPool.getInstance().getTTR(Names.towerspot));	
		towerSpotMenu = new TowerSpotMenu(this);
		//CdhActivity.scene.attachChild(towerSpotMenu);
	}
	
	@Override
	public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
			float pTouchAreaLocalX, float pTouchAreaLocalY) {
		Debug.d("Click tower spot");
		return super
				.onAreaTouched(pSceneTouchEvent, pTouchAreaLocalX, pTouchAreaLocalY);
	}

}
