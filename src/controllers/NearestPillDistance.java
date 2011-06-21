package controllers;

import core.GameStateInterface;
import core.Node;

import java.awt.*;

public class NearestPillDistance implements NodeScore {
    private static final int max = Integer.MAX_VALUE;
    public Node closest = null;

    public double score(GameStateInterface gs, Node node) {
        int minDist = max;
        for (Node n : gs.getMaze().getPills()) {
            if (gs.getPills().get(n.pillIndex)) {
                if (gs.getMaze().dist(node, n) < minDist) {
                    minDist = gs.getMaze().dist(node, n);
                    closest = n;
                }
            }
        }

        if (closest != null) {
            closest.col = Color.green;
        }
        return minDist;
    }
}