package com.gjknockout.test;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.awt.*;

public class Optionals {
    /*
    TODO: Add different optionals and options this could be the settings tab,
     * add Vsync
     * add custom fps in forecground and background, add
     * add On/Off FPS counter
     */
    public static boolean showFPS = true;
    public static BitmapFont font = new BitmapFont(Gdx.files.internal("font.fnt"), Gdx.files.internal("font.png"), false);
    ;

    public void create() {
    }

    public static void render(OrthographicCamera camera, SpriteBatch batch) {
        batch.begin();
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        GlyphLayout layout = new GlyphLayout();
        String str = "Score:" + Game.score;
        layout.setText(font,str);
        font.draw(batch, "FPS:" + Gdx.graphics.getFramesPerSecond(), 0, camera.viewportHeight);
        font.draw(batch, layout, Game.MAX_WIDTH - layout.width,camera.viewportHeight);
        batch.end();
    }
}
