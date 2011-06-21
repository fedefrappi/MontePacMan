package core.maze;

import core.Node;

import java.util.ArrayList;

public interface MazeInterface {
    int dist(Node a, Node b);
    ArrayList<Node> getPowers();
    ArrayList<Node> getPills();
    ArrayList<Node> getMap();
    int getWidth();
    int getHeight();
    Node[][] getNode2DArray();
    Node ghostStart();
    Node pacStart();
    Node getNode(int x, int y);
    Node getNode(int index);
    int getNumber();
}
