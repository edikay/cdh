package biz.lazysoft.cdh;

import monsters.Monster;

import org.anddev.andengine.entity.IEntity;
import org.anddev.andengine.entity.modifier.PathModifier;
import org.anddev.andengine.entity.modifier.PathModifier.IPathModifierListener;
import org.anddev.andengine.entity.modifier.PathModifier.Path;
import org.anddev.andengine.util.Debug;

import towers.Tower;

public class Track {

	public Path path;
	public float[] rotations;
	public float duration;

	public void setTrack(WayPoint... points) {
		int size = points.length;
		path = new Path(size);
		rotations = new float[size];
		int i = 0;
		for (WayPoint wp : points) {
			path.to(wp.getX(), wp.getY());
			rotations[i++] = wp.getRotate();
		}
	}

	public PathModifier getPathModiferMonster(final Monster monster) {

		duration = path.getLength() / monster.getSpeed();
		return new PathModifier(duration, path, null,
				new IPathModifierListener() {
					@Override
					public void onPathStarted(final PathModifier pPathModifier,
							final IEntity pEntity) {
						monster.animate(150, true);
					}

					@Override
					public void onPathWaypointStarted(
							final PathModifier pPathModifier,
							final IEntity pEntity, final int pWaypointIndex) {
						monster.setRotation(rotations[pWaypointIndex]);
					}

					@Override
					public void onPathWaypointFinished(
							final PathModifier pPathModifier,
							final IEntity pEntity, final int pWaypointIndex) {
					}

					@Override
					public void onPathFinished(
							final PathModifier pPathModifier,
							final IEntity pEntity) {
					}
				});

	}
	
	public PathModifier getPathModiferBullet(final Bullet bullet) {

		duration = path.getLength() / bullet.getSpeed();
		return new PathModifier(duration, path, null,
				new IPathModifierListener() {
					@Override
					public void onPathStarted(final PathModifier pPathModifier,
							final IEntity pEntity) {
						//bullet.animate(150, true);
						Debug.d("BULLET START");
					}

					@Override
					public void onPathWaypointStarted(
							final PathModifier pPathModifier,
							final IEntity pEntity, final int pWaypointIndex) {						
					}

					@Override
					public void onPathWaypointFinished(
							final PathModifier pPathModifier,
							final IEntity pEntity, final int pWaypointIndex) {
					}

					@Override
					public void onPathFinished(
							final PathModifier pPathModifier,
							final IEntity pEntity) {
						bullet.hit();
						bullet.setVisible(false);
						Debug.d("BULLET STOP");
							
						
					}
				});

	}
	
	

}
