package towers;

import biz.lazysoft.cdh.Names;



public class Cannon extends Tower{

	public Cannon() {
		super(Names.cannon);
		rate[0] = 2000;
		rate[1] = 400;
		rate[2] = 300;
		
		damage[0] = 60;
		damage[1] = 20;
		damage[2] = 30;
		
		range[0] = 200;
		range[1] = 200;
		range[2] = 200;
		
		cost[0] = 10;
		cost[1] = 20;
		cost[2] = 30;
		setSpriteRange();
	}
	

}
