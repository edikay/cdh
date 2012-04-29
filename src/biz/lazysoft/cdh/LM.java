package biz.lazysoft.cdh;

import org.anddev.andengine.engine.Engine;

import android.content.Context;
import biz.lazysoft.cdh.andengine.AssetPool;

public class LM {

	private static LM instance;

	private LM(){
	}
	
	public static LM getInstance() {
		if (instance == null) {
			instance = new LM();
		} 
		return instance;
	}
	
	public void init() {
		
	}
}
