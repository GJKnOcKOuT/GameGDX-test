package com.gjknockout.test.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.gjknockout.test.Game;

public class DesktopLauncher {
    public static void main(String[] arg) {
        /*
        TODO: Check all different option that can improve/modify this page to make it more defined
         */
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.vSyncEnabled = true;
        config.foregroundFPS = 60;
        config.backgroundFPS = 30;
        config.width = 800;
        config.height = 480;
        config.title = "Prova";
        new LwjglApplication(new Game(), config);
    }
}
