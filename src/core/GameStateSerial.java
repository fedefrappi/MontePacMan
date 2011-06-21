package core;

import core.maze.Level;
import core.maze.Maze;

import java.util.BitSet;

public class GameStateSerial {
    private final int agentPos;
    private final GhostsSerial[] ghosts;
    private final int level;
    private final int score;
    private final BitSet powers;
    private final BitSet pills;
    private final int gameTick;

    public GameStateSerial(GameStateInterface gs) {
        score = gs.getScore();
        pills = gs.getPills();
        powers = gs.getPowers();
        level = gs.getLevel();
        agentPos = gs.getPacman().current.nodeIndex;
        ghosts = GhostsSerial.getGhostStateSerial(gs.getGhosts());
        gameTick = gs.getTotalGameTicks();
    }

    public GameState getGameState() {
        GameState gs = new GameState();
        gs.score = score;
        gs.pills = pills;
        gs.powers = powers;
        gs.level = level;
        Maze maze = Level.getMaze(level);
        gs.maze = maze;
        gs.ghosts = GhostsSerial.getGhostState(ghosts, level);
        gs.pacMan.current = maze.getNode(agentPos);
        gs.totalGameTicks = gameTick;
        return gs;
    }
}