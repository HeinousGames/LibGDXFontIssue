package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGeneratorLoader;
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	BitmapFont f;
	AssetManager assetManager;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");

		assetManager = new AssetManager();

		FileHandleResolver resolver = new InternalFileHandleResolver();
		assetManager.setLoader(FreeTypeFontGenerator.class, new FreeTypeFontGeneratorLoader(resolver));
		assetManager.setLoader(BitmapFont.class, ".ttf", new FreetypeFontLoader(resolver));

		FreetypeFontLoader.FreeTypeFontLoaderParameter parameter7 = new FreetypeFontLoader.FreeTypeFontLoaderParameter();
		parameter7.fontFileName = "bungee_shade_regular.ttf";
		parameter7.fontParameters.size = 64;
		parameter7.fontParameters.color = Color.WHITE; //Colors.OFF_WHITE;
		parameter7.fontParameters.borderColor = Color.WHITE; //Colors.OFF_WHITE;
		parameter7.fontParameters.borderWidth = 1f;
		assetManager.load("BungeeRegularWhiteReal.ttf", BitmapFont.class, parameter7);

		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("bungee_shade_regular.ttf"));
		FreeTypeFontGenerator.FreeTypeFontParameter params = new FreeTypeFontGenerator.FreeTypeFontParameter();
		params.size = 64;
		params.color = Color.WHITE;
		params.borderColor = Color.WHITE;
		params.borderWidth = 1f;
		f = generator.generateFont(params);
		generator.dispose();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(img, 0, 0);
		if (assetManager.isLoaded("BungeeRegularWhiteReal.ttf", BitmapFont.class)) {
			/*assetManager.get("BungeeRegularWhiteReal.ttf", BitmapFont.class)*/
			f.draw(batch, "TAP ANYWHERE TO PLAY!", 300, 100);
		} else {
			assetManager.update();
		}
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
