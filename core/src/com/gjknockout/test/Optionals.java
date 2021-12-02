package com.gjknockout.test;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.awt.*;

public class Optionals {
    public static boolean showFPS = true;
    public static BitmapFont font = new BitmapFont(Gdx.files.internal("font.fnt"), Gdx.files.internal("font.png"), false);
    ;

    public void create() {
    }

    public static void render(OrthographicCamera camera, SpriteBatch batch) {
        batch.begin();
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        font.draw(batch, "Upper left, FPS=" + Gdx.graphics.getFramesPerSecond(), 0, camera.viewportHeight);
        batch.end();
    }
}
