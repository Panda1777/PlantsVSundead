package com.mygdx.game.utils;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.box2D.BulletUserData;
import com.mygdx.game.box2D.EnemyUserData;
import com.mygdx.game.box2D.GroundUserData;
import com.mygdx.game.box2D.PlantUserData;
import com.mygdx.game.enums.EnemyDataType;

public class WorldUtils {

    public static World createWorld() {
        return new World(Constants.WORLD_GRAVITY, true);
    }

    public static Body createGround(World world) {

        BodyDef bodyDef = new BodyDef();
        Body body = world.createBody(bodyDef);
        PolygonShape shape = new PolygonShape();
        bodyDef.position.set(new Vector2(Constants.GROUND_X, Constants.GROUND_Y));
        shape.setAsBox(Constants.GROUND_WIDTH, Constants.GROUND_HEIGHT);
        body.createFixture(shape, Constants.GROUND_DENSITY);
        body.setUserData(new GroundUserData());
        shape.dispose();
        return body;
    }

    public static Body createPlant(World world, float x, float y) {

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(new Vector2(x, y));//Constants.plant_X, Constants.plant_Y));
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(Constants.PLANT_WIDTH, Constants.PLANT_HEIGHT);
        Body body = world.createBody(bodyDef);
        //body.setGravityScale(Constants.RUNNER_GRAVITY_SCALE);
        body.createFixture(shape, Constants.RUNNER_DENSITY);
        body.resetMassData();
        body.setUserData(new PlantUserData(Constants.PLANT_WIDTH, Constants.PLANT_HEIGHT));
        shape.dispose();
        return body;

    }

    public static Body createEnemy(World world, float height) {

        EnemyDataType enemyType = RandomUtils.getRandomEnemyType();
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(new Vector2(enemyType.getX(), height));//enemyType.getY()));
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(enemyType.getWidth(), enemyType.getHeight());
        Body body = world.createBody(bodyDef);
        body.createFixture(shape, enemyType.getDensity());
        body.resetMassData();
        EnemyUserData userData = new EnemyUserData(enemyType.getWidth(), enemyType.getHeight());
        body.setUserData(userData);
        shape.dispose();
        return body;
    }

    public static Body createBullet(World world, float x, float y) {

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.KinematicBody;
        bodyDef.position.set(new Vector2(x, y));
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(Constants.BULLET_WIDTH, Constants.BULLET_HEIGHT);
        Body body = world.createBody(bodyDef);
        body.createFixture(shape, Constants.BULLET_DENSITY);
        body.resetMassData();
        body.setUserData(new BulletUserData(Constants.BULLET_WIDTH, Constants.BULLET_HEIGHT));
        body.setBullet(true);
        shape.dispose();
        return body;
    }
}
