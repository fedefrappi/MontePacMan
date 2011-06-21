package core.maze;


import java.util.ArrayList;

class MazeFour {

    public static Maze getMaze() {
        return new Maze(maze, w, h, xx, yy, 4);
    }

    private static final int w = 114;
    private static final int h = 130;
    private static final int[] xx = {7, 15, 19, 27, 39, 51, 63, 75, 87, 95, 99, 107};
    private static final int[] yy = {9, 21, 25, 37, 49, 57, 61, 69, 73, 77, 85, 97, 109, 121};

    private static final ArrayList<Line> maze = new ArrayList<Line>();

    static {
        maze.add(new Line(7, 9, 107, 9));
        maze.add(new Line(7, 9, 7, 37));
        maze.add(new Line(39, 9, 39, 37));
        maze.add(new Line(75, 9, 75, 37));
        maze.add(new Line(107, 9, 107, 37));
        maze.add(new Line(19, 9, 19, 25));
        maze.add(new Line(95, 9, 95, 25));
        maze.add(new Line(27, 25, 27, 37));
        maze.add(new Line(87, 25, 87, 37));
        maze.add(new Line(51, 21, 51, 37));
        maze.add(new Line(63, 21, 63, 37));
        maze.add(new Line(51, 21, 63, 21));
        maze.add(new Line(19, 25, 39, 25));
        maze.add(new Line(75, 25, 95, 25));
        maze.add(new Line(7, 37, 27, 37));
        maze.add(new Line(39, 37, 51, 37));
        maze.add(new Line(63, 37, 75, 37));
        maze.add(new Line(87, 37, 107, 37));
        maze.add(new Line(15, 37, 15, 49));
        maze.add(new Line(99, 37, 99, 49));
        maze.add(new Line(15, 49, 27, 49));
        maze.add(new Line(87, 49, 99, 49));
        maze.add(new Line(27, 49, 27, 85));
        maze.add(new Line(87, 49, 87, 85));
        maze.add(new Line(15, 77, 27, 77));
        maze.add(new Line(87, 77, 99, 77));
        maze.add(new Line(27, 85, 39, 85));
        maze.add(new Line(75, 85, 87, 85));
        maze.add(new Line(15, 77, 15, 97));
        maze.add(new Line(99, 77, 99, 97));
        maze.add(new Line(39, 85, 39, 109));
        maze.add(new Line(75, 85, 75, 109));
        maze.add(new Line(7, 97, 39, 97));
        maze.add(new Line(75, 97, 107, 97));
        maze.add(new Line(7, 97, 7, 121));
        maze.add(new Line(107, 97, 107, 121));
        maze.add(new Line(27, 97, 27, 109));
        maze.add(new Line(87, 97, 87, 109));
        maze.add(new Line(19, 109, 19, 121));
        maze.add(new Line(51, 109, 51, 121));
        maze.add(new Line(63, 109, 63, 121));
        maze.add(new Line(95, 109, 95, 121));
        maze.add(new Line(19, 109, 27, 109));
        maze.add(new Line(39, 109, 75, 109));
        maze.add(new Line(87, 109, 95, 109));
        maze.add(new Line(7, 121, 51, 121));
        maze.add(new Line(63, 121, 107, 121));
        maze.add(new Line(0, 57, 15, 57, false));
        maze.add(new Line(0, 69, 15, 69, false));
        maze.add(new Line(99, 57, 114, 57, false));
        maze.add(new Line(99, 69, 114, 69, false));
        maze.add(new Line(15, 49, 15, 57, false));
        maze.add(new Line(15, 69, 15, 77, false));
        maze.add(new Line(99, 49, 99, 57, false));
        maze.add(new Line(99, 69, 99, 77, false));
        maze.add(new Line(51, 37, 51, 49, false));
        maze.add(new Line(63, 37, 63, 49, false));
        maze.add(new Line(39, 49, 75, 49, false));
        maze.add(new Line(39, 73, 75, 73, false));
        maze.add(new Line(39, 49, 39, 73, false));
        maze.add(new Line(75, 49, 75, 73, false));
        maze.add(new Line(27, 61, 39, 61, false));
        maze.add(new Line(75, 61, 87, 61, false));
        maze.add(new Line(51, 73, 51, 97, false));
        maze.add(new Line(63, 73, 63, 97, false));
        maze.add(new Line(39, 85, 51, 85, false));
        maze.add(new Line(63, 85, 75, 85, false));
        maze.add(new Line(51, 97, 63, 97, false));
    }
}