package com.mygdx.game.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.enums.PlantDataType;
import com.mygdx.game.stages.GameStage;
import com.mygdx.game.utils.Constants;
import com.badlogic.gdx.graphics.GL20;

public class GameScreen extends AbstractGameScreen {

    private GameStage stage;
    private Sprite peasButton;
    private Sprite nutButton;
    private Sprite pepperButton;

  //  SpriteBatch batch;

    private long plantTime;

    public GameScreen(Game game) {
        super(game);
        stage = new GameStage();
        plantTime = 0;
    }

    @Override
    public void render(float delta) {

        super.render(delta);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if (stage.gameOver)
            game.setScreen(new MenuScreen(game));

        batch.begin();
        batch.draw(new Texture(Gdx.files.internal(Constants.BACKGROUND_IMAGE_PATH)),0,0,Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        peasButton.draw(batch);
        nutButton.draw(batch);
        pepperButton.draw(batch);
        batch.end();
        stage.draw();
        stage.act(delta);

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void show() {

        super.show();

        peasButton = new Sprite(new Texture(Gdx.files.internal("images/button2.png"))) {
            {
                setX(30);
                setY(400);
                setSize(50,50);
            }
        };

        nutButton = new Sprite(new Texture(Gdx.files.internal("images/nut_button.png"))) {
            {
                setX(100);
                setY(400);
                setSize(50,50);
            }
        };


        pepperButton = new Sprite(new Texture(Gdx.files.internal("images/pep_button.png"))) {
            {
                setX(170);
                setY(400);
                setSize(50,50);
            }
        };

        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override

            public boolean touchDown(int screenX, int screenY, int pointer, int btn) {

                screenY = Constants.APP_HEIGHT - screenY;

                if (peasButton.getBoundingRectangle().contains(screenX, screenY)) {
                    Constants.peasIsSelected = !Constants.peasIsSelected;
                    return true;

                } else if (Constants.peasIsSelected) {

                    Constants.coordinatePeasIsSelected = true;
                    Constants.plant_X = screenX / 33;
                    Constants.plant_Y = screenY / 48;
                }

                if (nutButton.getBoundingRectangle().contains(screenX, screenY)) {
                    Constants.nutIsSelected = !Constants.nutIsSelected;
                    return true;

                } else if (Constants.nutIsSelected) {

                    Constants.coordinateNutIsSelected = true;
                    Constants.plant_X = screenX / 33;
                    Constants.plant_Y = screenY / 48;
                }

                if (pepperButton.getBoundingRectangle().contains(screenX, screenY)) {
                    Constants.pepperIsSelected = !Constants.pepperIsSelected;
                    return true;

                } else if (Constants.pepperIsSelected) {

                    Constants.coordinatePepperIsSelected = true;
                    Constants.plant_X = screenX / 33;
                    Constants.plant_Y = screenY / 48;
                }
                return false;

            }
        });


    }

    @Override
    public void hide() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }

}