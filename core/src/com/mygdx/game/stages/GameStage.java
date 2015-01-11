package com.mygdx.game.stages;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.TimeUtils;
import com.mygdx.game.actors.*;
import com.mygdx.game.enums.PlantDataType;
import com.mygdx.game.utils.BodyUtils;
import com.mygdx.game.utils.Constants;
import com.mygdx.game.utils.WorldUtils;
import com.badlogic.gdx.utils.viewport.ScalingViewport;

import javax.swing.*;
import java.util.Random;

public class GameStage extends Stage implements ContactListener{

    private static final int VIEWPORT_WIDTH = Constants.APP_WIDTH;
    private static final int VIEWPORT_HEIGHT = Constants.APP_HEIGHT;

    public boolean gameOver = false;

    private World world;
    private Ground ground;
//    private Plant plant;
    private Array<Plant> plants = new Array<Plant>();
    private Array<Bullet> bullets = new Array<Bullet>();
    private Array<Enemy> enemies = new Array<Enemy>();

    private boolean planted;

    private final float TIME_STEP = 1 / 300f;
    private float accumulator = 0f;

    private OrthographicCamera camera;
    private Box2DDebugRenderer renderer;

    private Field field;

    private Vector3 touchPoint;

    public GameStage() {

        super(new ScalingViewport(Scaling.stretch, VIEWPORT_WIDTH, VIEWPORT_HEIGHT, new OrthographicCamera(VIEWPORT_WIDTH, VIEWPORT_HEIGHT)));
        setUpWorld();
        setupCamera();
        renderer = new Box2DDebugRenderer();
    }

    private void setUpWorld() {
        world = WorldUtils.createWorld();
        world.setContactListener(this);
        field = new Field(world);
        Cell[][] cells = field.getCells();
        for (Cell[] cellLines : cells)
            for (Cell cell : cellLines)
                addActor(cell);
        createEnemy(Constants.ENEMY_Y[randomY()]);
        touchPoint = new Vector3();

    }

    private void setCellsInFire(float x, float y) {
        Cell[][] cells = field.getCells();

        for (Cell[] cellLine : cells)
            for (Cell cell : cellLine)
                if (cell.getY() == y && cell.getX() != x)
                    cell.setInFire();
    }

    private void setCellsNotInFire(float x, float y) {
        Cell[][] cells = field.getCells();

        for (Cell[] cellLine : cells)
            for (Cell cell : cellLine)
                if (cell.getY() == y)
                    cell.setNotInFire();
    }

    private long lastSunTime;

    private void setUpPlants(float x, float y, PlantDataType plantDataType) {

        Cell cell = field.getCell(x, y);
        if (cell == null || !cell.getIsEmpty()) {
            return;
        }
        cell.setNotIsEmpty();
        x = cell.getX();
        y = cell.getY();
        Plant plant = null;

        if (plantDataType == PlantDataType.PEAS )
            plant = new Peas(WorldUtils.createPlant(world, x, y), plantDataType);
        if (plantDataType == PlantDataType.NUT)
            plant = new Nut(WorldUtils.createPlant(world, x, y), plantDataType);
        if (plantDataType == PlantDataType.PEPPER ) {
            plant = new Pepper(WorldUtils.createPlant(world, x, y), plantDataType);
            setCellsInFire(x, y);
        }

        plant.setX(x);
        plant.setY(y);
        plant.setTime(TimeUtils.nanoTime());
        plants.add(plant);
        addActor(plant);

        return;
    }

    private long lastEnemyTime;
    private void createEnemy(float height) {
        Enemy enemy = new Enemy(WorldUtils.createEnemy(world, height), height);
        enemy.getY();
        enemy.setY(height);
        addActor(enemy);
        lastEnemyTime = TimeUtils.nanoTime();
        enemies.add(enemy);
    }

    private void createBullet(float x, float y, Plant currentPlant) {

        Bullet bullet = new Bullet(WorldUtils.createBullet(world, x + 1f, y), x, y);
        addActor(bullet);
        currentPlant.setTime(TimeUtils.nanoTime());
        bullets.add(bullet);
    }

    private void setupCamera() {
        camera = new OrthographicCamera(VIEWPORT_WIDTH, VIEWPORT_HEIGHT);
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 3f);
        //camera.zoom = 10f;
        camera.update();
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        for (Enemy enemy : enemies) {
            if (!BodyUtils.bodyInBounds(enemy.getBody())) {
                JOptionPane.showMessageDialog(null, "GAME OVER");
                gameOver = true;
                enemy.setToDelete();
            }
        }

        for (Bullet bullet : bullets){
            if (!BodyUtils.bodyInBounds(bullet.getBody())) {
                bullet.setToDelete();
            }
        }

        for (Plant plant: plants){
            if (!BodyUtils.bodyInBounds(plant.getBody())) {
                plant.setToDelete();
            }
            if (plant.getTypePlant() == PlantDataType.PEPPER) {
                if (TimeUtils.nanoTime() - plant.getTime() > 1E9)
                    actPepper(plant);
            }
        }

        if ((TimeUtils.nanoTime() - lastEnemyTime) / 3 > 3E9)
            createEnemy(Constants.ENEMY_Y[randomY()]);

        if (Constants.peasIsSelected && Constants.coordinatePeasIsSelected){
            setUpPlants(Constants.plant_X, Constants.plant_Y, PlantDataType.PEAS);
            Constants.peasIsSelected = false;
            Constants.coordinatePeasIsSelected = false;
        }

        if (Constants.nutIsSelected && Constants.coordinateNutIsSelected){
            setUpPlants(Constants.plant_X, Constants.plant_Y, PlantDataType.NUT);
            Constants.nutIsSelected = false;
            Constants.coordinateNutIsSelected = false;
        }

        if (Constants.pepperIsSelected && Constants.coordinatePepperIsSelected){
            setUpPlants(Constants.plant_X, Constants.plant_Y, PlantDataType.PEPPER);
            Constants.pepperIsSelected = false;
            Constants.coordinatePepperIsSelected = false;
        }

        for (Plant currentPlant : plants) {
            if (currentPlant.getTypePlant() == PlantDataType.PEAS && ((TimeUtils.nanoTime() - currentPlant.getTime()) / 2 > 1000000000) && enemyOnWay(currentPlant))
                createBullet(currentPlant.getX(), currentPlant.getY(), currentPlant);
        }

        deleteOnContact();

        accumulator += delta;
        while (accumulator >= delta) {
            world.step(TIME_STEP, 6, 2);
            accumulator -= TIME_STEP;
        }
    }

    private boolean enemyOnWay(Plant plant) {
        for (Enemy enemy : enemies) {
            if (plant.getY() == enemy.getY())
                return true;
        }
        return false;
    }

    private void actPepper(Plant pepper) {
        for (Enemy enemy : enemies) {
            if (pepper.getY() == enemy.getY())
                enemy.setToDelete();
        }
        setCellsNotInFire(pepper.getX(), pepper.getY());
        pepper.setToDelete();
    }

    private int randomY() {
        Random rnd = new Random();
        return rnd.nextInt(4);
    }

    private void deleteOnContact(){

        for (Bullet bullet : bullets){
            if (bullet.getToDelete()){
                bullet.getBody().setActive(false);
                world.destroyBody(bullet.getBody());
                bullets.removeValue(bullet, false);
            }
        }
        for (Enemy enemy : enemies){
            if (enemy.getToDelete()){
                enemy.getBody().setActive(false);
                world.destroyBody(enemy.getBody());
                enemies.removeValue(enemy, false);
            }
        }

        for (Plant plant : plants){
            if (plant.getToDelete()){
                field.getCell(plant.getX(), plant.getY()).setIsEmpty();
                plant.getBody().setActive(false);
                world.destroyBody(plant.getBody());
                plants.removeValue(plant, false);
            }
        }
    }

    @Override
    public void draw() {
        super.draw();
        renderer.render(world, camera.combined);
    }


    @Override
    public boolean touchDown(int x, int y, int pointer, int button) {
        translateScreenToWorldCoordinates(x, y);
            //for (plants)
            if (touchPoint.x >= 30 && touchPoint.x <= 80 && touchPoint.y >= 400 && touchPoint.y <= 450)
                return super.touchDown(x, y, pointer, button);
        return  false;
    }

    private void translateScreenToWorldCoordinates(int x, int y) {
        getCamera().unproject(touchPoint.set(x, y, 0));
    }

    private void eatPlant(Enemy enemy, Plant plant) {

        if (TimeUtils.nanoTime() - enemy.getLastTimeAttack() > 3E9) {
           // JOptionPane.showMessageDialog(null, plant.getLives());
            if (plant.isAlive()) {
                plant.getAttack();
            }
            else {
                enemy.setIsNotEating();
                plant.setToDelete();
            }
            enemy.setLastTimeAttack(TimeUtils.nanoTime());
        }

    }

    private void eatEnemy(Enemy enemy, Bullet bullet) {
        if (bullet != enemy.getLastBulletAttack()) {
       //     JOptionPane.showMessageDialog(null, enemy.getLives());
            if (enemy.isAlive()) {
                enemy.getAttack();
            } else {
                enemy.setToDelete();
            }
            enemy.setLastBulletAttack(bullet);
        }

    }


    @Override
    public void beginContact(Contact contact) {

    }

    @Override
    public void endContact(Contact contact) {
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

        Body a = contact.getFixtureA().getBody();
        Body b = contact.getFixtureB().getBody();


        if (BodyUtils.bodyIsRunner(a) && BodyUtils.bodyIsEnemy(b) || BodyUtils.bodyIsEnemy(a) && BodyUtils.bodyIsRunner(b)) {
            b.setLinearVelocity(new Vector2(0, 0));
            Enemy enemy = null;
            Plant plant = null;
            for (Enemy currentEnemy : enemies)
                if (currentEnemy.getBody() == b || currentEnemy.getBody() == a) {
                    enemy = currentEnemy;
                    break;
                }
            for (Plant currentPlant : plants)
                if (currentPlant.getBody() == a || currentPlant.getBody() == b) {
                    plant = currentPlant;
                    break;
                }
            if (enemy != null && plant != null) {
                enemy.setIsEating();
           //     JOptionPane.showMessageDialog(null, enemy.getIsEating());
                eatPlant(enemy, plant);
            }
        }

        if ((BodyUtils.bodyIsBullet(a) && BodyUtils.bodyIsEnemy(b))|| (BodyUtils.bodyIsBullet(b) && BodyUtils.bodyIsEnemy(a))) {
            Bullet bullet = null;
            for(Bullet currentBullet : bullets){
                if(currentBullet.getBody() == a || currentBullet.getBody() == b)
                    bullet = currentBullet;
            }
            for(Enemy enemy : enemies) {
              //  JOptionPane.showMessageDialog(null, enemy.getLives());
                if (enemy.getBody() == a || enemy.getBody() == b) {
                    eatEnemy(enemy, bullet);
                    bullet.setToDelete();
                    break;
                }
            }
        }

        if (BodyUtils.bodyIsRunner(a) && BodyUtils.bodyIsBullet(b) || BodyUtils.bodyIsRunner(b) && BodyUtils.bodyIsBullet(a)) {

        }

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}