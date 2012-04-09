package biz.lazysoft.cdh;

import java.util.ArrayList;

import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.util.Debug;

import android.graphics.PointF;
import biz.lazysoft.cdh.andengine.AssetPool;

public class Menu extends Sprite {

	MenuListener menuListner;
	ArrayList<MenuItem> menuItems;

	public Menu(MenuListener tMenuListener,Names tName) {
		super(0, 0, AssetPool.getInstance().getTR(tName));
		this.setVisible(false);
		CdhActivity.lm.addObject(this);
		menuListner = tMenuListener;
		menuItems = new ArrayList<MenuItem>();
	}

	public void work(int index) {
		menuListner.action(index);
	}
	
	public void showMenu()
	{
		PointF point = menuListner.getPosition();
		setPosition(point.x,point.y);
		this.setVisible(true);
		int status[] = menuListner.getItemsStatus();
		for(int i=0;i<status.length;i++)
		{
			menuItems.get(i).setStatus(status[i]);
		}
	}
	
	public void hideMenu()
	{
		setVisible(false);
	}
	
	public void switchMenu()
	{
		
	}
	
	public void addMenuItem(float tX,float tY,int tIndex,Names tName)
	{
		MenuItem menuItem = new MenuItem(tIndex, this, tName);		
		menuItem.setPosition(tX, tY);
		menuItems.add(menuItem);
		this.attachChild(menuItem);		
	}
}
