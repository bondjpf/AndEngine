package org.andengine.opengl.texture.atlas.buildable;


import org.andengine.opengl.texture.atlas.ITextureAtlas;
import org.andengine.opengl.texture.atlas.source.ITextureAtlasSource;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.TextureRegion;
import org.andengine.opengl.texture.region.TiledTextureRegion;
import org.andengine.util.Callback;


/**
 * (c) 2010 Nicolas Gramlich 
 * (c) 2011 Zynga Inc.
 * 
 * @author Nicolas Gramlich
 * @since 16:42:08 - 12.07.2011
 */
public class BuildableTextureAtlasTextureRegionFactory {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	// ===========================================================
	// Constructors
	// ===========================================================

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Methods using BuildableBitmapTexture
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================

	public static <T extends ITextureAtlasSource, A extends ITextureAtlas<T>> ITextureRegion createFromSource(final BuildableTextureAtlas<T, A> pBuildableTextureAtlas, final T pTextureAtlasSource, final boolean pRotated) {
		final TextureRegion textureRegion = new TextureRegion(pBuildableTextureAtlas, 0, 0, pTextureAtlasSource.getWidth(), pTextureAtlasSource.getHeight(), pRotated);
		pBuildableTextureAtlas.addTextureAtlasSource(pTextureAtlasSource, new Callback<T>() {
			@Override
			public void onCallback(final T pCallbackValue) {
				textureRegion.setPosition(pCallbackValue.getTexturePositionX(), pCallbackValue.getTexturePositionY());
			}
		});
		return textureRegion;
	}

	public static <T extends ITextureAtlasSource, A extends ITextureAtlas<T>> TiledTextureRegion createTiledFromSource(final BuildableTextureAtlas<T, A> pBuildableTextureAtlas, final T pTextureAtlasSource, final int pTileColumns, final int pTileRows) {
		final TiledTextureRegion tiledTextureRegion = TiledTextureRegion.create(pBuildableTextureAtlas, 0, 0, pTextureAtlasSource.getWidth(), pTextureAtlasSource.getHeight(), pTileColumns, pTileRows);
		pBuildableTextureAtlas.addTextureAtlasSource(pTextureAtlasSource, new Callback<T>() {
			@Override
			public void onCallback(final T pCallbackValue) {
				final int tileWidth = pTextureAtlasSource.getWidth() / pTileColumns;
				final int tileHeight = pTextureAtlasSource.getHeight() / pTileRows;
				
				for(int tileColumn = 0; tileColumn < pTileColumns; tileColumn++) {
					for(int tileRow = 0; tileRow < pTileRows; tileRow++) {
						final int tileIndex = tileRow * pTileColumns + tileColumn;

						final int x = pCallbackValue.getTexturePositionX() + tileColumn * tileWidth;
						final int y = pCallbackValue.getTexturePositionY() + tileRow * tileHeight;
						
						tiledTextureRegion.setPosition(tileIndex, x, y);
					}
				}
			}
		});
		return tiledTextureRegion;
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
