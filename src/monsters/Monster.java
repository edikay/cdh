package monsters;

import org.anddev.andengine.entity.sprite.AnimatedSprite;

import biz.lazysoft.cdh.Colors;
import biz.lazysoft.cdh.Names;
import biz.lazysoft.cdh.TM;
import biz.lazysoft.cdh.Track;



public abstract class Monster extends AnimatedSprite {

	private float speed=1;
	private float energy=1;
	private float power=1;	
	Colors color;
	
	
	public Monster(Names name,float tSpeed,float tEnergy,float tPower,Colors tColor) {
		super(0, 0, TM.getTTR(name));		
		speed = tSpeed;
		energy = tEnergy;
		power = tPower;		
		color = tColor;
	}
	
	public void move(Track tTrack) {
		float length = tTrack.path.getLength();
		if (length != 0)
			this.registerEntityModifier(tTrack.getPathModiferMonster(this));

	}
	
	public float getSpeed()
	{
		return speed;
	}
	
	public float getEnergy()
	{
		return energy;
	}
	
	public float getPower()
	{
		return power;
	}
	
	public void hit(float damage)
	{
		energy-=damage;
		if(energy<=0)this.setVisible(false);
	}
		
	
}
