package biz.lazysoft.cdh;
import java.util.HashMap;

import org.anddev.andengine.opengl.font.Font;
import org.anddev.andengine.opengl.texture.TextureManager;
import org.anddev.andengine.opengl.texture.region.TextureRegion;
import org.anddev.andengine.opengl.texture.region.TiledTextureRegion;


public class TM {
	
	
	static HashMap<Names,Object> textures = new HashMap<Names, Object>();
	TextureManager tm = new TextureManager();
		

	public static void add(Names name,TextureRegion texture)
	{
		
		textures.put(name, (Object)texture);
	}
	
	public static void add(Names name,TiledTextureRegion texture)
	{
		textures.put(name, (Object)texture);
	}
	
	public static void add(Names name,Font font)
	{
		textures.put(name, (Object)font);
	}
	
	public static TextureRegion getTR(Names name)
	{		
		return ((TextureRegion)textures.get(name)).deepCopy();
	}
	
	public static TiledTextureRegion getTTR(Names name)
	{		
		return ((TiledTextureRegion)textures.get(name)).deepCopy();
	}
	
	public static Font getF(Names name)
	{		
		return (Font)textures.get(name);
	}
	
}
