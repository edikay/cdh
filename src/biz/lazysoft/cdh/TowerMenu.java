package biz.lazysoft.cdh;

import org.anddev.andengine.entity.sprite.AnimatedSprite;
import org.anddev.andengine.input.touch.TouchEvent;

import towers.Tower;

public class TowerMenu extends AnimatedSprite {

	private Tower tower;
	AnimatedSprite colors[] = new AnimatedSprite[3];

	public TowerMenu(Tower tTower) {
		super(0, 0, 260, 190, TM.getTTR(Names.towermenubg));
		tower = tTower;

		colors[0] = new AnimatedSprite((0) * 86, 0,
				TM.getTTR(Names.towermenucolors)) {
			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
					float pTouchAreaLocalX, float pTouchAreaLocalY) {

				return super.onAreaTouched(pSceneTouchEvent, pTouchAreaLocalX,
						pTouchAreaLocalY);
			}
		};

		colors[1] = new AnimatedSprite((1) * 86, 0,
				TM.getTTR(Names.towermenucolors)) {
			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
					float pTouchAreaLocalX, float pTouchAreaLocalY) {

				return super.onAreaTouched(pSceneTouchEvent, pTouchAreaLocalX,
						pTouchAreaLocalY);
			}
		};

		colors[2] = new AnimatedSprite((2) * 86, 0,
				TM.getTTR(Names.towermenucolors)) {
			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
					float pTouchAreaLocalX, float pTouchAreaLocalY) {

				return super.onAreaTouched(pSceneTouchEvent, pTouchAreaLocalX,
						pTouchAreaLocalY);
			}
		};

		for (AnimatedSprite as : colors) {
			this.attachChild(as);
			CdhActivity.scene.registerTouchArea(as);
		}
	}

	void setTowerLevel(int color) {
		
		switch (tower.getLevel()) { //wyzerownie odpowiednio do levelu
		case 0:
			colors[0].setCurrentTileIndex(0, 0);
			colors[1].setCurrentTileIndex(3, 0);
			colors[2].setCurrentTileIndex(3, 0);			
			break;
		case 1:
			colors[0].setCurrentTileIndex(0, 1);
			colors[1].setCurrentTileIndex(1, 1);
			colors[2].setCurrentTileIndex(3, 0);
			break;
		case 2:
			colors[0].setCurrentTileIndex(0, 1);
			colors[1].setCurrentTileIndex(1, 1);
			colors[2].setCurrentTileIndex(2, 1);			
			break;
		}
		
		colors[color].setCurrentTileIndex(color, 0); //ustawienie wybranego koloru przycisku
		
		switch(color) //ustawienie koloru wiezyczki
		{
		case 0: tower.setColor(Colors.red); break;
		case 1: tower.setColor(Colors.purple); break;
		case 2: tower.setColor(Colors.blue); break;
		}
	}

}
