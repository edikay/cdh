package biz.lazysoft.cdh;

import org.anddev.andengine.entity.sprite.AnimatedSprite;
import org.anddev.andengine.input.touch.TouchEvent;

import towers.Tower;

public class TowerMenu extends AnimatedSprite {

	private Tower tower;
	AnimatedSprite colors[] = new AnimatedSprite[3];
	
	public TowerMenu(Tower tTower) {
		super(0, 0,260,190,TM.getTTR(Names.towermenubg));
		tower=tTower;
		
		colors[0]=new AnimatedSprite((0)*86,0,TM.getTTR(Names.towermenucolors))
		{
			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
					float pTouchAreaLocalX, float pTouchAreaLocalY) {
				
				return super
						.onAreaTouched(pSceneTouchEvent, pTouchAreaLocalX, pTouchAreaLocalY);
			}
		};
		
		colors[1]=new AnimatedSprite((1)*86,0,TM.getTTR(Names.towermenucolors))
		{
			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
					float pTouchAreaLocalX, float pTouchAreaLocalY) {
				
				return super
						.onAreaTouched(pSceneTouchEvent, pTouchAreaLocalX, pTouchAreaLocalY);
			}
		};
		
		colors[2]=new AnimatedSprite((2)*86,0,TM.getTTR(Names.towermenucolors))
		{
			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
					float pTouchAreaLocalX, float pTouchAreaLocalY) {
				
				return super
						.onAreaTouched(pSceneTouchEvent, pTouchAreaLocalX, pTouchAreaLocalY);
			}
		};
	}
	
	

}
