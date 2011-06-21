package controllers.examples;

import competition.MsPacManController;
import core.Constants;
import core.GameStateInterface;
import core.Node;
import core.utilities.Utilities;

import java.util.ArrayList;

public class RandomNonReverseMsPacMan implements MsPacManController, Constants {
    private Node prev;

    public int getAction(GameStateInterface gs) {

        Node cur = gs.getPacman().current;
        ArrayList<Node> possibles = new ArrayList<Node>();
        for (Node n : cur.adj) {
            if (!n.equals(prev)) possibles.add(n);
        }
        Node next = possibles.get(rand.nextInt(possibles.size()));
        prev = cur;
        int action = Utilities.getWrappedDirection(cur, next, gs.getMaze());
        return action;
    }
}
