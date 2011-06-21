package core.visuals;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class JEasyFrame extends JFrame {
    private final boolean exitOnClose;

    boolean canClose() {

        return true;
    }

    private JEasyFrame(String title, boolean exit) {
        super(title);
        exitOnClose = exit;
        addWindowListener(new Closer());
    }

    class Closer extends WindowAdapter {
        public Closer() {
        }

        public void windowClosing(WindowEvent e) {
            tryClose();
        }
    }

    public JEasyFrame(Component comp, String title, boolean exit) {
        this(title, exit);

        getContentPane().add(BorderLayout.CENTER, comp);
        pack();
        this.setResizable(false);
        this.setVisible(true);
        repaint();
    }

    void tryClose() {
        if (canClose()) {

            if (exitOnClose)
                System.exit(0);
            else
                dispose();
        }
    }
}