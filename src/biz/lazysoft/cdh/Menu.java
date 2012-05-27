package biz.lazysoft.cdh;

import java.util.ArrayList;
import java.util.Collections;

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
		setZIndex(310);
		this.setVisible(false);
		Level.lm.addObject(this);
		menuListner = tMenuListener;
		menuItems = new ArrayList<MenuItem>();
		setCloseMenuItem();		
	}

	private void setCloseMenuItem()
	{
		MenuItem closeItem = new MenuItem(0, this, Names.touchCatcher);
		closeItem.setPosition(-1200, -720);
		closeItem.setWidth(2400);
		closeItem.setHeight(1440);
		closeItem.setZIndex(300);
		menuItems.add(closeItem);
		this.attachChild(closeItem);
	}
	
	public void work(int index) {	
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
		for(MenuItem item : menuItems)
		{
			item.setVisible(true);
		}
		topMenu=this;
	}

	public void hideMenu() {
		setVisible(false);
		menuListner.close();
		for(MenuItem item : menuItems)
		{
			item.setVisible(false);
		}
	}

	public void switchMenu() {

	}
	
	public void remove(){
		for(MenuItem item : menuItems)
		{
			Level.lm.removeButton(item);
		}
	}

	public void addMenuItem(float tX, float tY, int tIndex, Names tName) {
		MenuItem menuItem = new MenuItem(tIndex, this, tName);
		menuItem.setPosition(tX, tY);
		menuItems.add(menuItem);
		Collections.sort(menuItems);
		this.attachChild(menuItem);
	}
	
	public static void closeTopMenu()
	{
		if(topMenu!=null)
		{
			topMenu.hideMenu();
		}
	}
	

	private class MenuItem extends ObjectGame {

		int index;
		int status; // 0=wybrane 1=dostepne 2=zablokowane 3=closemenu
		Menu menu;

		public MenuItem(int tIndex, Menu tMenu, Names tName) {
			super(0, 0, AssetPool.getInstance().getTTR(tName));
			setZIndex(320);
			index = tIndex;
			menu = tMenu;
			Level.lm.addButton(this);	
			
			setVisible(false);
			
		}

		@Override
		public boolean onTouched(TouchEvent pSceneTouchEvent,
				float pTouchAreaLocalX, float pTouchAreaLocalY) {			
			if (status < 2 && menu.isVisible()) {
				menu.work(index);

			}else if(status==3 && menu.isVisible())
			{
				menu.hideMenu();
			}
			return true;
		}

		public void setStatus(int tStatus) {
			status = tStatus;
			setCurrentTileIndex(status);
		}
		
		@Override
		protected void finalize() throws Throwable {
			Level.lm.removeButton(this);
			super.finalize();
		}
	}
}
