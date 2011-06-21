package controllers;

import core.GameStateInterface;
import core.Node;

public class PathScore implements NodeScore {
    public double score(GameStateInterface gs, Node node) {
        return gs.getMaze().dist(gs.getPacman().current, node);
    }
}
