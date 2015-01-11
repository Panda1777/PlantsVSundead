package com.mygdx.game.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.mygdx.game.enums.PlantDataType;
import com.mygdx.game.utils.Constants;

public class Peas extends Plant {

    public long lastBulletTime;

    private Animation standingAnimation1;
    private float stateTime;

    public Peas(Body body, PlantDataType plantDataType) {
        super(body, plantDataType);
        lives = 3;

        TextureAtlas textureAtlas = new TextureAtlas(Constants.PLANT_ATLAS_PATH);
        TextureRegion[] runningFrames = new TextureRegion[Constants.PEAS_STANDING_REGION_NAME.length];
        for (int i = 0; i < Constants.PEAS_STANDING_REGION_NAME.length; i++) {
            String path = Constants.PEAS_STANDING_REGION_NAME[i];
            runningFrames[i] = textureAtlas.findRegion(path);
        }
        standingAnimation1 = new Animation(0.1f, runningFrames);
        stateTime = 0f;
    }

    @Override
    public void setTime(long lastBulletTime) {
        this.lastBulletTime = lastBulletTime;
    }
    @Override
    public long getTime() {
        return lastBulletTime;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {

        super.draw(batch, parentAlpha);
        // float x = screenRectangle.x - (screenRectangle.width * 0.1f);
        // float y = screenRectangle.y;
        stateTime += Gdx.graphics.getDeltaTime() / 2;

        batch.draw(standingAnimation1.getKeyFrame(stateTime, true), x * 33 - 30, y * 48 - 30, 40, 50);
    }


}
