package core.maze;


import java.util.ArrayList;

public class MazeOne {
    public static Maze getMaze() {
        return new Maze(maze, w, h, xx, yy, 1);
    }

    private static final int w = 114;
    private static final int h = 130;
    private static final int[] xx = {7, 15, 27, 39, 51, 63, 75, 87, 99, 107};
    private static final int[] yy = {9, 21, 37, 49, 61, 73, 85, 97, 109, 121};

    private static final ArrayList<Line> maze = new ArrayList<Line>();

    static {
        maze.add(new Line(7, 9, 7, 21));
        maze.add(new Line(27, 9, 27, 37));
        maze.add(new Line(39, 9, 39, 21));
        maze.add(new Line(75, 9, 75, 21));
        maze.add(new Line(87, 9, 87, 37));
        maze.add(new Line(107, 9, 107, 21));
        maze.add(new Line(7, 21, 107, 21));
        maze.add(new Line(27, 37, 51, 37));
        maze.add(new Line(63, 37, 87, 37));
        maze.add(new Line(7, 9, 27, 9));
        maze.add(new Line(39, 9, 75, 9));
        maze.add(new Line(87, 9, 107, 9));
        maze.add(new Line(51, 21, 51, 37));
        maze.add(new Line(63, 21, 63, 37));
        maze.add(new Line(99, 21, 99, 97));
        maze.add(new Line(75, 85, 75, 97));
        maze.add(new Line(39, 85, 39, 97));
        maze.add(new Line(15, 21, 15, 97));
        maze.add(new Line(7, 97, 7, 121));
        maze.add(new Line(27, 97, 27, 121));
        maze.add(new Line(87, 97, 87, 121));
        maze.add(new Line(107, 97, 107, 121));
        maze.add(new Line(39, 109, 39, 121));
        maze.add(new Line(51, 97, 51, 109));
        maze.add(new Line(63, 97, 63, 109));
        maze.add(new Line(75, 109, 75, 121));
        maze.add(new Line(7, 97, 107, 97));
        maze.add(new Line(39, 109, 51, 109));
        maze.add(new Line(63, 109, 75, 109));
        maze.add(new Line(7, 121, 107, 121));
        maze.add(new Line(0, 73, 27, 73, false));
        maze.add(new Line(87, 73, 114, 73, false));
        maze.add(new Line(0, 37, 15, 37, false));
        maze.add(new Line(99, 37, 114, 37, false));
        maze.add(new Line(39, 37, 39, 73, false));
        maze.add(new Line(75, 37, 75, 73, false));
        maze.add(new Line(15, 49, 99, 49, false));
        maze.add(new Line(39, 73, 75, 73, false));
        maze.add(new Line(27, 73, 27, 61, false));
        maze.add(new Line(27, 61, 39, 61, false));
        maze.add(new Line(75, 61, 87, 61, false));
        maze.add(new Line(87, 61, 87, 73, false));
        maze.add(new Line(15, 85, 39, 85, true));
        maze.add(new Line(39, 85, 51, 85, false));
        maze.add(new Line(51, 85, 51, 73, false));
        maze.add(new Line(63, 73, 63, 85, false));
        maze.add(new Line(75, 85, 99, 85, true));
        maze.add(new Line(63, 85, 75, 85, false));
    }
}