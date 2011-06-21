package core;

import core.maze.Level;
import core.maze.Maze;

public class GhostsSerial {
    private final int edibleTime;
    private final int current;
    private final int previous;
    private final int curDir;
    private final int delay;
    private final int delayCounter;
    private final int returnNode;

    private GhostsSerial(Ghosts gs) {
        edibleTime = gs.edibleTime;
        current = gs.current.nodeIndex;
        previous = safeIndex(gs.previous);
        curDir = gs.curDir;
        delay = gs.delay;
        delayCounter = gs.delayCounter;
        returnNode = safeIndex(gs.returnNode);
    }

    private int safeIndex(Node node) {
        if (node == null) {
            return -1;
        } else {
            return node.nodeIndex;
        }
    }

    private Node getNode(Maze maze, int index) {
        if (index == -1) {
            return null;
        } else {
            return maze.getNode(index);
        }
    }

    public static GhostsSerial[] getGhostStateSerial(Ghosts[] gs) {
        GhostsSerial[] gss = new GhostsSerial[gs.length];
        for (int i = 0; i < gs.length; i++) {

            gss[i] = new GhostsSerial(gs[i]);
        }
        return gss;
    }

    public static Ghosts[] getGhostState(GhostsSerial[] gss, int level) {
        Ghosts[] gs = new Ghosts[gss.length];
        for (int i = 0; i < gss.length; i++) {

            gs[i] = gss[i].getGhostState(level);
        }
        return gs;
    }

    Ghosts getGhostState(int level) {
        Maze maze = Level.getMaze(level);
        Ghosts gs = new Ghosts();
        gs.edibleTime = edibleTime;
        gs.current = getNode(maze, current);
        gs.previous = getNode(maze, previous);
        gs.curDir = curDir;
        gs.delay = delay;
        gs.delayCounter = delayCounter;
        gs.returnNode = getNode(maze, returnNode);
        return gs;
    }
}