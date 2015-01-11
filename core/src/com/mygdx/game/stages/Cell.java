package com.mygdx.game.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.game.utils.Constants;

public class Cell extends Actor {

    private boolean isEmpty;
    private float x;
    private float y;

    private boolean inFire;
    private Animation standingAnimation;
    private float stateTime;

    public Cell(float x, float y) {

        this.x = x;
        this.y = y;
        isEmpty = true;
        this.inFire = false;

        TextureAtlas textureAtlas = new TextureAtlas(Constants.PLANT_ATLAS_PATH);
        TextureRegion[] runningFrames = new TextureRegion[Constants.FIRE_REGION_NAME.length];
        for (int i = 0; i < Constants.FIRE_REGION_NAME.length; i++) {
            String path = Constants.FIRE_REGION_NAME[i];
            runningFrames[i] = textureAtlas.findRegion(path);
        }
        standingAnimation = new Animation(0.1f, runningFrames);
        stateTime = 0f;
    }

    public void setNotIsEmpty() {
        isEmpty = false;
    }
    public void setIsEmpty() {
        isEmpty = true;
    }
    public boolean getIsEmpty() {
        return isEmpty;
    }
    public float getX() {
        return x;
    }
    public float getY() {
        return y;
    }
    public void setInFire() { inFire = true; }
    public void setNotInFire() { inFire = false; }
    public boolean getInFire() { return inFire; }

    @Override
    public void draw(Batch batch, float parentAlpha) {

        stateTime += Gdx.graphics.getDeltaTime() / 2;
        if (inFire)
            batch.draw(standingAnimation.getKeyFrame(stateTime, true), x * 33 - 30, y * 48 - 30, 70, 70);
    }

}
