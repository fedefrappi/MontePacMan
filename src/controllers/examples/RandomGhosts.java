package controllers.examples;

import competition.GhostsController;
import core.Constants;
import core.GameStateInterface;

public class RandomGhosts implements GhostsController, Constants {
    private final int[] dirs;

    public RandomGhosts() {

        this.dirs = new int[nGhosts];
    }

    public int[] getActions(GameStateInterface gs) {


        for (int i = 0; i < dirs.length; i++) {
            dirs[i] = rand.nextInt(dx.length);
        }
        return dirs;
    }
}
