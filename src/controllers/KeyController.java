package controllers;


import competition.MsPacManController;
import core.Constants;
import core.GameStateInterface;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyController extends KeyAdapter implements Constants, MsPacManController {
    private static final int noKey = -1;
    private int key = noKey;

    public int getAction(GameStateInterface gs) {
        return getDirection();
    }

    int getDirection() {
        if (key == KeyEvent.VK_DOWN) {
            return DOWN;
        }
        if (key == KeyEvent.VK_UP) {
            return UP;
        }
        if (key == KeyEvent.VK_RIGHT) {
            return RIGHT;
        }
        if (key == KeyEvent.VK_LEFT) {
            return LEFT;
        }
        return NEUTRAL;
    }

    public void keyPressed(KeyEvent e) {
        key = e.getKeyCode();
    }
}