package com.mygdx.game.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.mygdx.game.box2D.BulletUserData;
import com.mygdx.game.box2D.UserData;
import com.mygdx.game.utils.Constants;

import javax.swing.*;


public class Bullet extends GameActor {

    Body bulletBody;

    boolean toDelete;

    private TextureRegion bulletTexture;
    private float x;
    private float y;

    public Bullet(Body body, float x, float y) {
        super(body);
        bulletBody = body;
        toDelete = false;


        TextureAtlas textureAtlas = new TextureAtlas(Constants.PLANT_ATLAS_PATH);

        bulletTexture = textureAtlas.findRegion(Constants.BULLET_REGION_NAME);
        this.x = x;
        this.y = y;
    }


    public Body getBody(){ return bulletBody; }
    public void setToDelete(){ toDelete = true; }
    public boolean getToDelete(){ return toDelete; }

    @Override
    public BulletUserData getUserData() {
        return (BulletUserData) userData;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        body.setLinearVelocity(getUserData().getLinearVelocity());
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

       // JOptionPane.showMessageDialog(null, screenRectangle.y);
        batch.draw(bulletTexture, (screenRectangle.x - (screenRectangle.width * 0.1f)),
                y * 48 + 8, 20, 20);
    }

}
