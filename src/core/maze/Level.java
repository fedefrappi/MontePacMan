package core.maze;

import java.util.ArrayList;

public class Level {

    private static final ArrayList<Maze> mazes = new ArrayList<Maze>();

    static {
        Maze maze1 = MazeOne.getMaze();
        mazes.add(maze1);
//        System.out.println("Processed maze 1");

        Maze maze2 = MazeTwo.getMaze();
        mazes.add(maze2);
//        System.out.println("Processed maze 2");

        Maze maze3 = MazeThree.getMaze();
        mazes.add(maze3);
//        System.out.println("Processed maze 3");

        Maze maze4 = MazeFour.getMaze();
        mazes.add(maze4);
//        System.out.println("Processed maze 4");
    }

    public static Maze getMaze(int level) {
        int index = level % mazes.size();
        return mazes.get(index);
    }

    private static final int maxEdibleTime = 150;
    private static final double edibleTimeReductionFactor = 0.67;

    public static int edibleTime(int level) {
        return (int) (maxEdibleTime *
                Math.pow(edibleTimeReductionFactor, level));
    }
}
