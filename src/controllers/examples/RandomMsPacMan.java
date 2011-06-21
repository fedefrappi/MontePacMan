package controllers.examples;

import competition.MsPacManController;
import core.Constants;
import core.GameStateInterface;

public class RandomMsPacMan implements MsPacManController, Constants {
    public int getAction(GameStateInterface gs) {
        return rand.nextInt(dx.length);
    }
}