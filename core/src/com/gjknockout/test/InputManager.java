package com.gjknockout.test;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class InputManager implements InputProcessor {

    @Override
    public boolean keyDown(int keycode)
    {
        switch (keycode)
        {
            case Input.Keys.A:
            case Input.Keys.LEFT:
                Game.leftMove = true;
                break;
            case Input.Keys.D:
            case Input.Keys.RIGHT:
                Game.rightMove = true;
                break;
            case Input.Keys.W:
            case Input.Keys.UP:
                Game.upMove = true;
                break;
            case  Input.Keys.S:
            case Input.Keys.DOWN:
                Game.downMove = true;
                break;
        }
        return true;
    }
    @Override
    public boolean keyUp(int keycode)
    {
        switch (keycode)
        {
            case Input.Keys.A:
            case Input.Keys.LEFT:
                Game.leftMove = false;
                break;
            case Input.Keys.D:
            case Input.Keys.RIGHT:
                Game.rightMove = false;
                break;
            case Input.Keys.W:
            case Input.Keys.UP:
                Game.upMove = false;
                break;
            case  Input.Keys.S:
            case Input.Keys.DOWN:
                Game.downMove = false;
                break;
        }
        return true;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}
