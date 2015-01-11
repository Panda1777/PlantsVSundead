package com.mygdx.game.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.mygdx.game.box2D.EnemyUserData;
import com.mygdx.game.utils.Constants;

public class Enemy extends GameActor {

    private Animation animationFlying;
    private Animation animationEating;
    private float stateTime;

    private int lives;

    Body body;
    boolean toDelete;

    private boolean isEating;

    private float y;

    private long lastTimeAttack;
    private Bullet lastBulletAttack;


    public Enemy(Body body, float y) {
        super(body);
        this.body = body;
        toDelete = false;
        this.lastTimeAttack = 300000000;
        this.lastBulletAttack = null;
        lives = 5;
        this.isEating = false;
        this.y = y;

        TextureAtlas textureAtlas = new TextureAtlas(Constants.ENEMY_ATLAS_PATH);
        TextureRegion[] flyingFrames = new TextureRegion[Constants.ENEMY_WAKING_REGION_NAMES.length];
        for (int i = 0; i < Constants.ENEMY_WAKING_REGION_NAMES.length; i++) {
            String path = Constants.ENEMY_WAKING_REGION_NAMES[i];
            flyingFrames[i] = textureAtlas.findRegion(path);
        }
        animationFlying = new Animation(0.1f, flyingFrames);

        TextureRegion[] eatingFrames = new TextureRegion[Constants.ENEMY_EATING_REGION_NAMES.length];
        for (int i = 0; i < Constants.ENEMY_EATING_REGION_NAMES.length; i++) {
            String path = Constants.ENEMY_EATING_REGION_NAMES[i];
            eatingFrames[i] = textureAtlas.findRegion(path);
        }
        animationEating = new Animation(0.1f, eatingFrames);

        stateTime = 0f;


    }

    public int getLives() {
        return lives;
    }
    public Body getBody(){return body;}
    public void setToDelete(){toDelete = true;}
    public boolean getToDelete(){return toDelete;}
    public boolean isAlive() { return lives > 0; }
    public void getAttack() { lives--; }
    public void setLastTimeAttack(long lastTimeAttack) { this.lastTimeAttack = lastTimeAttack; }
    public long getLastTimeAttack() {
        return lastTimeAttack;
    }
    public void setLastBulletAttack(Bullet lastBulletAttack) { this.lastBulletAttack = lastBulletAttack; }
    public Bullet getLastBulletAttack() {
        return lastBulletAttack;
    }

    /*public boolean isAlive (int lives) {
        if ((lives--)<=0) return false;
        return true;
    }*/

    @Override
    public EnemyUserData getUserData() {
        return (EnemyUserData) userData;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        body.setLinearVelocity(getUserData().getLinearVelocity());
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        stateTime += Gdx.graphics.getDeltaTime();
        if (isEating) {
            batch.draw(animationEating.getKeyFrame(stateTime, true), -40 + (screenRectangle.x - (screenRectangle.width * 0.1f)),
                    y * 48 - 30, 60, 80);
        } else {
            batch.draw(animationFlying.getKeyFrame(stateTime, true), -40 + (screenRectangle.x - (screenRectangle.width * 0.1f)),
                    y * 48 - 30, 60, 80);
        }
    }

    @Override
    public void setY(float y) {
        this.y = y;
    }

    @Override
    public float getY() {
        return y;
    }

    public void setIsEating() {
        isEating = true;
    }

    public boolean getIsEating() {
        return isEating;
    }

    public void setIsNotEating() {
        isEating = false;
    }
}