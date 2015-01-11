package com.mygdx.game.stages;

import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.box2D.BulletUserData;
import com.mygdx.game.utils.Constants;
import com.mygdx.game.utils.WorldUtils;

import javax.swing.*;
import java.util.ArrayList;

public class Field {

    private Cell[][] cells;

    private final float[] FIELD_X = new float[] { 2f, 4f, 6f, 8f, 10f, 12f, 14f, 16f, 18f, 20f, 22f };
    private final float[] FIELD_Y = new float[] { 1f, 3f, 5f, 7f };

    private final float CELL_WIDTH = 2f;
    private final float CELL_HEIGHT = 2f;

    private World world;

    public Field(World world) {

        this.world = world;
        cells = new Cell[FIELD_X.length][FIELD_Y.length];
        createField();
    }

    public Cell[][] getCells() {
        return cells;
    }

    private void createField() {

        for (int i = 0; i < FIELD_X.length; i++)
            for (int j = 0; j < FIELD_Y.length; j++) {
                cells[i][j] = new Cell(FIELD_X[i], FIELD_Y[j]);
            }
    }

    public Cell getCell(float x, float y) {

        int n = FIELD_Y.length;
        for (int i = 0; i < FIELD_X.length; i++)
            for (int j = 0; j < FIELD_Y.length; j++){

                if ((cells[i][j].getX() <= x && cells[i][j].getX() + CELL_WIDTH > x) && (cells[i][j].getY() <= y && cells[i][j].getY() + CELL_HEIGHT > y)) {
               //     JOptionPane.showMessageDialog(null, cells[i][j].getX() + " <= " + x + " < " + (cells[i][j].getX() + CELL_WIDTH) + "\n" +
                  //                                             cells[i][j].getY() + " <= " + y + " < " + (cells[i][j].getY() + CELL_HEIGHT) + "\n");
                    return cells[i][j];
                }
        }

        return null;
    }

}
