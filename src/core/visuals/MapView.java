package core.visuals;

import core.Constants;
import core.GameStateInterface;
import core.maze.MazeInterface;
import core.Node;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

class MapView extends JComponent implements Constants {
    private final GameStateInterface gs;
    private MazeInterface maze;
    private final BufferedImage[] images;

    public MapView(GameStateInterface gs) {
        this.gs = gs;
        images = ImageLoader.loadImages();
    }

    public void paintComponent(Graphics g) {
        maze = gs.getMaze();
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, maze.getWidth() * MAG, maze.getHeight() * MAG + 20);
        int mazeNumber = gs.getMaze().getNumber();
        BufferedImage bg = images[mazeNumber];

        if (bg != null) {
            g.drawImage(bg, 2, 6, null);
        }


        for (Node n : maze.getMap()) {
            if (n.col != Color.black) {
                g.setColor(n.col);
                g.fillRect(n.x * MAG - 8, n.y * MAG - 8, 16, 16);

                n.col = Color.black;
            }
        }
    }

    public Dimension getPreferredSize() {
        maze = gs.getMaze();
        return new Dimension(maze.getWidth() * MAG, maze.getHeight() * MAG);
    }
}
