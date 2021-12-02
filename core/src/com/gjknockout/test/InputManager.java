package com.gjknockout.test;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class InputManager {

    public static void render(Entity entity, int MAX_WIDTH, int MAX_HEIGHT, int speed, OrthographicCamera camera) {
        if (Gdx.input.isTouched()) {
            Vector3 touchPos = new Vector3();
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchPos);
            entity.x = touchPos.x - 64 / 2;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A)) entity.x -= speed * Gdx.graphics.getDeltaTime();
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D)) entity.x += speed * Gdx.graphics.getDeltaTime();
        if (Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isKeyPressed(Input.Keys.W)) entity.y += speed * Gdx.graphics.getDeltaTime();
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN) || Gdx.input.isKeyPressed(Input.Keys.S)) entity.y -= speed * Gdx.graphics.getDeltaTime();

        // make sure the bucket stays within the screen bounds
        if (entity.x < 0) entity.x = 0;
        if (entity.x > MAX_WIDTH - entity.width) entity.x = MAX_WIDTH - entity.width;
        if (entity.y < 0) entity.y = 0;
        if (entity.y > MAX_HEIGHT - entity.height) entity.y = MAX_HEIGHT - entity.height;
    }
}
