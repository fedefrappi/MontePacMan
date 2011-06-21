package core;

import java.awt.*;
import java.util.ArrayList;

public class Node implements Constants {
    public static final int PILL = 10;
    public static final int POWER_PILL = 50;

    public int ix;
    public Node[] next;
    public int pill;
    public double junctionDist;
    public final int x;
    public final int y;
    public int nodeIndex;
    public int pillIndex;
    public int powerIndex;
    public ArrayList<Node> adj;
    public Color col = Color.black;

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
        adj = new ArrayList<Node>();
        pillIndex = -1;
        powerIndex = -1;
    }

    public Node(int x, int y, int ix) {
        this.x = x;
        this.y = y;
        this.ix = ix;
    }

    public Node[] succ() {
        return next;
    }

    public void findNext(Node[][] ng, int w) {
        int n = nNext(ng, w);

        next = new Node[n];

        Node[] dirs = new Node[4];
        int ix = 0;

        for (int i = 0; i < dirs.length; i++) {
            Node node = g(ng, x + dx[i], y + dy[i], w);
            dirs[i] = node;

            if (node != null) {
                next[ix++] = node;
            }
        }
    }

    int nNext(Node[][] ng, int w) {
        int n = f(ng, x + 1, y, w) + f(ng, x - 1, y, w) + f(ng, x, y + 1, w) + f(ng, x, y - 1, w);
        return n;
    }

    private Node g(Node[][] ng, int x, int y, int w) {
        try {
            return ng[(x + w) % w][y];
        } catch (Exception e) {
        }
        return null;
    }

    private int f(Node[][] ng, int x, int y, int w) {
        try {
            if (ng[(x + w) % w][y] != null) {

                return 1;
            }
        } catch (Exception e) {
        }

        return 0;
    }
}