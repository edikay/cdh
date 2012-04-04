package biz.lazysoft.cdh;

import org.anddev.andengine.entity.sprite.Sprite;

public class TowerSpotMenu extends Sprite{
	
	TowerSpot towerSpot;
	
	public TowerSpotMenu(TowerSpot tTowerSpot)
	{
		super(0, 0, 260, 190, TM.getTR(Names.towermenubg));
		towerSpot = tTowerSpot;
	}

}
