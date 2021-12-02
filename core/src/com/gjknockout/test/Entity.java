package com.gjknockout.test;

import com.badlogic.gdx.math.Rectangle;

public class Entity extends Rectangle {
    float speed;
    boolean isActive = false;


    public float getHeight() {
        return height;
    }

    public float getWidth() {
        return width;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public float getX() {
        return x;
    }


    public float getY() {
        return y;
    }


    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
