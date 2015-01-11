package com.mygdx.game.box2D;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.enums.UserDataType;
import com.mygdx.game.utils.Constants;


public class PlantUserData extends UserData {

    private final Vector2 runningPosition = new Vector2(Constants.plant_X, Constants.plant_Y);
    private final Vector2 dodgePosition = new Vector2(Constants.RUNNER_DODGE_X, Constants.RUNNER_DODGE_Y);
    //private Vector2 jumpingLinearImpulse;

    public PlantUserData(float width, float height) {
        super(width, height);
        //jumpingLinearImpulse = Constants.RUNNER_JUMPING_LINEAR_IMPULSE;
        userDataType = UserDataType.PLANT;
    }


    public float getDodgeAngle() {
        // In radians
        return (float) (-90f * (Math.PI / 180f));
    }

    public Vector2 getDodgePosition() {
        return dodgePosition;
    }

    public float getHitAngularImpulse() {
        return Constants.RUNNER_HIT_ANGULAR_IMPULSE;
    }

    public Vector2 getRunningPosition() {
        return runningPosition;
    }
}