package biz.lazysoft.cdh.towers;

import biz.lazysoft.cdh.Colors;
import biz.lazysoft.cdh.Names;
import biz.lazysoft.cdh.TowerSpot;


public class Cannon extends Tower{

	

	
	public Cannon(TowerSpot tTowerSpot) {
		super(Names.cannon,Colors.red,tTowerSpot);
		rate[0] = 1500;
		rate[1] = 1250;
		rate[2] = 1000;
		
		damage[0] = 10;//150;
		damage[1] = 50;//250;
		damage[2] = 350;
		
		range[0] = 250;
		range[1] = 300;
		range[2] = 350;
		
		cost[0] = 120;
		cost[1] = 120;
		cost[2] = 200;
		
	}
	

}
