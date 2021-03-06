package competition;

import java.io.BufferedWriter;
import java.io.FileWriter;

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
    	long iter = 12;
 //   	long total = 0;
 //   	long max = 0;
 //   	long total2 = 0;
    	
    	FileWriter fw = new FileWriter("out.txt");
    	BufferedWriter bw = new BufferedWriter(fw);
    	try{
    		int[] sims = {50,75,100,250,500};
        	for (int sim=0; sim<sims.length; sim++){
        		
        		bw.write("-------------------------\n");
        		bw.write(sims[sim] + " Simulations\n");
        		
        		for (int i = 0; i < iter; i++) {
        			bw.write((i+1)+"/"+iter + "\n");
        			MCTSMsPacman mspacman = new MCTSMsPacman();
        			
        			GhostsController ghosts = new LegacyTeam();
        			
        			if (visual) {
        				runVisual(mspacman, ghosts, async);
        			} else {
        				long currentScore = runDark(mspacman, ghosts, async);
     //   				total += currentScore;
     //   				total2 += currentScore*currentScore;
     //   				if(currentScore > max){
     //   					max = currentScore;
     //   				}
        			}
        			
        			bw.write("Mean simulation time : " + (mspacman.totalTime/mspacman.numberOfMoves) + " ms\n");
        			bw.write("Max simulation time: " + mspacman.maxTime + " ms\n");
        		}    		
        	}
        	bw.close();
    	} catch (Exception e) {

    	}
    	
    	    	/*
    	double devstd = Math.sqrt(iter*total2 - (total*total))/(double)iter;
    	System.out.println("---------------------------------");
    	System.out.println("Mean score: " + total/iter);
    	System.out.println("Std dev: " + devstd);
    	System.out.println("Max score: " + max);
    	*/
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