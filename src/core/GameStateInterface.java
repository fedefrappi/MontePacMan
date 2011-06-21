package core;

import core.maze.MazeInterface;

import java.util.BitSet;

public interface GameStateInterface {
    GameStateInterface copy();
    void next(int pacDir, int[] ghostDirs);
    MsPacMan getPacman();
    MazeInterface getMaze();
    int getLevel();
    BitSet getPills();
    BitSet getPowers();
    Ghosts[] getGhosts();
    int getScore();
    int getTotalGameTicks();
    int getEdibleGhostScore();
    int getLivesRemaining();
    boolean MsPacManDeath();
    boolean terminal();
    void reset();
    
    public void setLastLife();
}