package competition;

import controllers.KeyController;
import controllers.examples.*;
import core.*;
import core.maze.MazeOne;
import core.visuals.GameStateView;
import core.maze.Maze;
import core.visuals.JEasyFrame;

//Executes the code and shows how you can test your controllers.
public class Main implements Constants {

    private static final boolean visual = false;
    private static final boolean async = false;

    public static void main(String[] args) throws Exception {
    	long total = 0;
    	for (int i = 0; i < 100; i++) {
	    	MsPacManController mspacman = new MCTSMsPacman();
	    	
	        GhostsController ghosts = new LegacyTeam();
	
	        if (visual) {
	            runVisual(mspacman, ghosts, async);
	        } else {
	        	total += runDark(mspacman, ghosts, async);
	    	}
    	}
    	System.out.println("Mean score: " + total/100);
    }

    public static int runDark(MsPacManController mspacman, GhostsController ghosts, boolean async) throws Exception {
    	
        Maze maze = MazeOne.getMaze();
        GameState gs = new GameState(maze);
        gs.reset();
        Game game;

        if (!async)
            game = new Game(gs, null, mspacman, ghosts);
        else
            game = new GameThread(gs, null, mspacman, ghosts);

        game.gs.reset();
        game.run(delay);
        System.out.println("Final score: " + game.gs.getScore() + ", ticks = " + game.gs.getTotalGameTicks());

        return game.gs.getScore();
    }

    public static int runVisual(MsPacManController mspacman, GhostsController ghosts, boolean async) throws Exception {
        Maze maze = MazeOne.getMaze();
        GameState gs = new GameState(maze);
        gs.reset();
        GameStateView gsv = new GameStateView(gs);
        JEasyFrame fr = new JEasyFrame(gsv, "Pac-Man vs Ghosts", true);

        if (mspacman == null) {
            KeyController kc = new KeyController();
            fr.addKeyListener(kc);
            mspacman = kc;
        }

        Game game;

        if (!async)
            game = new Game(gs, gsv, mspacman, ghosts);
        else
            game = new GameThread(gs, gsv, mspacman, ghosts);

        game.frame = fr;
        game.run(delay);

        return game.gs.getScore();
    }
}