package biz.lazysoft.cdh;

import java.util.ArrayList;

import monsters.Monster;

import org.anddev.andengine.entity.scene.Scene;

import towers.Tower;

public class LevelManager {

	Scene scene;
	
	ArrayList<Monster> monsters = new ArrayList<Monster>();
	ArrayList<Tower> towers = new ArrayList<Tower>();
	
	
	LevelManager(Scene tScene)
	{
		scene=tScene;
	}
	
	//Monster
	
	public void addMonster(Monster tMonster)
	{
		scene.attachChild(tMonster);			
		monsters.add(tMonster);
	}
	
	public ArrayList<Monster> getMonsters()
	{
		return monsters;
	}
	
	public void removeMonster(Monster tMonster)
	{
		scene.detachChild(tMonster);
		monsters.remove(tMonster);
	}
	
	//Tower
	
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