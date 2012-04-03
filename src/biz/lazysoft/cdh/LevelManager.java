package biz.lazysoft.cdh;

import java.util.ArrayList;

import org.anddev.andengine.entity.IEntity;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.scene.Scene.ITouchArea;

import towers.Tower;

public class LevelManager {

	Scene scene;
	
	ArrayList<Tower> towers = new ArrayList<Tower>();
	
	LevelManager(Scene tScene)
	{
		scene=tScene;
	}
	
	public void addTower(Tower tTower)
	{
		scene.attachChild(tTower);
		scene.registerTouchArea(tTower);	
		towers.add(tTower);
	}
	
	public ArrayList<Tower> getTowers()
	{
		return towers;
	}
	
	public void removeTower(Tower tTower)
	{
		scene.detachChild(tTower);
		towers.remove(tTower);
	}
}
