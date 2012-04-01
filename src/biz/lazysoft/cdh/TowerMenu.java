package biz.lazysoft.cdh;

import org.anddev.andengine.entity.sprite.AnimatedSprite;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.input.touch.TouchEvent;


import towers.Tower;

public class TowerMenu extends Sprite {

	private Tower tower;
	AnimatedSprite colors[] = new AnimatedSprite[3];

	public TowerMenu(Tower tTower) {
		super(0, 0, 260, 190, TM.getTR(Names.towermenubg));
		tower = tTower;

		colors[0] = new AnimatedSprite((0) * 86, 0,
				TM.getTTR(Names.towermenucolors)) {
			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
					float pTouchAreaLocalX, float pTouchAreaLocalY) {
				if (tower.getLevel() >= 0) {
					setTowerLevel(0);
				}
				return super.onAreaTouched(pSceneTouchEvent, pTouchAreaLocalX,
						pTouchAreaLocalY);
			}
		};

		colors[1] = new AnimatedSprite((1) * 86, 0,
				TM.getTTR(Names.towermenucolors)) {
			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
					float pTouchAreaLocalX, float pTouchAreaLocalY) {
				if (tower.getLevel() >= 1) {
					setTowerLevel(1);
				}
				return super.onAreaTouched(pSceneTouchEvent, pTouchAreaLocalX,
						pTouchAreaLocalY);
			}
		};

		colors[2] = new AnimatedSprite((2) * 86, 0,
				TM.getTTR(Names.towermenucolors)) {
			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
					float pTouchAreaLocalX, float pTouchAreaLocalY) {
				if (tower.getLevel() >= 2) {
					setTowerLevel(2);
				}
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
		

		//colors[color].setCurrentTileIndex(color, 0); // ustawienie wybranego
														// koloru przycisku
		switch (color) // ustawienie koloru wiezyczki
		{
		case 0:
			tower.setBulletColor(Colors.red);
			break;
		case 1:
			tower.setBulletColor(Colors.purple);
			break;
		case 2:
			tower.setBulletColor(Colors.blue);
			break;
		}
		refresh();
	}

	void refresh() {
		switch (tower.getLevel()) { // wyzerownie odpowiednio do levelu
		case 0:
			colors[0].setCurrentTileIndex(0, 1);
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
		if(tower.getBulletColor()==Colors.red) colors[0].setCurrentTileIndex(0,0);
		else if(tower.getBulletColor()==Colors.purple) colors[1].setCurrentTileIndex(1,0);
		else if(tower.getBulletColor()==Colors.blue) colors[2].setCurrentTileIndex(2,0);
	}

	@Override
	public void setVisible(boolean pVisible) {
		refresh();
		super.setVisible(pVisible);
	}

}
