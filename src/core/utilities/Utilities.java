package core.utilities;

import controllers.NodeScore;
import core.*;
import core.maze.MazeInterface;
import core.Node;

import java.util.ArrayList;

public class Utilities implements Constants {
    public static Node getMin(ArrayList<Node> nodes, NodeScore f, GameStateInterface gs) {
        double best = Double.MAX_VALUE;

        Node sel = null;
        for (Node n : nodes) {
            if (f.score(gs, n) < best) {
                best = f.score(gs, n);
                sel = n;
            }
        }
        return sel;
    }

    public static int getMinDir(ArrayList<Node> nodes, Node cur, NodeScore f, GameStateInterface gs) {
        Node target = getMin(nodes, f, gs);

        return getWrappedDirection(cur, target, gs.getMaze());
    }

    public static int getClosestDir(ArrayList<Node> nodes, Node cur, NodeScore f, GameState gs) {
        Node target = getMin(nodes, f, gs);

        return getWrappedDirection(cur, target, gs.getMaze());
    }

    public static Node getClosest(ArrayList<Node> nodes, Node target, MazeInterface maze) {

        if (target == null) {
            System.out.println("Warning: null target in Utilities.getClosest()");
            return nodes.get(rand.nextInt(nodes.size()));
        }
        double best = Double.MAX_VALUE;

        Node sel = null;
        for (Node n : nodes) {
            int d = maze.dist(n, target);
            if (d < best) {
                best = d;
                sel = n;
            }
        }
        return sel;
    }

    //bug fix thanks to Nicolai Czempin
    public static int getDirection(Node from, Node to) {
        int xAbs = Math.abs(to.x - from.x);
        int yAbs = Math.abs(to.y - from.y);

        int xDiff = 0;
        int yDiff = 0;

        if (xAbs > yAbs) {
        xDiff = sgn(to.x - from.x);
        } else {
        yDiff = sgn(to.y - from.y);
        }

        for (int i = 0; i < dx.length; i++) {
           if (dx[i] == xDiff && dy[i] == yDiff) {
                return i;
             }
         }

        System.out.println("Error in Util.getDirection");
        throw new RuntimeException("Error in getDirection " + from + " : " + to);
    }

    public static int getWrappedDirection(Node a, Node b, MazeInterface maze) {
        int w = maze.getWidth();
        int h = maze.getHeight();
        for (int i = 0; i < dx.length; i++) {
            if (
                    ((a.x + dx[i] + w) % w == b.x) &&
                            ((a.y + dy[i] + h) % h == b.y)
                    ) {
                return i;
            }
        }

        System.out.println("Non-adjacent nodes in getWrappedDirection");
        return NEUTRAL;
    }

    private static int sgn(int x) {
        if (x < 0) return -1;
        if (x > 0) return 1;
        return 0;
    }

    public static int manhattan(Node a, Node b) {
        return Math.abs(a.x - b.x) + Math.abs(a.y - b.y);
    }
}
