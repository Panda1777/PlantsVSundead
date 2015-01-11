package com.mygdx.game.utils;

import com.badlogic.gdx.physics.box2d.Body;
import com.mygdx.game.box2D.UserData;
import com.mygdx.game.enums.UserDataType;

import javax.swing.*;

public class BodyUtils {

    public static boolean bodyIsRunner(Body body) {
        UserData userData = (UserData) body.getUserData();

        return userData != null && userData.getUserDataType() == UserDataType.PLANT;
    }

    public static boolean bodyIsGround(Body body) {
        UserData userData = (UserData) body.getUserData();

        return userData != null && userData.getUserDataType() == UserDataType.GROUND;
    }

    public static boolean bodyIsEnemy(Body body) {
        UserData userData = (UserData) body.getUserData();

        return userData != null && userData.getUserDataType() == UserDataType.ENEMY;
    }

    public static boolean bodyIsBullet(Body body) {
        UserData userData = (UserData) body.getUserData();

        return userData != null && userData.getUserDataType() == UserDataType.BULLET;
    }

    public static boolean bodyInBounds(Body body) {
        UserData userData = (UserData) body.getUserData();

        switch (userData.getUserDataType()) {
            case BULLET:
                return body.getPosition().x + userData.getWidth() / 2 < 23f;
            case ENEMY:
                //case PLANT:

                return body.getPosition().x + userData.getWidth() / 2 > 0;


        }

        return true;

    }
}