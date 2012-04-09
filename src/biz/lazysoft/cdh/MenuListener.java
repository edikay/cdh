package biz.lazysoft.cdh;

import android.graphics.PointF;

public interface MenuListener {
	
	public void action(int index);
	public int[] getItemsStatus();
	public PointF getPosition();

}
