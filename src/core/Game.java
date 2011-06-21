package core;

import competition.GhostsController;
import competition.MsPacManController;
import core.visuals.GameStateView;
import core.visuals.JEasyFrame;

public class Game implements Constants {
    public final GameStateInterface gs;
    final GameStateView gsv;
    final MsPacManController mspacman;
    final GhostsController ghosts;
    public JEasyFrame frame;

    public Game(GameStateInterface gs, GameStateView gsv, MsPacManController mspacman, GhostsController ghosts) {
        this.gs = gs;
        this.gsv = gsv;
        this.mspacman = mspacman;
        this.ghosts = ghosts;
    }

    public int run(int delay) throws Exception {
        while (!gs.terminal()) {
            cycle(delay);
        }
        return gs.getScore();
    }

    void cycle(int delay) throws Exception {
        gs.next(mspacman.getAction(gs), ghosts.getActions(gs));

        if (gsv != null) {
            gsv.repaint();
            if (frame != null)
                frame.setTitle("Score: " + gs.getScore() + ", time: " + gs.getTotalGameTicks());
            Thread.sleep(delay);
        }
    }
}