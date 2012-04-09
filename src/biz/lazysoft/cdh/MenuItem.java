package biz.lazysoft.cdh;

import org.anddev.andengine.entity.sprite.AnimatedSprite;
import org.anddev.andengine.input.touch.TouchEvent;

import biz.lazysoft.cdh.andengine.AssetPool;

public class MenuItem extends AnimatedSprite{

	int index;
	int status; // 0=wybrane 1=dostepne 2=zablokowane
	Menu menu;
	
	public MenuItem(int tIndex,Menu tMenu,Names tName)
	{
		super(0, 0,AssetPool.getInstance().getTTR(tName));
		index=tIndex;
		menu = tMenu;
		CdhActivity.lm.addButton(this);
	}
	
	@Override
	public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
			float pTouchAreaLocalX, float pTouchAreaLocalY) {
		if(status!=2)
		{
			menu.work(index);
			
		}
		return super
				.onAreaTouched(pSceneTouchEvent, pTouchAreaLocalX, pTouchAreaLocalY);
	}
	
	public void setStatus(int tStatus)
	{
		status=tStatus;
		setCurrentTileIndex(status);
	}
}
