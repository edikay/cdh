package towers;

import java.util.ArrayList;
import java.util.Date;

import monsters.Monster;

import org.anddev.andengine.entity.sprite.AnimatedSprite;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.util.Debug;

import biz.lazysoft.cdh.Bullet;
import biz.lazysoft.cdh.Names;
import biz.lazysoft.cdh.TM;
import biz.lazysoft.cdh.Track;
import biz.lazysoft.cdh.WayPoint;

public class Tower extends AnimatedSprite {

	float[] rate;
	float[] damage;
	float[] range;
	float[] cost;
	int level = 0;
	long lastFire;
	Sprite spriteRange;
	Monster target=null;

	public Tower(Names name) {
		super(0, 0, 90, 90, TM.getTTR(name));
		rate = new float[3];
		damage = new float[3];
		range = new float[3];
		cost = new float[3];
		spriteRange = new Sprite(0,0,TM.getTR(Names.range));
		this.attachChild(spriteRange);		
		
	}
	
	public float getRotationAngle(Monster monster){
		float x = monster.getX() - getX();
		float y = monster.getY() - getY();
		if(x != 0)
			return (float) Math.toDegrees(Math.atan(y/x)) + 90;
		return 0;
	}
	
	public float getRate()
	{
		return rate[level];
	}

	public float getDamage() {
		return damage[level];
	}

	public float getRange() {
		return range[level];
	}

	public float getCost() {
		return cost[level];
	}

	private boolean isInRange(Monster monster) {
		double xa, xb, ya, yb;
		xa = this.getX();
		ya = this.getY();
		xb = monster.getX();
		yb = monster.getY();
		float distance = (float) Math.sqrt(Math.pow((xa - xb), 2)
				+ Math.pow((ya - yb), 2));
		if (distance-15 <= getRange())
			return true;
		else
			return false;
	}

	public boolean fire(Monster monster) {
		if (isInRange(monster)) {
			if (new Date().getTime() - lastFire > getRate()) {
				lastFire = new Date().getTime();
				WayPoint start = new WayPoint(this.getX()+(this.getWidth()/2),this.getY()+(this.getHeight()/2),0);
				WayPoint end = new WayPoint(monster.getX()+(monster.getWidth()/2),monster.getY()+(monster.getHeight()/2),0);
				Track track = new Track();
				track.setTrack(start,end);
				Bullet bullet = new Bullet(getDamage(),monster);
				this.getParent().attachChild(bullet);
				bullet.move(track);				
				return true;
			}
		}
		return false;
	}
	
	public void checkFire(ArrayList<Monster> monsters)
	{
		if(target!=null && isInRange(target)==true)
		{
			this.setRotation(getRotationAngle (target));
			
			Debug.d("NAMIERZONY    angel="+getRotationAngle (target)+"FIRE: "+fire(target));
		}
		else
		{
			for(Monster m:monsters)
			{
				if(isInRange(m))
				{
					target=m;
					Debug.d("W ZASIEGU MONSTER");
					break;
					
				}
			}
		}
	}
	
	void setSpriteRange()
	{
		spriteRange.setSize(getRange()*2, getRange()*2);
		spriteRange.setPosition((this.getWidth()/2)-(spriteRange.getWidth()/2),(this.getHeight()/2)-(spriteRange.getHeight()/2));
		
	}
	


}
