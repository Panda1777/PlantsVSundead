package com.mygdx.game.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.mygdx.game.box2D.PlantUserData;
import com.mygdx.game.enums.PlantDataType;
import com.mygdx.game.utils.Constants;

import javax.swing.*;

public abstract class Plant extends GameActor {

    Body plantBody;
    boolean toDelete;

    public int lives;
    public int maxLives;
    public boolean alive = true;

    float x;
    float y;

    private PlantDataType plantDataType;

    public Plant(Body body, PlantDataType plantDataType) {
        super(body);
        plantBody = body;
        toDelete = false;
        this.plantDataType = plantDataType;
    }

    @Override
    public void setX(float x) {
        this.x = x;
    }

    @Override
    public void setY(float y) {
        this.y = y;
    }

    @Override
    public float getX() {
        return x;
    }

    @Override
    public float getY() {
        return y;
    }

    @Override
    public PlantUserData getUserData() {
        return (PlantUserData) userData;
    }

    public Body getBody() {
        return plantBody;
    }

    public void setToDelete() {
        toDelete = true;
    }

    public boolean getToDelete() {
        return toDelete;
    }

    public boolean isAlive() {
        return lives > 0;
    }

    public int getAttack() {
        lives--;
        return lives;
    }

    public int getLives() {
        return lives;
    }


    @Override
    public void draw(Batch batch, float parentAlpha) {

        super.draw(batch, parentAlpha);

    }

    public abstract void setTime(long time);
    public abstract long getTime();

    public PlantDataType getTypePlant() { return plantDataType; }
}