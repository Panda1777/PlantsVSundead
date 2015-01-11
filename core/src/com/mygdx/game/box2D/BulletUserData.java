package com.mygdx.game.box2D;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.actors.Bullet;
import com.mygdx.game.enums.UserDataType;
import com.mygdx.game.utils.Constants;

public class BulletUserData extends UserData {

    private Vector2 linearVelocity;

    public BulletUserData(float width, float height) {

        super(width, height);
        userDataType = UserDataType.BULLET;
        linearVelocity = Constants.BULLET_LINEAR_VELOCITY;
    }

    public Vector2 getLinearVelocity() {
        return linearVelocity;
    }


}
