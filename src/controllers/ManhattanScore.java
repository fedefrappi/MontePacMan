package controllers;

import core.GameStateInterface;
import core.Node;

public class ManhattanScore implements NodeScore {
    public double score(GameStateInterface gs, Node node) {
        Node pac = gs.getPacman().current;
        return Math.abs(node.x - pac.x) + Math.abs(node.y - pac.y);
    }
}
