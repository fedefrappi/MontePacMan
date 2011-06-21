package core.visuals;

import core.Constants;
import core.GameState;
import core.Ghosts;
import core.Node;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class GameStateView extends JComponent implements Constants {
    private final GameState gs;
    private final MapView mv;
    private final BufferedImage[][] pacmanImgs = new BufferedImage[4][3];
    private final BufferedImage[][][] ghostsImgs = new BufferedImage[6][4][2];

    public GameStateView(GameState gs) {
        this.gs = gs;
        mv = new MapView(gs);

        pacmanImgs[UP][0] = ImageLoader.getImage("mspacman-up-normal.png");
        pacmanImgs[UP][1] = ImageLoader.getImage("mspacman-up-open.png");
        pacmanImgs[UP][2] = ImageLoader.getImage("mspacman-up-closed.png");
        pacmanImgs[RIGHT][0] = ImageLoader.getImage("mspacman-right-normal.png");
        pacmanImgs[RIGHT][1] = ImageLoader.getImage("mspacman-right-open.png");
        pacmanImgs[RIGHT][2] = ImageLoader.getImage("mspacman-right-closed.png");
        pacmanImgs[DOWN][0] = ImageLoader.getImage("mspacman-down-normal.png");
        pacmanImgs[DOWN][1] = ImageLoader.getImage("mspacman-down-open.png");
        pacmanImgs[DOWN][2] = ImageLoader.getImage("mspacman-down-closed.png");
        pacmanImgs[LEFT][0] = ImageLoader.getImage("mspacman-left-normal.png");
        pacmanImgs[LEFT][1] = ImageLoader.getImage("mspacman-left-open.png");
        pacmanImgs[LEFT][2] = ImageLoader.getImage("mspacman-left-closed.png");
        ghostsImgs[0][UP][0] = ImageLoader.getImage("blinky-up-1.png");
        ghostsImgs[0][UP][1] = ImageLoader.getImage("blinky-up-2.png");
        ghostsImgs[0][RIGHT][0] = ImageLoader.getImage("blinky-right-1.png");
        ghostsImgs[0][RIGHT][1] = ImageLoader.getImage("blinky-right-2.png");
        ghostsImgs[0][DOWN][0] = ImageLoader.getImage("blinky-down-1.png");
        ghostsImgs[0][DOWN][1] = ImageLoader.getImage("blinky-down-2.png");
        ghostsImgs[0][LEFT][0] = ImageLoader.getImage("blinky-left-1.png");
        ghostsImgs[0][LEFT][1] = ImageLoader.getImage("blinky-left-2.png");
        ghostsImgs[1][UP][0] = ImageLoader.getImage("pinky-up-1.png");
        ghostsImgs[1][UP][1] = ImageLoader.getImage("pinky-up-2.png");
        ghostsImgs[1][RIGHT][0] = ImageLoader.getImage("pinky-right-1.png");
        ghostsImgs[1][RIGHT][1] = ImageLoader.getImage("pinky-right-2.png");
        ghostsImgs[1][DOWN][0] = ImageLoader.getImage("pinky-down-1.png");
        ghostsImgs[1][DOWN][1] = ImageLoader.getImage("pinky-down-2.png");
        ghostsImgs[1][LEFT][0] = ImageLoader.getImage("pinky-left-1.png");
        ghostsImgs[1][LEFT][1] = ImageLoader.getImage("pinky-left-2.png");
        ghostsImgs[2][UP][0] = ImageLoader.getImage("inky-up-1.png");
        ghostsImgs[2][UP][1] = ImageLoader.getImage("inky-up-2.png");
        ghostsImgs[2][RIGHT][0] = ImageLoader.getImage("inky-right-1.png");
        ghostsImgs[2][RIGHT][1] = ImageLoader.getImage("inky-right-2.png");
        ghostsImgs[2][DOWN][0] = ImageLoader.getImage("inky-down-1.png");
        ghostsImgs[2][DOWN][1] = ImageLoader.getImage("inky-down-2.png");
        ghostsImgs[2][LEFT][0] = ImageLoader.getImage("inky-left-1.png");
        ghostsImgs[2][LEFT][1] = ImageLoader.getImage("inky-left-2.png");
        ghostsImgs[3][UP][0] = ImageLoader.getImage("sue-up-1.png");
        ghostsImgs[3][UP][1] = ImageLoader.getImage("sue-up-2.png");
        ghostsImgs[3][RIGHT][0] = ImageLoader.getImage("sue-right-1.png");
        ghostsImgs[3][RIGHT][1] = ImageLoader.getImage("sue-right-2.png");
        ghostsImgs[3][DOWN][0] = ImageLoader.getImage("sue-down-1.png");
        ghostsImgs[3][DOWN][1] = ImageLoader.getImage("sue-down-2.png");
        ghostsImgs[3][LEFT][0] = ImageLoader.getImage("sue-left-1.png");
        ghostsImgs[3][LEFT][1] = ImageLoader.getImage("sue-left-2.png");
        ghostsImgs[4][0][0] = ImageLoader.getImage("edible-ghost-1.png");
        ghostsImgs[4][0][1] = ImageLoader.getImage("edible-ghost-2.png");
        ghostsImgs[5][0][0] = ImageLoader.getImage("edible-ghost-blink-1.png");
        ghostsImgs[5][0][1] = ImageLoader.getImage("edible-ghost-blink-2.png");
    }

    public void paintComponent(Graphics g) {

        mv.paintComponent(g);

        g.setColor(Color.white);
        for (Node p : gs.maze.getPills()) {

            if (gs.pills.get(p.pillIndex)) {
                g.fillOval(p.x * MAG - 1, p.y * MAG - 1, 3, 3);
            }
        }
        g.setColor(Color.WHITE);
        for (Node p : gs.maze.getPowers()) {

            if (gs.powers.get(p.powerIndex)) {
                g.fillOval(p.x * MAG - 5, p.y * MAG - 5, 10, 10);
            }
        }

        Node pac = gs.pacMan.current;
        g.drawImage(pacmanImgs[gs.pacMan.curDir][(gs.totalGameTicks % 6) / 2], pac.x * MAG - 6, pac.y * MAG - 6, null);
        int ix = 0;
        for (Ghosts ghs : gs.ghosts) {
            if (ghs != null) {
                drawGhost(g, ghs, ix++);
            }
        }

        drawLives(g);
    }

    private void drawLives(Graphics g) {
        for (int i = 0; i < gs.nLivesRemaining - 1; i++) {
            g.drawImage(pacmanImgs[RIGHT][0], (i * 10) + ((10 * i) / 2) + (10 / 2), 260, null);
        }
    }

    private void drawGhost(Graphics g, Ghosts ghs, int ix) {
        if (ghs.returning()) {
            drawEyes(g, ghs);
        } else {
            Node ng = ghs.current;

            if (ng != null) {
                if (ghs.edible()) {

                    if (ghs.edibleTime < 40 && ((gs.totalGameTicks % 6) / 3) == 0)
                        g.drawImage(ghostsImgs[5][0][(gs.totalGameTicks % 6) / 3], ng.x * MAG - 6, ng.y * MAG - 6, null);
                    else
                        g.drawImage(ghostsImgs[4][0][(gs.totalGameTicks % 6) / 3], ng.x * MAG - 6, ng.y * MAG - 6, null);
                } else {
                    g.drawImage(ghostsImgs[ix][ghs.curDir][(gs.totalGameTicks % 6) / 3], ng.x * MAG - 6, ng.y * MAG - 6, null);
                }
            }
        }
    }

    void drawEyes(Graphics g, Ghosts ghs) {
        Node n = ghs.current;
        g.setColor(Color.white);
        g.fillOval(n.x * MAG - 6, n.y * MAG - 3, 6, 6);
        g.fillOval(n.x * MAG, n.y * MAG - 3, 6, 6);
        g.setColor(Color.blue);
        g.fillOval(n.x * MAG - 4, n.y * MAG - 1, 2, 2);
        g.fillOval(n.x * MAG + 2, n.y * MAG - 1, 2, 2);
    }

    public Dimension getPreferredSize
            () {
        return new Dimension(gs.maze.getWidth() * MAG, gs.maze.getHeight() * MAG + 20);
    }
}