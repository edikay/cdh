package biz.lazysoft.cdh;

import java.util.ArrayList;

import org.anddev.andengine.entity.IEntity;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.scene.Scene.ITouchArea;
import org.anddev.andengine.util.Debug;

import biz.lazysoft.cdh.monsters.Monster;
import biz.lazysoft.cdh.towers.Tower;

public class LevelManager {

	Scene scene;

	ArrayList<Monster> monsters = new ArrayList<Monster>();
	ArrayList<Tower> towers = new ArrayList<Tower>();
	ArrayList<TowerSpot> towerSpots = new ArrayList<TowerSpot>();

	LevelManager(Scene tScene) {
		scene = tScene;
	}

	// Monster

	public void addMonster(Monster tMonster) {
		scene.attachChild(tMonster);
		monsters.add(tMonster);
	}

	public ArrayList<Monster> getMonsters() {
		return monsters;
	}

	public void removeMonster(Monster tMonster) {
		Debug.d("Remove monster "+tMonster);
		scene.detachChild(tMonster);
		monsters.remove(tMonster);
	}

	// Tower

	public void addTower(Tower tTower) {
		scene.attachChild(tTower);
		scene.registerTouchArea(tTower);
		towers.add(tTower);
	}

	public ArrayList<Tower> getTowers() {
		return towers;
	}

	public void removeTower(Tower tTower) {
		scene.detachChild(tTower);
		towers.remove(tTower);
	}

	// Tower spot

	public void addTowerSpot(TowerSpot tTowerSpot) {
		scene.attachChild(tTowerSpot);
		scene.registerTouchArea(tTowerSpot);
		towerSpots.add(tTowerSpot);
	}

	public ArrayList<TowerSpot> getTowerSpots() {
		return towerSpots;
	}

	public void removeTowerSpot(TowerSpot tTowerSpot) {
		scene.detachChild(tTowerSpot);
		scene.unregisterTouchArea(tTowerSpot);
		towerSpots.remove(tTowerSpot);
	}

	// Buttons

	public void addButton(ITouchArea tButton) {
		scene.registerTouchArea(tButton);		
	}
	
	public void removeButton(ITouchArea tButton) {
		scene.unregisterTouchArea(tButton);
	}
	
	// Objects
	
	public void addObject(IEntity tEntity)
	{
		scene.attachChild(tEntity);
	}
	
	public void removeObject(IEntity tEntity)
	{
		scene.detachChild(tEntity);
	}
	
	
}
