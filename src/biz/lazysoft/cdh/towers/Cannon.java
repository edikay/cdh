package biz.lazysoft.cdh.towers;

import biz.lazysoft.cdh.Colors;
import biz.lazysoft.cdh.Names;



public class Cannon extends Tower{

	public Cannon() {
		super(Names.cannon,Colors.red);
		rate[0] = 1500;
		rate[1] = 1250;
		rate[2] = 1000;
		
		damage[0] = 20;//150;
		damage[1] = 25;//250;
		damage[2] = 350;
		
		range[0] = 250;
		range[1] = 300;
		range[2] = 350;
		
		cost[0] = 120;
		cost[1] = 120;
		cost[2] = 200;
		
	}
	

}
