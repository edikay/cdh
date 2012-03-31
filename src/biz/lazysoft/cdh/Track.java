package biz.lazysoft.cdh;

import monsters.Monster;

import org.anddev.andengine.entity.IEntity;
import org.anddev.andengine.entity.modifier.PathModifier;
import org.anddev.andengine.entity.modifier.PathModifier.IPathModifierListener;
import org.anddev.andengine.entity.modifier.PathModifier.Path;

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

	public PathModifier getPathModifer(final Monster monster) {

		duration = path.getLength() / monster.getSpeed();
		return new PathModifier(duration, path, null,
				new IPathModifierListener() {
					@Override
					public void onPathStarted(final PathModifier pPathModifier,
							final IEntity pEntity) {
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

}
