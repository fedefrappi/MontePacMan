package core;

import core.maze.Level;
import core.maze.Maze;
import core.utilities.Utilities;

import java.util.BitSet;


public class GameState implements GameStateInterface, Constants {
    public Maze maze;
    public BitSet powers;
    public BitSet pills;
    public MsPacMan pacMan;
    public Ghosts[] ghosts;
    public int score = 0;
    int level;
    private int edibleGhostScore;
    public int gameTicks,totalGameTicks;
    public int nLivesRemaining;
    private boolean extraLife = false;

    public GameStateInterface copy() {
        GameState gs = new GameState();
        gs.maze = maze;
        gs.powers = (BitSet) powers.clone();
        gs.pills = (BitSet) pills.clone();
        gs.pacMan = pacMan.copy();
        for (int i = 0; i < ghosts.length; i++) {
            gs.ghosts[i] = ghosts[i].copy();
        }
        gs.score = score;
        gs.level = level;
        gs.edibleGhostScore = edibleGhostScore;
        gs.totalGameTicks = totalGameTicks;
        gs.nLivesRemaining = nLivesRemaining;
        gs.extraLife = extraLife;
        gs.gameTicks = gameTicks;
        return gs;
    }

    public GameState() {
        this(Level.getMaze(0));
    }

    public GameState(Maze maze) {
        this.maze = maze;
        ghosts = new Ghosts[nGhosts];
        pills = new BitSet(maze.getPills().size());
        powers = new BitSet(maze.getPowers().size());
        reset();
    }

    public void reset() {
        resetScores();
        maze = Level.getMaze(level);
        resetAgents();
        resetPills();
        totalGameTicks = 0;
        gameTicks = 0;
    }

    void resetAgents() {
        pacMan = new MsPacMan(maze.getMap().get(0));
        pacMan.current = maze.pacStart();
        for (int i = 0; i < nGhosts; i++) {
            ghosts[i] = new Ghosts(initGhostDelay[i]);
            ghosts[i].current = maze.ghostStart();
        }
    }

    //bug fix thanks to James_2
    void resetPills() {
        pills = new BitSet(maze.getPills().size());
        powers = new BitSet(maze.getPowers().size());
        pills.set(0, maze.getPills().size());
        powers.set(0, maze.getPowers().size());
        }

    void resetScores() {
        totalGameTicks = 0;
        score = 0;
        level = 0;
        nLivesRemaining = nLives;
    }

    public void nextLevel() {
        level++;
        maze = Level.getMaze(level);
        resetPills();
        resetAgents();
        gameTicks = 0;
    }

    public void next(int pacDir, int[] ghostDirs) {
        pacMan.next(pacDir, maze);
        tryEatPill();
        tryEatPower();
        eatGhosts();
        if (ghostReversal()) {
            reverseGhosts();
        } else {
            moveGhosts(ghostDirs);
        }
        if (pillsCleared() || gameTicks>=limit) {

            if(gameTicks>=limit)
                score+=10*pills.cardinality()+50*powers.cardinality();

            nextLevel();
        } else {
            if (MsPacManDeath()) {
                nLivesRemaining--;
                resetAgents();
            }
        }
        if(score>=10000 && !extraLife)
        {
            nLivesRemaining++;
            extraLife=true;
        }

        totalGameTicks++;
        gameTicks++;
    }

    boolean ghostReversal() {
        return rand.nextDouble() < 0.005;
    }

    void reverseGhosts() {
        for (Ghosts g : ghosts) g.reverse();
    }

    void moveGhosts(int[] ghostDirs) {
        for (int i = 0; i < ghosts.length; i++) {
            ghosts[i].next(ghostDirs[i], this);
        }
    }

    void tryEatPill() {
        int ix = pacMan.current.pillIndex;
        if (ix >= 0) {
            if (pills.get(ix)) {
                pills.clear(ix);
                score += pillScore;

            }
        }
    }

    void tryEatPower() {
        int ix = pacMan.current.powerIndex;
        if (ix >= 0) {
            if (powers.get(ix)) {
                powers.clear(ix);

                reverseGhosts();
                setEdible();
                score += powerScore;
            }
        }
    }

    void setEdible() {
        edibleGhostScore = edibleGhostStartingScore;
        for (Ghosts g : ghosts) {
            g.edibleTime = Level.edibleTime(level);
        }
    }

    boolean pillsCleared() {
        return pills.isEmpty() && powers.isEmpty();
    }

    public boolean MsPacManDeath() {
        for (Ghosts g : ghosts) {
            if (!g.edible() && !g.returning() && overlap(g, pacMan)) {

                return true;
            }
        }
        return false;
    }

    void eatGhosts() {
        for (Ghosts g : ghosts) {
            if (g.edible() && overlap(g, pacMan)) {
                score += edibleGhostScore;
                edibleGhostScore *= 2;

                g.returnNode = maze.ghostStart();
                g.setPredatory();
            }
        }
    }

    public boolean terminal() {
        return nLivesRemaining <= 0;
    }

    private static boolean overlap(Ghosts g, MsPacMan agent) {
        return Utilities.manhattan(g.current, agent.current)
                <= agentOverlapDistance;
    }

    public int getLivesRemaining() {
        return nLivesRemaining;
    }

    public MsPacMan getPacman() {
        return pacMan;
    }

    public Maze getMaze() {
        return maze;
    }

    public BitSet getPills() {
        return pills;
    }

    public BitSet getPowers() {
        return powers;
    }

    public Ghosts[] getGhosts() {
        return ghosts;
    }

    public int getScore() {
        return score;
    }

    public int getLevel() {
        return level;
    }

    public int getTotalGameTicks() {
        return totalGameTicks;
    }

    public int getEdibleGhostScore() {
        return edibleGhostScore;
    }
    
    public void setLastLife(){
    	this.nLivesRemaining = 1;
    }
}