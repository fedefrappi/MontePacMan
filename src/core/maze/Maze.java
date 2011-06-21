package core.maze;

import core.Constants;
import core.Node;

import java.util.ArrayList;

public class Maze implements MazeInterface, Constants {
    private final ArrayList<Node> powers;
    private final ArrayList<Node> pills;
    private final ArrayList<Node> map;
    private int w, h;
    private Node[][] na;
    private int[][] dist;
    private int number;
    private OldMaze maze;

    public Maze() {
        map = new ArrayList<Node>();
        pills = new ArrayList<Node>();
        powers = new ArrayList<Node>();
    }

    public Maze(ArrayList lines, int w, int h, int[] xx, int[] yy, int mazeNo) {
        map = new ArrayList<Node>();
        pills = new ArrayList<Node>();
        powers = new ArrayList<Node>();

        this.maze = new OldMaze(lines, w, h, xx, yy, mazeNo);
        processOldMaze();
    }

    public int dist(Node a, Node b) {
        return dist[a.nodeIndex][b.nodeIndex];
    }

    public Node ghostStart() {
        return getNode(57, 49);
    }

    public Node pacStart() {
        return getNode(57, 97);
    }

    void processOldMaze() {
        w = maze.w;
        h = maze.h;
        int nNodes = 0;

        number = maze.mazeNo - 1;
        int mag = 1;
        int powerIndex = 0;
        int pillIndex = 0;
        na = new Node[maze.w * mag][maze.h * mag];
        maze.reset();
        for (Node n : maze.na) {

            int x = n.x * mag;
            int y = n.y * mag;
            Node node = new Node(x, y);
            node.nodeIndex = nNodes;
            na[x][y] = node;
            map.add(node);
            nNodes++;
            if (n.pill == Node.PILL) {
                pills.add(node);
                node.pillIndex = pillIndex++;
            } else if (n.pill == Node.POWER_PILL) {
                powers.add(node);
                node.powerIndex = powerIndex++;
            }

        }
//        System.out.println("pillIndex: " + pillIndex);
//        System.out.println("powerIndex: " + powerIndex);
        addAdjacencies();
//        System.out.println("nNodes: " + nNodes + "\t " + map.size());
//        System.out.println("Setting distances");
        dist = maze.dist;

        int[] h = new int[5];
        for (Node n : map) {
            h[n.adj.size()]++;
        }
//        for (int i = 0; i < h.length; i++) {
//            System.out.println(i + "\t " + h[i]);
//        }
    }

    private void addAdjacencies() {
        for (Node n : map) {
            for (int i = 0; i < dx.length; i++) {
                if (dx[i] != 0 || dy[i] != 0) {
                    Node adj = getNode(n.x + dx[i], n.y + dy[i]);
                    if (adj != null) n.adj.add(adj);
                }
            }
        }
    }

    public Node getNode(int x, int y) {
        return na[(x + w) % w][(y + h) % h];
    }

    public Node getNode(int index) {
        return map.get(index);
    }

    public int getNumber() {
        return number;
    }

    class OldMaze implements Constants {
        public final ArrayList<Object> lines;
        public final int w;
        public final int h;
        int top, left;
        final int[] xx;
        final int[] yy;
        public Node[] na;
        Node[][] ng;
        public int[][] dist;
        final int pillSpace = 4;
        final ArrayList junctions;
        public final ArrayList<Node> pills;
        public final ArrayList<Node> power;
        public final int mazeNo;

        public OldMaze(ArrayList lines, int w, int h, int[] xx, int[] yy, int mazeNo) {
            this.lines = (ArrayList) lines.clone();
            this.w = w;
            this.h = h;
            this.xx = xx;
            this.yy = yy;
            junctions = new ArrayList();
            pills = new ArrayList<Node>();
            power = new ArrayList<Node>();
            this.mazeNo = mazeNo;
            makeGraph();
        }

        public void reset() {

            for (int i = 0; i < pills.size(); i++) {
                Node node = pills.get(i);
                node.pill = Node.PILL;
            }
            for (int i = 0; i < power.size(); i++) {
                Node node = power.get(i);
                node.pill = Node.POWER_PILL;
            }
        }

        private void makeGraph() {


            top = Integer.MAX_VALUE;
            left = Integer.MAX_VALUE;
            ng = new Node[w][h];

            int nNodes = 0;
            ArrayList<Node> nodes = new ArrayList<Node>();

            setLeftTop();

            for (int i = 0; i < lines.size(); i++) {

                Line line = (Line) lines.get(i);
                int x = line.x1;
                int y = line.y1;
                int dx = sgn(line.x2 - line.x1);
                int dy = sgn(line.y2 - line.y1);
                do {
                    if (ng[(x + w) % w][y] == null) {

                        Node node = new Node(x, y, nNodes++);
                        ng[(x + w) % w][y] = node;
                        nodes.add(node);
                        if (line.pill && pill(node)) {
                            node.pill = Node.PILL;
                            if (powerPill(node)) {
                                node.pill = Node.POWER_PILL;
                            }
                        }
                    }
                    x += dx;
                    y += dy;

                } while (line.in(x, y));
            }

            na = new Node[nNodes];

            for (int i = 0; i < nodes.size(); i++) {
                Node node = nodes.get(i);
                na[i] = node;
            }


            for (Node aNa : na) {
                aNa.findNext(ng, w);
            }


            dist = new int[nNodes][nNodes];
            for (int i = 0; i < nNodes; i++) {
                setMax(dist[i]);
                search(na, dist[i], i, 0);
            }


            for (int i = 0; i < nodes.size(); i++) {
                Node node = na[i];

                if (node.pill == Node.POWER_PILL) {
                    lines.add(node);
                    power.add(node);
                }
                if (node.pill == Node.PILL) {
                    lines.add(node);
                    pills.add(node);
                }
                if (node.next.length > 2) {
                    junctions.add(node);
                }
            }

            setJunctionDists();
        }

        public void setMax(int[] d) {
            for (int i = 0; i < d.length; i++) {
                d[i] = Integer.MAX_VALUE;
            }
        }

        public void search(Node[] g, int[] d, int curNode, int curDist) {
            if (curDist < d[curNode]) {
                d[curNode] = curDist;
                Node[] next = g[curNode].succ();
                for (int i = 0; i < next.length; i++) {
                    search(g, d, next[i].ix, curDist + 1);
                }
            }
        }

        private void setLeftTop() {
            for (int i = 0; i < lines.size(); i++) {
                Line line = (Line) lines.get(i);
                int x = line.x1;
                left = Math.min(left, x);
                int y = line.y1;
                top = Math.min(top, y);
            }
        }

        private void setJunctionDists() {
            for (int i = 0; i < na.length; i++) {

                double d = h;
                for (int j = 0; j < junctions.size(); j++) {
                    Node junction = (Node) junctions.get(j);
                    d = Math.min(d, dist[na[i].ix][junction.ix]);
                }
                na[i].junctionDist = d;

            }
        }

        private boolean pill(Node node) {
            return ((node.x - 7) % pillSpace) == 0 &&
                    ((node.y - top) % pillSpace) == 0;

        }

        public boolean powerPill(Node node) {

            if (mazeNo == 1) {
                if (node.x == 7) {
                    return node.y == 13 || node.y == 113;
                }
                if (node.x == 107) {
                    return node.y == 13 || node.y == 113;
                }
            } else if (mazeNo == 2) {
                if (node.x == 7) {
                    return node.y == 21 || node.y == 109;
                }
                if (node.x == 107) {
                    return node.y == 21 || node.y == 109;
                }
            } else if (mazeNo == 3) {
                if (node.x == 7) {
                    return node.y == 17 || node.y == 97;
                }
                if (node.x == 107) {
                    return node.y == 17 || node.y == 97;
                }
            } else if (mazeNo == 4) {
                if (node.x == 7) {
                    return node.y == 17 || node.y == 113;
                }
                if (node.x == 107) {
                    return node.y == 17 || node.y == 113;
                }
            }


            return false;
        }

        private int sgn(int x) {
            if (x < 0) return -1;
            if (x > 0) return 1;
            return 0;
        }
    }

    public ArrayList<Node> getPowers() {
        return (ArrayList<Node>)powers.clone();
    }

    public ArrayList<Node> getPills() {
        return (ArrayList<Node>)pills.clone();
    }

    public ArrayList<Node> getMap() {
        return (ArrayList<Node>)map.clone();
    }

    public int getWidth() {
        return w;
    }

    public int getHeight() {
        return h;
    }

    public Node[][] getNode2DArray() {
        return na;
    }
}