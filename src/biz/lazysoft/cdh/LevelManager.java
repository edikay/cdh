package biz.lazysoft.cdh;

import java.util.ArrayList;

import org.anddev.andengine.entity.IEntity;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.scene.Scene.ITouchArea;
import org.anddev.andengine.util.Debug;

import android.graphics.PointF;
import biz.lazysoft.cdh.andengine.BaseGame;
import biz.lazysoft.cdh.monsters.Monster;
import biz.lazysoft.cdh.towers.Tower;

public class LevelManager {

	Level game;
	Scene scene;
	TouchManager touchManager;
	ArrayList<Monster> monsters = new ArrayList<Monster>();
	ArrayList<Tower> towers = new ArrayList<Tower>();
	ArrayList<TowerSpot> towerSpots = new ArrayList<TowerSpot>();
	ArrayList<ObjectGame> objects = new ArrayList<ObjectGame>();

	LevelManager(Scene tScene,Level game) {
		scene = tScene;
		this.game=game;
		touchManager = new TouchManager(tScene);
	}

	// Monster

	public void addMonster(Monster tMonster) {

		scene.attachChild(tMonster);
		monsters.add(tMonster);
		sort();
	}

	public ArrayList<Monster> getMonsters() {
		return monsters;
	}

	public void removeMonster(Monster tMonster) {
		Debug.d("Remove monster " + tMonster);
		monsters.remove(tMonster);
	}

	// Tower

	public void addTower(Tower tTower) {
		scene.attachChild(tTower);
		touchManager.add(tTower);
		towers.add(tTower);
	}

	public ArrayList<Tower> getTowers() {
		return towers;
	}

	public void removeTower(Tower tTower) {
		
		game.removeEntity(tTower);
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

	public void addButton(ObjectGame obj) {
		touchManager.add(obj);
	}

	public void removeButton(ITouchArea tButton) {
		scene.unregisterTouchArea(tButton);
		scene.sortChildren();
	}

	// Objects

	public void addObject(IEntity tEntity) {
		scene.attachChild(tEntity);
		sort();
	}

	public void removeObject(IEntity tEntity) {
		scene.detachChild(tEntity);
	}

	// set on map

	public void addObject(Objects obj, String pos) {
		IEntity entity = null;
		if (obj == Objects.TowerSpot) {
			TowerSpot tmp = new TowerSpot();
			addTowerSpot(tmp);
			entity = tmp;
		}

		if (entity != null) {
			setEntity(entity, pos);
		}
	}

	private void setEntity(IEntity tEntity, String tPosition) {
		float px, py;
		PointF point = getPonint(tPosition);
		px = point.x;
		py = point.y;
		tEntity.setPosition(px, py);

	}

	private PointF getPonint(String tPosition) {
		String position = tPosition.toUpperCase();
		float px = 15;
		float py = 0;

		int column = 0;
		int line = 0;

		String partX = "";
		String partY = "";
		partX = position.substring(0, 1);
		partY = position.substring(1, 2);

		try {

			line = Integer.parseInt(partY) - 1;
			column = partX.charAt(0) - 65;

			px += column * 90;
			py += line * 90;
		} catch (Exception e) {
			Debug.d("Blad wspolrzednych");
		}

		PointF point = new PointF(px, py);
		return point;
	}

	// sort

	public void sort() {
		scene.sortChildren();
	}

}
