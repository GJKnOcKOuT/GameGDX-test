package com.gjknockout.test;

import java.util.Iterator;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.TimeUtils;


public class Game extends ApplicationAdapter {
    public final int MAX_HEIGHT = 480;
    public final int MAX_WIDTH = 800;
    private Texture playerImg;
    private Texture background;
    private Texture appleImg;
    private Sound eating;
    private Music music;
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private Player player;
    private Array<Rectangle> apples;
    private long lastApple;

    @Override
    public void create() {
        //load the image file
        playerImg = new Texture(Gdx.files.internal("mc.png"));
        background = new Texture(Gdx.files.internal("background.png"));
        appleImg = new Texture(Gdx.files.internal("apple.png"));
        //load the sound file
        eating = Gdx.audio.newSound(Gdx.files.internal("eating.ogg"));
        music = Gdx.audio.newMusic(Gdx.files.internal("Overworld1.ogg"));
        //Start music
        music.setLooping(true);
        music.play();
        //create camera
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        //create batch
        batch = new SpriteBatch();
        player = new Player();
        player.width = 64;
        player.height = 64;
        player.x = MAX_WIDTH / 2 - player.width / 2;
        player.y = 20;

        apples = new Array<>();
        spawnApple();

    }

    private void spawnApple() {
        Rectangle apple = new Rectangle();
        apple.x = MathUtils.random(0, 800 - 32);
        apple.y = MathUtils.random(0, 480 - 39);
        apple.width = 32;
        apple.height = 39;
        apples.add(apple);
        lastApple = TimeUtils.nanoTime();
    }

    @Override
    public void render() {
        ScreenUtils.clear(1, 0, 0, 1);
        camera.update();

        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        batch.draw(background, 0, 0);
        batch.draw(playerImg, player.x, player.y);
        for (Rectangle apple : apples) {
            batch.draw(appleImg, apple.x, apple.y);
        }
        batch.end();
        Optionals.render(camera,batch);

        InputManager.render(player,MAX_WIDTH,MAX_HEIGHT,200,camera);
        if (TimeUtils.nanoTime() - lastApple > 1000000000L * 3) spawnApple();

        for (Iterator<Rectangle> iter = apples.iterator(); iter.hasNext(); ) {
            Rectangle apple = iter.next();
            apple.y -= 200 * Gdx.graphics.getDeltaTime();
            if (apple.y + 64 < 0) iter.remove();
            if (apple.overlaps(player)) {
                eating.play();
                iter.remove();
            }
        }
    }

    @Override
    public void dispose() {
        batch.dispose();
        background.dispose();
        playerImg.dispose();
        music.dispose();
    }
}
