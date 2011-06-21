package controllers.examples;

import competition.MsPacManController;
import controllers.NearestPillDistance;
import core.Constants;
import core.GameStateInterface;
import core.Node;
import core.utilities.Utilities;

public class SimplePillEatingMsPacMan implements MsPacManController, Constants {

    private final NearestPillDistance npd;

    public SimplePillEatingMsPacMan() {
        npd = new NearestPillDistance();
    }

    public int getAction(GameStateInterface gs) {

        gs = gs.copy();
        Node current = gs.getPacman().current;
        npd.score(gs, current);
        Node next = Utilities.getClosest(current.adj, npd.closest, gs.getMaze());
        int dir = Utilities.getWrappedDirection(current, next, gs.getMaze());
        return dir;
    }
}