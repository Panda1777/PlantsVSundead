package com.mygdx.game.actors;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.mygdx.game.enums.PlantDataType;
import com.mygdx.game.utils.Constants;

public class Pepper extends Plant {

    private TextureRegion texture;
    public long time;

    public Pepper(Body body, PlantDataType plantDataType) {

        super(body, plantDataType);

        TextureAtlas textureAtlas = new TextureAtlas(Constants.PLANT_ATLAS_PATH);
        texture = textureAtlas.findRegion(Constants.PEPPER_REGION_NAME);

        lives = 0;

    }

    @Override
    public void setTime(long time) { this.time = time;}
    @Override
    public long getTime() { return time; }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

        // JOptionPane.showMessageDialog(null, screenRectangle.y);
        batch.draw(texture, x * 33 - 15, y * 48 - 30, 40, 50);
    }

}
