package controllers;

import core.maze.MazeInterface;
import core.Node;

import java.util.ArrayList;

public class PathPlanner {

    private final MazeInterface maze;

    public PathPlanner(MazeInterface maze) {
        this.maze = maze;
    }

    public ArrayList<Node> getPath(Node from, Node to) {
        return getPath(from, to, new ArrayList<Node>());
    }

    private ArrayList<Node> getPath(Node from, Node to, ArrayList<Node> path) {
        if (from.equals(to))
            return path;
        else {
            Node next = getClosest(from.adj, to, maze);
            path.add(next);
            return getPath(next, to, path);
        }
    }

    private Node getClosest(ArrayList<Node> nodes, Node to, MazeInterface maze) {
        double best = Double.MAX_VALUE;

        Node sel = null;
        for (Node n : nodes) {
            if (maze.dist(n, to) < best) {
                best = maze.dist(n, to);
                sel = n;
            }
        }
        return sel;
    }
}

