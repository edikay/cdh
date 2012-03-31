package biz.lazysoft.cdh;

import monsters.Monster;

import org.anddev.andengine.entity.sprite.AnimatedSprite;

public class Bullet extends AnimatedSprite{

	float damage=60;
	float speed=900;
	Monster target;
	
	public Bullet(float tDamage,Monster tTarget) {
		super(0, 0, TM.getTTR(Names.bullet));
		damage=tDamage;
		target=tTarget;
	}
	
	public float getDamage()
	{
		return damage;
	}
	
	public float getSpeed()
	{
		return speed;
	}
	
	public void hit()
	{
		target.hit(damage);
	}
	
	public void move(Track tTrack) {
		float length = tTrack.path.getLength();		
		if (length != 0)
			this.registerEntityModifier(tTrack.getPathModiferBullet(this));

	}
	

}
