package core.maze;


import java.util.ArrayList;

class MazeTwo {
    public static Maze getMaze() {
        return new Maze(maze, w, h, xx, yy, 2);
    }

    private static final int w = 114;
    private static final int h = 130;

    private static final int[] xx = {7, 15, 19, 27, 39, 47, 51, 63, 67, 75, 87, 95, 99, 107};
    private static final int[] yy = {9, 21, 33, 37, 45, 49, 57, 65, 69, 73, 85, 97, 109, 121};

    private static final ArrayList<Line> maze = new ArrayList<Line>();

    static {
        maze.add(new Line(7, 21, 7, 45));
        maze.add(new Line(39, 9, 39, 33));
        maze.add(new Line(51, 21, 51, 37));
        maze.add(new Line(63, 21, 63, 37));
        maze.add(new Line(75, 9, 75, 33));
        maze.add(new Line(107, 21, 107, 45));
        maze.add(new Line(39, 9, 75, 9));
        maze.add(new Line(51, 37, 63, 37));
        maze.add(new Line(7, 21, 51, 21));
        maze.add(new Line(63, 21, 107, 21));
        maze.add(new Line(19, 33, 39, 33));
        maze.add(new Line(75, 33, 95, 33));
        maze.add(new Line(7, 45, 27, 45));
        maze.add(new Line(87, 45, 107, 45));
        maze.add(new Line(19, 33, 19, 45));
        maze.add(new Line(95, 33, 95, 45));
        maze.add(new Line(7, 57, 7, 69));
        maze.add(new Line(107, 57, 107, 69));
        maze.add(new Line(27, 45, 27, 85));
        maze.add(new Line(87, 45, 87, 85));
        maze.add(new Line(7, 57, 27, 57));
        maze.add(new Line(87, 57, 107, 57));
        maze.add(new Line(15, 69, 15, 109));
        maze.add(new Line(99, 69, 99, 109));
        maze.add(new Line(7, 69, 15, 69));
        maze.add(new Line(99, 69, 107, 69));
        maze.add(new Line(15, 85, 47, 85));
        maze.add(new Line(99, 85, 67, 85));
        maze.add(new Line(47, 85, 47, 97));
        maze.add(new Line(67, 85, 67, 97));
        maze.add(new Line(15, 97, 27, 97));
        maze.add(new Line(39, 97, 47, 97));
        maze.add(new Line(67, 97, 75, 97));
        maze.add(new Line(87, 97, 99, 97));
        maze.add(new Line(15, 109, 7, 109));
        maze.add(new Line(99, 109, 107, 109));
        maze.add(new Line(7, 109, 7, 121));
        maze.add(new Line(107, 109, 107, 121));
        maze.add(new Line(7, 121, 107, 121));
        maze.add(new Line(27, 97, 27, 121));
        maze.add(new Line(87, 97, 87, 121));
        maze.add(new Line(39, 97, 39, 109));
        maze.add(new Line(75, 97, 75, 109));
        maze.add(new Line(51, 109, 51, 121));
        maze.add(new Line(63, 109, 63, 121));
        maze.add(new Line(27, 109, 51, 109));
        maze.add(new Line(63, 109, 87, 109));
        maze.add(new Line(0, 9, 27, 9, false));
        maze.add(new Line(87, 9, 114, 9, false));
        maze.add(new Line(27, 9, 27, 21, false));
        maze.add(new Line(87, 9, 87, 21, false));
        maze.add(new Line(0, 97, 15, 97, false));
        maze.add(new Line(99, 97, 114, 97, false));
        maze.add(new Line(47, 97, 67, 97, false));
        maze.add(new Line(47, 85, 47, 73, false));
        maze.add(new Line(67, 85, 67, 73, false));
        maze.add(new Line(39, 73, 75, 73, false));
        maze.add(new Line(39, 31, 39, 73, false));
        maze.add(new Line(75, 31, 75, 73, false));
        maze.add(new Line(39, 49, 75, 49, false));
        maze.add(new Line(27, 65, 39, 65, false));
        maze.add(new Line(75, 65, 87, 65, false));
    }
}