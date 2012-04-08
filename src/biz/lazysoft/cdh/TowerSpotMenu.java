package biz.lazysoft.cdh;

import org.anddev.andengine.entity.sprite.AnimatedSprite;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.input.touch.TouchEvent;
import org.anddev.andengine.util.Debug;

import biz.lazysoft.cdh.andengine.AssetPool;


public class TowerSpotMenu extends Sprite{
	
	TowerSpot towerSpot;
	Sprite towers[] = new Sprite[2];
	
	public TowerSpotMenu(TowerSpot tTowerSpot)
	{
		super(0, 0, 200, 720, AssetPool.getInstance().getTR(Names.towerspotmenu));
		towerSpot = tTowerSpot;
		createButtons();
	}
	
	private void createButtons()
	{
		towers[0] =new Sprite(0, 0, AssetPool.getInstance().getTR(Names.cannonicon))
		{
			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
					float pTouchAreaLocalX, float pTouchAreaLocalY) {
				Debug.d("Wybrano tower 1");
				return super
						.onAreaTouched(pSceneTouchEvent, pTouchAreaLocalX, pTouchAreaLocalY);
			}
		};		
	
		for (Sprite as : towers) {
			if(as!=null)
			{
			this.attachChild(as);
			CdhActivity.lm.addButton(as);
			}
		}
	}
	
	

}
