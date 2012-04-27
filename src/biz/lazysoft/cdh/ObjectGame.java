package biz.lazysoft.cdh;

import org.anddev.andengine.entity.sprite.AnimatedSprite;
import org.anddev.andengine.input.touch.TouchEvent;
import org.anddev.andengine.opengl.texture.region.TiledTextureRegion;
import org.anddev.andengine.util.Debug;

import biz.lazysoft.cdh.andengine.AssetPool;

public abstract class ObjectGame extends AnimatedSprite implements Comparable<ObjectGame> {

	public ObjectGame(float x, float y, float w, float h, TiledTextureRegion ttr) {
		super(x, y, w, h, ttr);
	}
	
	public ObjectGame(float x, float y, TiledTextureRegion ttr) {
		super(x, y, ttr);
	}

	@Override
	public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
			float pTouchAreaLocalX, float pTouchAreaLocalY) {
		Debug.d("Click w onAreaTocuhed przed if");
		if (this.contains(pTouchAreaLocalX, pTouchAreaLocalY) && this.isVisible()) {
			Debug.d("Click wywoluje onTouched()");
			return onTouched(pSceneTouchEvent, pTouchAreaLocalX,
					pTouchAreaLocalY);
		} else
			return false;
	}

	public boolean onTouched(TouchEvent pSceneTouchEvent,
			float pTouchAreaLocalX, float pTouchAreaLocalY) {
		return false;
	}

	@Override
	public int compareTo(ObjectGame another) {
		
		if(this.getZIndex()>another.getZIndex())return -1;
		if(this.getZIndex()==another.getZIndex())return 0;
		if(this.getZIndex()<another.getZIndex())return 1;
		return 0;		
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString()+" zIndex="+getZIndex();
	}

}
