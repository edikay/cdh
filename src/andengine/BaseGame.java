package andengine;

import org.anddev.andengine.opengl.font.Font;
import org.anddev.andengine.opengl.font.FontManager;
import org.anddev.andengine.opengl.texture.TextureOptions;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.anddev.andengine.opengl.texture.region.TextureRegion;
import org.anddev.andengine.opengl.texture.region.TiledTextureRegion;
import org.anddev.andengine.ui.activity.BaseGameActivity;

import android.graphics.Color;
import android.graphics.Typeface;

public abstract class BaseGame extends BaseGameActivity {

	public TextureRegion loadTextureRegion(String tPath, int tWidth, int tHeight) {
		BitmapTextureAtlas tmpAtlas;
		tmpAtlas = new BitmapTextureAtlas(tWidth, tHeight,
				TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		TextureRegion tmpTexture;
		tmpTexture = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				tmpAtlas, this, tPath, 0, 0);
		this.mEngine.getTextureManager().loadTexture(tmpAtlas);
		return tmpTexture;
	}

	public TiledTextureRegion loadTiledTextureRegion(String tPath, int tWidth,
			int tHeight, int tTileColumns, int tTileRows) {
		BitmapTextureAtlas tmpAtlas;
		tmpAtlas = new BitmapTextureAtlas(tWidth, tHeight,
				TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		TiledTextureRegion tmpTexture;
		tmpTexture = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(tmpAtlas, this, tPath, 0, 0,
						tTileColumns, tTileRows);
		this.mEngine.getTextureManager().loadTexture(tmpAtlas);
		return tmpTexture;
	}

	public Font loadFont(int color) {
		BitmapTextureAtlas tmpAtlas = new BitmapTextureAtlas(256, 256, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		Font font = new Font(tmpAtlas, Typeface.create(Typeface.DEFAULT, Typeface.BOLD), 35, true, Color.WHITE);
		this.mEngine.getTextureManager().loadTexture(tmpAtlas);
        this.getFontManager().loadFont(font);		
		return font;
	}

	public FontManager getFontManager() {
		return this.mEngine.getFontManager();
	}

}
