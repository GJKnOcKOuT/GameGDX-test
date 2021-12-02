package com.gjknockout.test;

import java.util.Iterator;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
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


public class Game extends com.badlogic.gdx.Game {
    public static boolean leftMove;
    public static boolean rightMove;
    public static boolean upMove;
    public static boolean downMove;
    public static float MAX_HEIGHT = 480;
    public static float MAX_WIDTH = 800;
    public static int score;
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
    private double scale = 1;

    @Override
        public void create() {
        /*
        TODO: Separate each different element in his own function and optimize this part
         */

        //Setting the InputProcessor
        InputManager inputProcessor = new InputManager();
        Gdx.input.setInputProcessor(inputProcessor);

        //Load the image file
        playerImg = new Texture(Gdx.files.internal("mc.png"));
        background = new Texture(Gdx.files.internal("background.png"));
        appleImg = new Texture(Gdx.files.internal("apple.png"));

        //Load the sound file
        eating = Gdx.audio.newSound(Gdx.files.internal("eating.ogg"));
        music = Gdx.audio.newMusic(Gdx.files.internal("Overworld1.ogg"));

        //Start music
        music.setLooping(true);
        music.setVolume((float) 0.1);
        music.play();

        //Create camera
        camera = new OrthographicCamera();
        camera.setToOrtho(false, MAX_WIDTH, MAX_HEIGHT);

        //Create batch
        batch = new SpriteBatch();
        player = new Player();

        //Setting player values
        player.width = 64;
        player.height = 64;
        player.x = MAX_WIDTH / 2 - player.width / 2;
        player.y = 20;

        //Creating apples array and starting the spawnApple() function
        apples = new Array<>();
        spawnApple();

    }

    private void spawnApple() {
        Rectangle apple = new Rectangle();
        apple.width = (float) (32 * scale);
        apple.height = (float) (39 * scale);
        apple.x = MathUtils.random(0, MAX_WIDTH - apple.width);
        apple.y = MathUtils.random(MAX_HEIGHT / 3, MAX_HEIGHT - apple.height);
        apples.add(apple);
        lastApple = TimeUtils.nanoTime();
    }

    public void setRightMove(boolean status) {

    }

    public void update() {
        if (leftMove) {
            player.x -= 200 * scale * Gdx.graphics.getDeltaTime();
        }
        if (rightMove) {
            player.x += 200 * scale * Gdx.graphics.getDeltaTime();
        }
        if (upMove) {
            player.y += 200 * scale * Gdx.graphics.getDeltaTime();
        }
        if (downMove) {
            player.y -= 200 * scale * Gdx.graphics.getDeltaTime();
        }
        if (player.x > MAX_WIDTH - player.width) player.x = MAX_WIDTH - player.width;
        if (player.x < 0) player.x = 0;
        if (player.y > MAX_HEIGHT - player.height) player.y = MAX_HEIGHT - player.height;
        if (player.y < 0) player.y = 0;
    }

    @Override
    public void render() {
        ScreenUtils.clear(1, 0, 0, 1);
        update();
        camera.update();

        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        batch.draw(background, 0, 0);
        batch.draw(playerImg, player.x, player.y);
        for (Rectangle apple : apples) {
            batch.draw(appleImg, apple.x, apple.y);
        }
        batch.end();
        Optionals.render(camera, batch);

        if (TimeUtils.nanoTime() - lastApple > 1000000000L * 3) spawnApple();

        for (Iterator<Rectangle> iter = apples.iterator(); iter.hasNext(); ) {
            Rectangle apple = iter.next();
            apple.y -= 200 * Gdx.graphics.getDeltaTime();
            if (apple.y + 64 < 0) iter.remove();
            if (apple.overlaps(player)) {
                eating.play();
                score++;
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
