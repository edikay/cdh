package biz.lazysoft.cdh;

import org.anddev.andengine.entity.sprite.AnimatedSprite;
import org.anddev.andengine.input.touch.TouchEvent;
import org.anddev.andengine.util.Debug;

import android.graphics.PointF;
import biz.lazysoft.cdh.andengine.AssetPool;
import biz.lazysoft.cdh.towers.Cannon;
import biz.lazysoft.cdh.towers.Tower;

public class TowerSpot extends ObjectGame implements MenuListener {

	Menu menu;

	public TowerSpot() {
		super(0, 0, 90, 90, AssetPool.getInstance().getTTR(Names.towerspot));
		setZIndex(50);
		CdhActivity.lm.addButton(this);
		menu = new Menu(this, Names.towerspotmenu);
		//menu.addMenuItem(0, 0, 0, Names.closeicon);
		menu.addMenuItem(170, 0, 1, Names.cannonicon);
		
	}

	public void hide() {		
		this.setVisible(false);
		
	}

	public void show() {		
		this.setVisible(true);
	}

	
	public boolean onTouched(TouchEvent pSceneTouchEvent,
			float pTouchAreaLocalX, float pTouchAreaLocalY) {
		Debug.d("Click on TOWERSPOT");		
		if(this.isVisible())
		{
			menu.showMenu();
		}
		return true;
	}

	@Override
	public void action(int index) {
		Debug.d("TowerSpot wybrano " + index);
		Tower tower = null;
		switch (index) {
		case 0:
			menu.hideMenu();
			break;
		case 1:
			tower = new Cannon(this);
			break;
		}
		menu.hideMenu();
		if (tower != null) {
			tower.setPosition(this);
			CdhActivity.lm.addTower(tower);
			hide();
		}
	}

	@Override
	public int[] getItemsStatus() {
		int[] items = new int[2];
		items[0] = 1;
		items[1] = 1;
		return items;
	}

	@Override
	public PointF getPosition() {
		// TODO Auto-generated method stub
		return new PointF(0, 0);
	}

	@Override
	public void close() {
		
		
	}

}
