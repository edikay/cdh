package biz.lazysoft.cdh;


import org.anddev.andengine.entity.IEntity;
import org.anddev.andengine.entity.modifier.EntityModifier;
import org.anddev.andengine.entity.modifier.PathModifier;
import org.anddev.andengine.entity.modifier.PathModifier.IPathModifierListener;
import org.anddev.andengine.entity.modifier.PathModifier.Path;
import org.anddev.andengine.util.Debug;

import biz.lazysoft.cdh.monsters.Monster;



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
		EntityModifier pa;
		PathModifier as;
		
		Path path=calcPath(monster);
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
						Debug.d("BULLET STOP");						
						bullet.hit();				
						
					}
				});

	}
	
	public Path calcPath(Monster monster)
	{
		float sizeMonster = monster.getWidth()/2;
		float cx[] = path.getCoordinatesX();
		float cy[] = path.getCoordinatesY();
		
		float ncx[] = path.getCoordinatesX();
		float ncy[] = path.getCoordinatesY();
		
		for(int i=0;i<cx.length;i++)
		{
			
			ncx[i]=cx[i]-sizeMonster;
			ncy[i]=cy[i]-sizeMonster;
			Debug.d("step "+i+" : x="+cx[i]+" , cy="+cy[i]);
		}
		return new Path(ncx,ncy);
	}
	
	

}
