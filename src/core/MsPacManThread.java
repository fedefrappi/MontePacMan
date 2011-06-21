package core;

import competition.MsPacManController;

class MsPacManThread extends Thread {
    private GameStateInterface gs;
    private GameThread gt;
    private final MsPacManController agent;
    private boolean alive;

    public MsPacManThread(MsPacManController agent) {
        this.agent = agent;
        alive = true;

        start();
    }

    public synchronized void die() {
        alive = false;
        notify();
    }

    public synchronized void action(GameStateInterface gs, GameThread gt) {
        this.gs = gs;
        this.gt = gt;

        notify();
    }

    public void run() {
        while (alive) {
            try {
                synchronized (this) {
                    wait();
                }
                int dir = agent.getAction(gs);
                gt.setAgentDir(dir);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}