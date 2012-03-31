package towers;

import java.util.Date;

import monsters.Monster;

import org.anddev.andengine.entity.sprite.AnimatedSprite;
import org.anddev.andengine.util.Debug;

import biz.lazysoft.cdh.Names;
import biz.lazysoft.cdh.TM;

public class Tower extends AnimatedSprite{

	float[] rate;
	float[] damage;
	float[] range;
	float[] cost;
	int level=0;
	long lastFire;
	
	public Tower(Names name) {
		super(0, 0, 90, 90, TM.getTTR(name));
		rate = new float[3];
		damage = new float[3];
		range = new float[3];
		cost = new float[3];
		lastFire = new Date().getTime();
		Debug.d("Current date= "+lastFire);
	}
	
	public float getRotationAngle(Monster monster){
		float x = Math.abs(monster.getX() - getX());
		float y = Math.abs(monster.getY() - getY());
		if(x != 0)
			return (float) Math.atan(y/x);
		return 0;
	}
	
	public float getRate()
	{
		return rate[level];
	}
	
	public float getDamage()
	{
		return damage[level];
	}
	
	public float getRange()
	{
		return range[level];
	}
	
	public float getCost()
	{
		return cost[level];
	}
	
	public boolean isInRange(Monster monster)
	{
		double xa,xb,ya,yb;
		xa=this.getX();
		ya=this.getY();
		xb=monster.getX();
		yb=monster.getY();
		float distance =(float) Math.sqrt( Math.pow((xa-xb),2) + Math.pow((ya-yb),2) );
		if(distance<=getRange()) return true;
		else return false;		
	}
	
	public boolean fire()
	{		
		if(new Date().getTime()-lastFire>getRate())
		{
		lastFire = new Date().getTime();
		return true;
		}
		return false;
	}
	

}
