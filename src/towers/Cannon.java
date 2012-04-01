package towers;

import biz.lazysoft.cdh.Colors;
import biz.lazysoft.cdh.Names;



public class Cannon extends Tower{

	public Cannon() {
		super(Names.cannon,Colors.red);
		rate[0] = 2000;
		rate[1] = 400;
		rate[2] = 300;
		
		damage[0] = 100;
		damage[1] = 100;
		damage[2] = 100;
		
		range[0] = 200;
		range[1] = 200;
		range[2] = 200;
		
		cost[0] = 10;
		cost[1] = 20;
		cost[2] = 30;
		
	}
	

}
