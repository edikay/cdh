package biz.lazysoft.cdh;

import java.util.ArrayList;
import java.util.Collections;

import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.input.touch.TouchEvent;
import org.anddev.andengine.util.Debug;

import biz.lazysoft.cdh.andengine.AssetPool;

public class TouchManager {

	ArrayList<ObjectGame> touchObjects = new ArrayList<ObjectGame>();
	Scene scene;
	Sprite touchCatcher;
	
	public TouchManager(Scene tScene)
	{
		this.scene=tScene;
		touchCatcher = new Sprite(0, 0,1200,720,AssetPool.getInstance().getTR(Names.touchCatcher))
		{
			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
					float pTouchAreaLocalX, float pTouchAreaLocalY) {
				Click(pSceneTouchEvent,pTouchAreaLocalX,pTouchAreaLocalY);
				return super
						.onAreaTouched(pSceneTouchEvent, pTouchAreaLocalX, pTouchAreaLocalY);
			}
		};
		touchCatcher.setAlpha(0);
		scene.attachChild(touchCatcher);
		scene.registerTouchArea(touchCatcher);
	}
	
	public void add(ObjectGame obj)
	{
		if(touchObjects.indexOf(obj)==-1)
		{
			touchObjects.add(obj);	
			Collections.sort(touchObjects);
		}
		Debug.d("TouchManager size="+touchObjects);
	}
	
	public void remove(ObjectGame obj)
	{
		touchObjects.remove(obj);
	}
	
	public void Click(TouchEvent pSceneTouchEvent,
			float pTouchAreaLocalX, float pTouchAreaLocalY)
	{
		Debug.d("Click Click Click Clcik");
		for(ObjectGame obj : touchObjects)
		{
			if(obj.onAreaTouched(pSceneTouchEvent, pTouchAreaLocalX, pTouchAreaLocalY)==true)break;
		}
	}
}
