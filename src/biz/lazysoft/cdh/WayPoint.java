package biz.lazysoft.cdh;

public class WayPoint {

	private float x;
	private float y;
	private float rotate;

	WayPoint(float x, float y, float rotate) {
		this.x = x;
		this.y = y;
		this.rotate = rotate;
	}

	public float getX() {
		return x;
	}
	
	public float getY() {
		return y;
	}
	
	public float getRotate() {
		return rotate;
	}
}
