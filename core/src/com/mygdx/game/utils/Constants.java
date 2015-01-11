package com.mygdx.game.utils;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.enums.PlantDataType;

public class Constants {

    public static final int APP_WIDTH = 800;
    public static final int APP_HEIGHT = 480;

    public static final Vector2 WORLD_GRAVITY = new Vector2(0, 0);

    public static final float GROUND_X = 4;
    public static final float GROUND_Y = 0;
    public static final float GROUND_WIDTH = 5f;
    public static final float GROUND_HEIGHT = 4f;
    public static final float GROUND_DENSITY = 0f;

    public static int plant_X = 0;
    public static int plant_Y = 0;
    public static final float PLANT_WIDTH = 0.8f;
    public static final float PLANT_HEIGHT = 0.6f;
    public static final float RUNNER_DENSITY = 0.5f;
    public static final float RUNNER_DODGE_X = 2f;
    public static final float RUNNER_DODGE_Y = 1.5f;
    public static final float RUNNER_HIT_ANGULAR_IMPULSE = 10f;

    public static final float ENEMY_X = 25f;
    public static final float ENEMY_DENSITY = RUNNER_DENSITY;
    public static final float WAKING_ENEMY_Y = 0.5f;
    public static final Vector2 ENEMY_LINEAR_VELOCITY = new Vector2(-1f, 0);
    public static final Vector2 BULLET_LINEAR_VELOCITY = new Vector2(6f, 0);

    public static final int[] ENEMY_Y = new int[] {1, 3, 5, 7, 9};
    public static final float BULLET_WIDTH = 0.3f;
    public static final float BULLET_HEIGHT = 0.3f;
    public static final float BULLET_DENSITY = RUNNER_DENSITY;

    public static boolean peasIsSelected = false;
    public static boolean coordinatePeasIsSelected = false;
    public static boolean nutIsSelected= false;
    public static boolean coordinateNutIsSelected = false;
    public static boolean pepperIsSelected = false;
    public static boolean coordinatePepperIsSelected = false;

    /* Атлас растений ***************/
    public static final String PLANT_ATLAS_PATH = "images/plants.txt";
    public static final String[] PEAS_STANDING_REGION_NAME = new String[] {"peas1", "peas2", "peas3", "peas4", "peas5"};
    public static final String[] NUT1_STANDING_REGION_NAME = new String[] {"nut1", "nut2", "nut3", "nut4"};
    public static final String[] NUT2_STANDING_REGION_NAME = new String[] {"nut21", "nut22", "nut23", "nut24"};
    public static final String[] NUT3_STANDING_REGION_NAME = new String[] {"nut31", "nut32", "nut33", "nut34"};
    public static final String BULLET_REGION_NAME = "ball";
    public static final String PEPPER_REGION_NAME = "2";
    public static final String[] FIRE_REGION_NAME =  new String[] {"fire1", "fire2", "fire3", "fire4", "fire5"};
    /*******************************/

    public static final String BACKGROUND_IMAGE_PATH = "images/background.jpg";
    public static final String BACKGROUND2_IMAGE_PATH = "images/background1.jpg";
    public static final float WORLD_TO_SCREEN = 32;

    public static final String ENEMY_ATLAS_PATH = "images/sceleton.txt";
    public static final String[] ENEMY_EATING_REGION_NAMES = new String[] {"sceletoneat1", "sceletoneat2", "sceletoneat4", "sceletoneat5"};
    public static final String[] ENEMY_WAKING_REGION_NAMES = new String[] {"sceleton1", "sceleton2", "sceleton3", "sceleton4","sceleton5", "sceleton6", "sceleton7", "sceleton8", "sceleton9"};

    public static int suns = 0;

}