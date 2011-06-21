package controllers;

import core.GameStateInterface;
import core.Node;

public class EuclideanScore implements NodeScore {
    public double score(GameStateInterface gs, Node node) {

        Node pac = gs.getPacman().current;
        return Math.sqrt(sqr(node.x - pac.x) + sqr(node.y - pac.y));
    }

    private static double sqr(double x) {
        return x * x;
    }

}