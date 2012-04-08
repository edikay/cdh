package biz.lazysoft.cdh.andengine;

import java.io.IOException;
import java.util.Hashtable;

import org.anddev.andengine.audio.music.Music;
import org.anddev.andengine.audio.music.MusicFactory;
import org.anddev.andengine.audio.sound.Sound;
import org.anddev.andengine.audio.sound.SoundFactory;
import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.opengl.font.Font;
import org.anddev.andengine.opengl.texture.Texture;
import org.anddev.andengine.opengl.texture.TextureOptions;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.anddev.andengine.opengl.texture.region.TextureRegion;
import org.anddev.andengine.opengl.texture.region.TiledTextureRegion;
import org.anddev.andengine.util.Debug;

import biz.lazysoft.cdh.Names;

import android.content.Context;
import android.graphics.Typeface;

public class AssetPool {
	
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================
	
	private static AssetPool instance;
	private Context context;
	private Engine engine;
	
	private Hashtable<Names, TextureRegion> images;
	private Hashtable<Names, TiledTextureRegion> tiles;
	private Hashtable<Names, Sound> sounds;
	private Hashtable<Names, Music> musics;
	private Hashtable<Names, Font> fonts;
	
	// ===========================================================
	// Constructors
	// ===========================================================

	private void AssetPool() {
	}
	
	// ===========================================================
	// Getter & Setter
	// ===========================================================

	public static AssetPool getInstance() {
		if (instance == null) {
			instance = new AssetPool();
		} 
		return instance;
	}
	
	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	public void init(Context context, Engine engine) {
		this.context = context;
		this.engine = engine;
	}
	
	//
	// Image
	//
	
	public void loadTextureRegion(Names key, String path, int width, int height) {
		if (images == null) {
			images = new Hashtable<Names, TextureRegion>();
		}	
		BitmapTextureAtlas texture = new BitmapTextureAtlas(width, height, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		TextureRegion region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(texture, context, path, 0, 0);
		images.put(key, region);
		engine.getTextureManager().loadTexture(texture);
	}
	
	public TextureRegion getTR(Names key) {
		return images.get(key).deepCopy();
	}
	
	//
	// Animated Image
	//
	
	public void loadTiledTextureRegion(Names key, String path, int width, int height, int columns, int rows) {
		if (tiles == null) {
			tiles = new Hashtable<Names, TiledTextureRegion>();
		}
		BitmapTextureAtlas texture = new BitmapTextureAtlas(width, height, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		TiledTextureRegion region = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(texture, context, path, 0, 0, columns, rows);
		tiles.put(key, region);
		engine.getTextureManager().loadTexture(texture);
	}
	
	public TiledTextureRegion getTTR(Names key) {
		return tiles.get(key).deepCopy();
	}
	
	//
	// Sound
	//
	
	public void loadSound(Names key, String path) {
		if (sounds == null) {
			sounds = new Hashtable<Names, Sound>();
		}
		try {
			Sound sound = SoundFactory.createSoundFromAsset(engine.getSoundManager(), context, path);
			sounds.put(key, sound);
		} catch (IOException e) {
			Debug.e(e);
		}
	}
	
	public Sound getSound(Names key) {
		return sounds.get(key);
	}
	
	//
	// Music
	//
	
	public void loadMusic(Names key, String path) {
		if (musics == null) {
			musics = new Hashtable<Names, Music>();
		}
		try {
			Music music = MusicFactory.createMusicFromAsset(engine.getMusicManager(), context, path);
			musics.put(key, music);
		} catch (IOException e) {
			Debug.e(e);
		}
	}
	
	public Music getMusic(String key) {
		return musics.get(key);
	}
	
	//
	// Font
	// 

	public void loadFont(Names key, Typeface typeface, float size, boolean antiAlias, int color) {
		if (fonts == null) {
			fonts = new Hashtable<Names, Font>();
		}	
		BitmapTextureAtlas texture = new BitmapTextureAtlas(256, 256, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		Font font = new Font(texture, typeface, size, antiAlias, color);
		engine.getTextureManager().loadTexture(texture);
		engine.getFontManager().loadFont(font);
		fonts.put(key, font);
	}
	
	public Font getFont(Names key) {
		return fonts.get(key);
	}
	
	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
	
}
