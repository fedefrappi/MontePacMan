package core;

import competition.GhostsController;
import competition.MsPacManController;
import core.visuals.GameStateView;

public class GameThread extends Game implements Constants {
    private int agentDir;
    private final int[] ghostTeamDirs;
    private MsPacManThread mspacmanThread;
    private GhostsThread ghostsThread;

    public GameThread(GameState gs, GameStateView gsv, MsPacManController mspacman, GhostsController ghosts) {
        super(gs, gsv, mspacman, ghosts);

        ghostTeamDirs = new int[dx.length];
    }

    public int run(int delay) throws Exception {
        setProxies();
        while (!gs.terminal()) {
            cycle(delay);
        }
        killProxies();
        return gs.getScore();
    }

    private void setProxies() {
        mspacmanThread = new MsPacManThread(mspacman);
        ghostsThread = new GhostsThread(ghosts);
    }

    private void killProxies() {
        mspacmanThread.die();
        ghostsThread.die();
    }

    void cycle(int delay) throws Exception {
        mspacmanThread.action(gs.copy(), this);
        ghostsThread.action(gs.copy(), this);
        Thread.sleep(delay);


        gs.next(agentDir, ghostTeamDirs);
        if (gsv != null) {
            gsv.repaint();
            if (frame != null)
                frame.setTitle("Score: " + gs.getScore() + ", time: " + gs.getTotalGameTicks());
        }
    }

    public void setAgentDir(int dir) {
        agentDir = dir;
    }

    public void setGhostTeamDirs(int[] dirs) {
        for (int i = 0; i < dirs.length; i++) {
            ghostTeamDirs[i] = dirs[i];
        }
    }
}