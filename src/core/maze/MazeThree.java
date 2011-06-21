package core.maze;


import java.util.ArrayList;

class MazeThree {
    public static Maze getMaze() {
        return new Maze(maze, w, h, xx, yy, 3);
    }

    private static final int w = 114;
    private static final int h = 130;

    private static final int[] xx = {7, 15, 19, 27, 31, 39, 51, 63, 75, 83, 87, 95, 99, 107};
    private static final int[] yy = {9, 21, 29, 37, 41, 49, 61, 73, 85, 97, 109, 121};

    private static final ArrayList<Line> maze = new ArrayList<Line>();

    static {
        maze.add(new Line(7, 9, 39, 9));
        maze.add(new Line(51, 9, 63, 9));
        maze.add(new Line(75, 9, 107, 9));
        maze.add(new Line(7, 9, 7, 29));
        maze.add(new Line(107, 9, 107, 29));
        maze.add(new Line(39, 9, 39, 21));
        maze.add(new Line(75, 9, 75, 21));
        maze.add(new Line(51, 9, 51, 37));
        maze.add(new Line(63, 9, 63, 37));
        maze.add(new Line(19, 21, 51, 21));
        maze.add(new Line(63, 21, 95, 21));
        maze.add(new Line(7, 29, 19, 29));
        maze.add(new Line(95, 29, 107, 29));
        maze.add(new Line(31, 37, 83, 37));
        maze.add(new Line(19, 21, 19, 41));
        maze.add(new Line(95, 21, 95, 41));
        maze.add(new Line(31, 21, 31, 37));
        maze.add(new Line(83, 21, 83, 37));
        maze.add(new Line(7, 41, 19, 41));
        maze.add(new Line(95, 41, 107, 41));
        maze.add(new Line(7, 41, 7, 85));
        maze.add(new Line(107, 41, 107, 85));
        maze.add(new Line(7, 85, 27, 85));
        maze.add(new Line(39, 85, 51, 85));
        maze.add(new Line(63, 85, 75, 85));
        maze.add(new Line(87, 85, 107, 85));
        maze.add(new Line(15, 85, 15, 97));
        maze.add(new Line(39, 85, 39, 97));
        maze.add(new Line(75, 85, 75, 97));
        maze.add(new Line(99, 85, 99, 97));
        maze.add(new Line(27, 85, 27, 121));
        maze.add(new Line(87, 85, 87, 121));
        maze.add(new Line(7, 97, 15, 97));
        maze.add(new Line(27, 97, 87, 97));
        maze.add(new Line(99, 97, 107, 97));
        maze.add(new Line(7, 97, 7, 121));
        maze.add(new Line(107, 97, 107, 121));
        maze.add(new Line(51, 97, 51, 109));
        maze.add(new Line(63, 97, 63, 109));
        maze.add(new Line(39, 109, 39, 121));
        maze.add(new Line(75, 109, 75, 121));
        maze.add(new Line(7, 109, 27, 109));
        maze.add(new Line(39, 109, 51, 109));
        maze.add(new Line(63, 109, 75, 109));
        maze.add(new Line(87, 109, 107, 109));
        maze.add(new Line(7, 121, 27, 121));
        maze.add(new Line(39, 121, 75, 121));
        maze.add(new Line(87, 121, 107, 121));
        maze.add(new Line(0, 41, 7, 41, false));
        maze.add(new Line(107, 41, 114, 41, false));
        maze.add(new Line(19, 41, 19, 49, false));
        maze.add(new Line(95, 41, 95, 49, false));
        maze.add(new Line(39, 37, 39, 73, false));
        maze.add(new Line(75, 37, 75, 73, false));
        maze.add(new Line(19, 49, 95, 49, false));
        maze.add(new Line(27, 49, 27, 61, false));
        maze.add(new Line(87, 49, 87, 61, false));
        maze.add(new Line(19, 61, 19, 73, false));
        maze.add(new Line(95, 61, 95, 73, false));
        maze.add(new Line(7, 61, 27, 61, false));
        maze.add(new Line(87, 61, 107, 61, false));
        maze.add(new Line(19, 73, 95, 73, false));
        maze.add(new Line(27, 73, 27, 85, false));
        maze.add(new Line(51, 73, 51, 85, false));
        maze.add(new Line(63, 73, 63, 85, false));
        maze.add(new Line(87, 73, 87, 85, false));
    }
}