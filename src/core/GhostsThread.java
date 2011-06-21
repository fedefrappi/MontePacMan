package core;

import competition.GhostsController;

class GhostsThread extends Thread {
    private GameStateInterface gs;
    private GameThread gt;
    private final GhostsController team;
    private boolean alive;

    public GhostsThread(GhostsController team) {
        this.team = team;
        alive = true;
        start();
    }

    public synchronized void action(GameStateInterface gs, GameThread gt) {
        this.gs = gs;
        this.gt = gt;
        notify();
    }

    public synchronized void die() {
        alive = false;
        notify();
    }

    public void run() {
        while (alive) {
            try {
                 synchronized (this) {
                 wait();
                 }
                int[] dir = team.getActions(gs);
                gt.setGhostTeamDirs(dir);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}