package biz.lazysoft.cdh.towers;

import java.util.ArrayList;
import java.util.Date;

import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.input.touch.TouchEvent;
import org.anddev.andengine.util.Debug;

import android.graphics.PointF;
import biz.lazysoft.cdh.Bullet;
import biz.lazysoft.cdh.CdhActivity;
import biz.lazysoft.cdh.Colors;
import biz.lazysoft.cdh.Menu;
import biz.lazysoft.cdh.MenuListener;
import biz.lazysoft.cdh.Names;
import biz.lazysoft.cdh.ObjectGame;
import biz.lazysoft.cdh.TowerSpot;
import biz.lazysoft.cdh.Track;
import biz.lazysoft.cdh.WayPoint;
import biz.lazysoft.cdh.andengine.AssetPool;
import biz.lazysoft.cdh.monsters.Monster;

public abstract class Tower extends ObjectGame implements MenuListener {

	float[] rate;
	float[] damage;
	float[] range;
	float[] cost;
	private int level = 1;
	Colors color;

	private Colors bulletColor = Colors.red;

	private Monster target = null;
	private long lastFire;

	private Sprite spriteRange;
	
	private TowerSpot towerSpot;
	
	Menu menu;

	public Tower(Names name, Colors tColor,TowerSpot tTowerSpot) {
		super(0, 0, 90, 90, AssetPool.getInstance().getTTR(name));
		setZIndex(60);
		rate = new float[3];
		damage = new float[3];
		range = new float[3];
		cost = new float[3];
		color = tColor;
		
		towerSpot = tTowerSpot;
		setRange();

		menu = new Menu(this, Names.towermenubg);
		menu.addMenuItem(0, 0, 1, Names.el1);
		menu.addMenuItem(80, 0, 2, Names.el2);
		menu.addMenuItem(160, 0, 3, Names.el3);
		

	}

	public void setRange() {
		spriteRange = new Sprite(this.getX(), this.getY(), AssetPool
				.getInstance().getTR(Names.range));
		spriteRange.setVisible(false);
		CdhActivity.lm.addObject(spriteRange);
	}

	private void showRange() {
		spriteRange.setSize(getRange() * 2, getRange() * 2);
		spriteRange.setPosition(
				(this.getX() + (this.getWidth() / 2))
						- (spriteRange.getWidth() / 2),
				(this.getY() + (this.getHeight() / 2))
						- (spriteRange.getHeight() / 2));
		spriteRange.setVisible(true);
	}

	public float getRate() {
		return rate[level];
	}

	public float getDamage() {

		if (color == bulletColor) {
			Debug.d("Damage = " + damage[level] * (1 + (getLevel() + 1) * 0.1f));
			return damage[level] * (1 + (getLevel() + 1) * 0.1f);

		} else {
			Debug.d("Damage = " + damage[level]);
			return damage[level];
		}
	}

	public float getRange() {
		return range[level];
	}

	public float getCost() {
		return cost[level];
	}

	public Colors getBulletColor() {
		return bulletColor;
	}

	public void setBulletColor(Colors tColor) {
		bulletColor = tColor;
	}

	public Colors getColor() {
		return color;
	}

	public void setLevel(int tLevel) {
		level = tLevel;
	}

	public int getLevel() {
		return level;
	}

	public void showTowerMenu() {
		menu.showMenu();
		showRange();
	}

	public void hideTowerMenu() {
		menu.hideMenu();
		spriteRange.setVisible(false);
	}

	@Override
	public boolean onTouched(TouchEvent pSceneTouchEvent,
			float pTouchAreaLocalX, float pTouchAreaLocalY) {
		Debug.d("CLICK ON TOWER");
		if (menu.isVisible())
			hideTowerMenu();
		else
			showTowerMenu();
		return true;
	}

	// Metody obslugujace namierzenie Monstera i strzelanie

	public void work(ArrayList<Monster> monsters) {
		checkTarget();
		findTarget(monsters);
		rotate();
		shoot();

	}

	private void checkTarget() {
		if (target != null) {
			if (!isInRange(target) || target.isAlive() == false) {
				target = null;
			}
		}
	}

	private void findTarget(ArrayList<Monster> monsters) {
		if (target == null) {
			for (Monster m : monsters) {
				if (isInRange(m)) {
					target = m;
					Debug.d("W ZASIEGU MONSTER");
					break;
				}
			}
		}
	}

	private void rotate() {
		if (target != null) {
			this.setRotation(getRotationAngle(target));
		}
	}

	private void shoot() {
		if (target != null) {
			if (new Date().getTime() - lastFire > getRate()) {
				lastFire = new Date().getTime();

				WayPoint start = new WayPoint(this.getX()
						+ (this.getWidth() / 2), this.getY()
						+ (this.getHeight() / 2), 0);
				WayPoint end = new WayPoint(target.getX()
						+ (target.getWidth() / 2), target.getY()
						+ (target.getHeight() / 2), 0);

				Track track = new Track();
				track.setTrack(start, end);
				Bullet bullet = new Bullet(getBulletColor(), getDamage(),
						target);				
				bullet.move(track);
				this.animate(100, false);
			}
		}
	}

	private float getRotationAngle(Monster monster) {
		float x = monster.getX() - getX();
		float y = monster.getY() - getY();
		if (x != 0)
			if (x > 0)
				return (float) Math.toDegrees(Math.atan(y / x)) + 90;
			else
				return (float) Math.toDegrees(Math.atan(y / x)) + 270;
		return 0;
	}

	private boolean isInRange(Monster monster) {
		double xa, xb, ya, yb;
		xa = this.getX();
		ya = this.getY();
		xb = monster.getX();
		yb = monster.getY();
		float distance = (float) Math.sqrt(Math.pow((xa - xb), 2)
				+ Math.pow((ya - yb), 2));
		if (distance - 15 <= getRange())
			return true;
		else
			return false;
	}
	
	//Metody interfejsu MenuListener

	@Override
	public void action(int index) {
		switch (index) {
		case 0:
			hideTowerMenu();
			break;
		case 1:
			bulletColor = Colors.red;
			showTowerMenu();
			break;
		case 2:
			bulletColor = Colors.purple;
			showTowerMenu();
			break;
		case 3:
			bulletColor = Colors.blue;
			showTowerMenu();
			break;
		}
		
	}

	@Override
	public int[] getItemsStatus() {
		int[] status = new int[3];
		switch (level) {
		case 0:
			status[0] = 1;
			status[1] = 2;
			status[2] = 2;
			break;
		case 1:
			status[0] = 1;
			status[1] = 1;
			status[2] = 2;
			break;
		case 2:
			status[0] = 1;
			status[1] = 1;
			status[2] = 1;
			break;
		}
		if (bulletColor == Colors.red)
			status[0] = 0;
		else if (bulletColor == Colors.purple)
			status[1] = 0;
		else if (bulletColor == Colors.blue)
			status[2] = 0;
		return status;
	}

	@Override
	public PointF getPosition() {
		float tX = 0;
		float tY = 0;

		if (this.getX() - menu.getWidth() >= 0)
			tX = this.getX() - menu.getWidth();
		else
			tX = this.getX() + this.getWidth();

		if ((this.getY() + this.getHeight()) - menu.getHeight() >= 0)
			tY = (this.getY() + this.getHeight()) - menu.getHeight();
		else
			tY = this.getY();
		return new PointF(tX, tY);
	}
	
	@Override
	public void close() {
		spriteRange.setVisible(false);		
	}

}
