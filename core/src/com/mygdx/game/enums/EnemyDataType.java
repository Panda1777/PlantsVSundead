package com.mygdx.game.enums;

import com.mygdx.game.utils.Constants;

public enum EnemyDataType {

    RUNNING_SMALL(1f, 0.5f, Constants.ENEMY_X, Constants.WAKING_ENEMY_Y, Constants.ENEMY_DENSITY,
            Constants.ENEMY_WAKING_REGION_NAMES, Constants.ENEMY_EATING_REGION_NAMES, 5);

    private float width;
    private float height;
    private float x;
    private float y;
    private float density;
    private String[] regionsWalking;
    private String[] regionsEating;
    private int lives ;

    EnemyDataType(float width, float height, float x, float y, float density, String[] regionsWalking, String[] regionsEating, int lives) {
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
        this.density = density;
        this.regionsWalking = regionsWalking;
        this.regionsEating = regionsEating;
        this.lives = lives;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public int getLives() {return lives;}

    public void setLives(int lives) { this.lives = lives;}

    public float getDensity() {
        return density;
    }

    public String[] getRegionsWalking() {
        return regionsWalking;
    }

    public String[] getRegionsEating() {
        return regionsEating;
    }
}