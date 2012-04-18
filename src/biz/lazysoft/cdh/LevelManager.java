package biz.lazysoft.cdh;

import java.util.ArrayList;

import org.anddev.andengine.entity.IEntity;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.scene.Scene.ITouchArea;
import org.anddev.andengine.util.Debug;

import android.graphics.PointF;
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
		//scene.detachChild(tMonster);
		monsters.remove(tMonster);
	}

	// Tower

	public void addTower(Tower tTower) {
		scene.getChild(0).attachChild(tTower);
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
	
	//set on map
	
	public void addObject(Objects obj,String pos)
	{
		IEntity entity=null;
		if(obj==Objects.TowerSpot)
		{
			TowerSpot tmp = new TowerSpot();
			addTowerSpot(tmp);
			entity=tmp;
		}
		
		if(entity!=null)
		{
			setEntity(entity, pos);
		}
	}
	
	private void setEntity(IEntity tEntity,String tPosition){
		float px,py;
		PointF point = getPonint(tPosition);
		px=point.x;
		py=point.y;
		tEntity.setPosition(px, py);
		
	}
	
	private PointF getPonint(String tPosition)
	{
		String position=tPosition.toUpperCase();
		float px=15;
		float py=0;
		
		int column=0;
		int line=0;
		
		String partX="";
		String partY="";
		partX=position.substring(0, 1);
		partY=position.substring(1, 2);
		
		try{
			
		
		line=Integer.parseInt(partY)-1;
		column=partX.charAt(0)-65;
		
		px+=column*90;
		py+=line*90;
		}catch(Exception e)
		{
			Debug.d("Blad wspolrzednych");
		}
			
		PointF point = new PointF(px, py);
		return point;
	}
	
}
