package controllers;

import core.GameStateInterface;
import core.Node;

public class AimPoint implements NodeScore {
    private final int x;
    private final int y;

    public AimPoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public double score(GameStateInterface gs, Node node) {
        return Math.abs(node.x - x) + Math.abs(node.y - y);
    }
}