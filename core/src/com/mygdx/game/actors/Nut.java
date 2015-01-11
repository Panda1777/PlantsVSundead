package com.mygdx.game.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.mygdx.game.enums.PlantDataType;
import com.mygdx.game.utils.Constants;

public class Nut extends Plant {

    private Animation standingAnimation1;
    private Animation standingAnimation2;
    private Animation standingAnimation3;
    private float stateTime;

    public Nut(Body body, PlantDataType plantDataType) {

        super(body, plantDataType);
        lives = 7;
        maxLives = lives;

        TextureAtlas textureAtlas = new TextureAtlas(Constants.PLANT_ATLAS_PATH);
        TextureRegion[] runningFrames;

        runningFrames = new TextureRegion[Constants.NUT1_STANDING_REGION_NAME.length];
        for (int i = 0; i < Constants.NUT1_STANDING_REGION_NAME.length; i++) {
            String path = Constants.NUT1_STANDING_REGION_NAME[i];
            runningFrames[i] = textureAtlas.findRegion(path);
        }
        standingAnimation1 = new Animation(0.1f, runningFrames);
        runningFrames = new TextureRegion[Constants.NUT2_STANDING_REGION_NAME.length];
        for (int i = 0; i < Constants.NUT2_STANDING_REGION_NAME.length; i++) {
            String path = Constants.NUT2_STANDING_REGION_NAME[i];
            runningFrames[i] = textureAtlas.findRegion(path);
        }
        standingAnimation2 = new Animation(0.1f, runningFrames);
        runningFrames = new TextureRegion[Constants.NUT3_STANDING_REGION_NAME.length];
        for (int i = 0; i < Constants.NUT3_STANDING_REGION_NAME.length; i++) {
            String path = Constants.NUT3_STANDING_REGION_NAME[i];
            runningFrames[i] = textureAtlas.findRegion(path);
        }
        standingAnimation3 = new Animation(0.1f, runningFrames);
        stateTime = 0f;

    }


    @Override
    public void draw(Batch batch, float parentAlpha) {

        super.draw(batch, parentAlpha);
        // float x = screenRectangle.x - (screenRectangle.width * 0.1f);
        // float y = screenRectangle.y;
        stateTime += Gdx.graphics.getDeltaTime() / 2;

        if (maxLives / 3 * 2 < lives && lives <= maxLives)
            batch.draw(standingAnimation1.getKeyFrame(stateTime, true), x * 33 - 30, y * 48 - 30, 40, 50);
        if (maxLives / 3 <= lives && lives <= maxLives / 3 * 2)
            batch.draw(standingAnimation2.getKeyFrame(stateTime, true), x * 33 - 30, y * 48 - 30, 40, 50);
        if (0 <= lives && lives < maxLives / 3)
            batch.draw(standingAnimation3.getKeyFrame(stateTime, true), x * 33 - 30, y * 48 - 30, 40, 50);

    }

    @Override
    public void setTime(long time) {}
    @Override
    public long getTime() { return -1;}
}
