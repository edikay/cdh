package biz.lazysoft.cdh;

import java.util.ArrayList;

import org.anddev.andengine.entity.sprite.AnimatedSprite;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.input.touch.TouchEvent;
import org.anddev.andengine.util.Debug;

import android.graphics.PointF;
import biz.lazysoft.cdh.andengine.AssetPool;

public class Menu extends Sprite {

	static Menu topMenu;
	MenuListener menuListner;
	ArrayList<MenuItem> menuItems;

	public Menu(MenuListener tMenuListener, Names tName) {
		super(0, 0, AssetPool.getInstance().getTR(tName));
		this.setVisible(false);
		CdhActivity.lm.addObject(this);
		menuListner = tMenuListener;
		menuItems = new ArrayList<MenuItem>();
	}

	public void work(int index) {
		Debug.d("TowerSpot work " + this.toString());
		menuListner.action(index);
	}

	public void showMenu() {
		
		if(topMenu!=null)
		{
			topMenu.hideMenu();			
		}
		
		PointF point = menuListner.getPosition();
		setPosition(point.x, point.y);
		this.setVisible(true);
		int status[] = menuListner.getItemsStatus();
		for (int i = 0; i < status.length; i++) {
			menuItems.get(i).setStatus(status[i]);
		}
		
		topMenu=this;
	}

	public void hideMenu() {
		setVisible(false);
		menuListner.close();
	}

	public void switchMenu() {

	}

	public void addMenuItem(float tX, float tY, int tIndex, Names tName) {
		MenuItem menuItem = new MenuItem(tIndex, this, tName);
		menuItem.setPosition(tX, tY);
		menuItems.add(menuItem);
		this.attachChild(menuItem);
	}

	private class MenuItem extends AnimatedSprite {

		int index;
		int status; // 0=wybrane 1=dostepne 2=zablokowane
		Menu menu;

		public MenuItem(int tIndex, Menu tMenu, Names tName) {
			super(0, 0, AssetPool.getInstance().getTTR(tName));
			index = tIndex;
			menu = tMenu;
			CdhActivity.lm.addButton(this);
			
		}

		@Override
		public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
				float pTouchAreaLocalX, float pTouchAreaLocalY) {			
			if (status != 2 && menu.isVisible()) {
				menu.work(index);

			}
			return super.onAreaTouched(pSceneTouchEvent, pTouchAreaLocalX,
					pTouchAreaLocalY);
		}

		public void setStatus(int tStatus) {
			status = tStatus;
			setCurrentTileIndex(status);
		}
	}
}
