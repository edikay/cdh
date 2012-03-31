package biz.lazysoft.cdh;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.entity.scene.Scene;

import android.os.Bundle;

public class CdhActivity extends  BaseGame{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        //Widzisz to?
    }

	@Override
	public Engine onLoadEngine() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onLoadResources() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Scene onLoadScene() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onLoadComplete() {
		// TODO Auto-generated method stub
		
	}
}