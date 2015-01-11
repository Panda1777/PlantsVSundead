package com.mygdx.game.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.TimeUtils;
import com.mygdx.game.utils.Constants;

public class MenuScreen extends AbstractGameScreen{

    Sprite newGameButton;
    Sprite exitButton;

    public MenuScreen(Game game) {
        super(game);
    }


    public void render (float delta) {

        super.render(delta);
        batch.begin();
        batch.draw(new Texture(Gdx.files.internal(Constants.BACKGROUND2_IMAGE_PATH)),0,0,Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        newGameButton.draw(batch);
        exitButton.draw(batch);
        batch.end();

    }

    public void resize (int width, int height) {}

    public void show () {

        super.show();
        newGameButton = new Sprite(new Texture(Gdx.files.internal("images/newGameButton.png"))) {{
            setX(30);
            setY(400);
        }};
        exitButton = new Sprite(new Texture(Gdx.files.internal("images/exitButton.png"))) {{
            setX(30);
            setY(350);
        }
        };

        Gdx.input.setInputProcessor(new InputAdapter() {

            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int btn) {
                screenY = Constants.APP_HEIGHT - screenY;


                if (newGameButton.getBoundingRectangle().contains(screenX, screenY)) {
                    game.setScreen(new GameScreen(game));
                    return true;
                }

                if (exitButton.getBoundingRectangle().contains(screenX, screenY)) {
                    Gdx.app.exit();
                    return true;
                }

                return false;

            }
        });



    }

    public void hide () {}

    public void pause () {}

    public void resume () {}

    public void dispose () {}


}
