package monsters;

import org.anddev.andengine.entity.sprite.AnimatedSprite;
import org.anddev.andengine.opengl.texture.ITexture;
import org.anddev.andengine.opengl.texture.region.TiledTextureRegion;


import biz.lazysoft.cdh.Colors;
import biz.lazysoft.cdh.Names;
import biz.lazysoft.cdh.TM;



public abstract class Monster extends AnimatedSprite {

	float speed=1;
	float energy=1;
	float power=1;
	boolean hidden=false;
	Colors color;
	
	
	public Monster(Names name,float tSpeed,float tEnergy,float tPower,Colors tColor) {
		super(0, 0, TM.getTTR(name));		
		speed = tSpeed;
		energy = tEnergy;
		power = tPower;		
		color = tColor;
	}
	
	
		
	
}
