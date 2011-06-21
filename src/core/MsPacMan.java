package core;

import core.maze.Maze;

public class MsPacMan implements Constants {

    public Node current;
    public int curDir;

    public MsPacMan(Node current) {
        this.current = current;
        curDir = LEFT;
    }

    public MsPacMan copy() {
        MsPacMan p = new MsPacMan(current);
        p.curDir = curDir;
        return p;
    }

    public void next(int dir, Maze maze) {
        if (dir == NEUTRAL) dir = curDir;
        Node next = maze.getNode(current.x + dx[dir], current.y + dy[dir]);
        if (next != null) {
            current = next;
            curDir = dir;
        } else {
            next = maze.getNode(current.x + dx[curDir], current.y + dy[curDir]);
            if (next != null) current = next;
        }
    }
}