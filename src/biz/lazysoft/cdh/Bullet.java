package biz.lazysoft.cdh;


import org.anddev.andengine.entity.sprite.AnimatedSprite;

import biz.lazysoft.cdh.andengine.AssetPool;
import biz.lazysoft.cdh.monsters.Monster;


public class Bullet extends AnimatedSprite{

	float damage=60;
	float speed=900;
	Monster target;
	Colors color;
	
	public Bullet(Colors tColor,float tDamage,Monster tTarget) {
		super(0, 0, AssetPool.getInstance().getTTR(Names.bullet));
		damage=tDamage;
		target=tTarget;
		color=tColor;
		refreshColor();
	}
	
	public float getDamage()
	{
		return damage;
	}
	
	public Colors getColor()
	{
		return color;
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
	
	private void refreshColor()
	{
		if(color==Colors.red) this.setCurrentTileIndex(0);
		else if(color==Colors.purple) this.setCurrentTileIndex(1);
		else if(color==Colors.blue) this.setCurrentTileIndex(2);
	}
	
	

}
