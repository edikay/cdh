package towers;

import java.util.ArrayList;
import java.util.Date;

import monsters.Monster;

import org.anddev.andengine.entity.sprite.AnimatedSprite;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.input.touch.TouchEvent;
import org.anddev.andengine.util.Debug;

import andengine.AssetPool;
import biz.lazysoft.cdh.Bullet;
import biz.lazysoft.cdh.CdhActivity;
import biz.lazysoft.cdh.Colors;
import biz.lazysoft.cdh.Names;
import biz.lazysoft.cdh.TowerMenu;
import biz.lazysoft.cdh.Track;
import biz.lazysoft.cdh.WayPoint;

public class Tower extends AnimatedSprite {

	float[] rate;
	float[] damage;
	float[] range;
	float[] cost;
	int level = 0;
	Colors color;

	private Colors bulletColor = Colors.red;

	private long lastFire;
	private Sprite spriteRange;
	private Monster target = null;

	private TowerMenu towerMenu;

	public Tower(Names name, Colors tColor) {
		super(0, 0, 90, 90, AssetPool.getInstance().getTTR(name));
		rate = new float[3];
		damage = new float[3];
		range = new float[3];
		cost = new float[3];
		color = tColor;
		spriteRange = new Sprite(0, 0, AssetPool.getInstance().getTR(Names.range));
		spriteRange.setVisible(false);
		this.attachChild(spriteRange);
		towerMenu = new TowerMenu(this);
		CdhActivity.scene.attachChild(towerMenu);
		towerMenu.setVisible(false);
		
	}

	public float getRotationAngle(Monster monster) {
		float x = monster.getX() - getX();
		float y = monster.getY() - getY();
		if (x != 0)
			return (float) Math.toDegrees(Math.atan(y / x)) + 90;
		return 0;
	}

	public float getRate() {
		return rate[level];
	}

	public float getDamage() {
		
		if (color == bulletColor)
		{
			Debug.d("Damage = "+damage[level] * (1 + (getLevel() + 1) * 0.1f));
			return damage[level] * (1 + (getLevel() + 1) * 0.1f);
			
		}
		else
		{
			Debug.d("Damage = "+damage[level]);
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

	public boolean fire(Monster monster) {
		if (isInRange(monster)) {
			if (new Date().getTime() - lastFire > getRate()) {
				lastFire = new Date().getTime();

				WayPoint start = new WayPoint(this.getX()
						+ (this.getWidth() / 2), this.getY()
						+ (this.getHeight() / 2), 0);
				WayPoint end = new WayPoint(monster.getX()
						+ (monster.getWidth() / 2), monster.getY()
						+ (monster.getHeight() / 2), 0);

				Track track = new Track();
				track.setTrack(start, end);
				Bullet bullet = new Bullet(getBulletColor(), getDamage(),
						monster);
				this.getParent().attachChild(bullet);
				bullet.move(track);
				this.animate(100, false);
				return true;
			}
		}
		return false;
	}

	public void checkFire(ArrayList<Monster> monsters) {
		if (target != null && isInRange(target) == true) {
			this.setRotation(getRotationAngle(target));

			Debug.d("NAMIERZONY    angel=" + getRotationAngle(target)
					+ "FIRE: " + fire(target));
		} else {
			for (Monster m : monsters) {
				if (isInRange(m)) {
					target = m;
					Debug.d("W ZASIEGU MONSTER");
					break;

				}
			}
		}
	}

	public void setLevel(int tLevel) {
		level = tLevel;
	}

	public int getLevel() {
		return level;
	}
	
	private void setSpriteRange() {
		spriteRange.setSize(getRange() * 2, getRange() * 2);
		spriteRange.setPosition((this.getWidth() / 2)
				- (spriteRange.getWidth() / 2), (this.getHeight() / 2)
				- (spriteRange.getHeight() / 2));
		spriteRange.setVisible(true);

	}

	public void showTowerMenu() {
		float tX = 0;
		float tY = 0;

		if (this.getX() - towerMenu.getWidth() >= 0)
			tX = this.getX() - towerMenu.getWidth();
		else
			tX = this.getX() + this.getWidth();

		if ((this.getY() + this.getHeight()) - towerMenu.getHeight() >= 0)
			tY = (this.getY() + this.getHeight()) - towerMenu.getHeight();
		else
			tY = this.getY();

		towerMenu.setPosition(tX, tY);
		towerMenu.setVisible(true);
		
		setSpriteRange();
		
	}

	public void hideTowerMenu() {
		towerMenu.setVisible(false);
		spriteRange.setVisible(false);
	}

	@Override
	public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
			float pTouchAreaLocalX, float pTouchAreaLocalY) {
		if (towerMenu.isVisible())
			hideTowerMenu();
		else
			showTowerMenu();
		return super.onAreaTouched(pSceneTouchEvent, pTouchAreaLocalX,
				pTouchAreaLocalY);
	}

}
