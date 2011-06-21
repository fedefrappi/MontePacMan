package controllers.examples;

import competition.GhostsController;
import controllers.*;
import core.Constants;
import core.GameStateInterface;
import core.Ghosts;
import core.Node;
import core.utilities.Utilities;

import java.util.ArrayList;

public class LegacyTeam implements GhostsController, Constants {
    private final int[] dirs;
    private final NodeScore[] scorers;
    private final ArrayList<Node> options;

    public LegacyTeam() {
        this.dirs = new int[nGhosts];
        options = new ArrayList<Node>();
        scorers = new NodeScore[]{
                new PathScore(), new EuclideanScore(),
                new ManhattanScore(), new RandScore(),
        };
    }

    public int[] getActions(GameStateInterface gs) {
        for (int i = 0; i < dirs.length; i++) {
            Ghosts gh = gs.getGhosts()[i];
            options.clear();
            for (Node n : gh.current.adj) {
                if (!n.equals(gh.previous)) options.add(n);
            }
            dirs[i] = Utilities.getMinDir(options, gh.current, scorers[i], gs);
        }
        return dirs;
    }
}