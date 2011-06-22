package controllers.examples;

import competition.MsPacManController;
import core.Constants;
import core.GameStateInterface;
import core.Node;
import core.utilities.Utilities;

import java.util.ArrayList;

public class RandomSemiNonReverseMsPacMan implements MsPacManController, Constants {
    private Node prev;

    public int getAction(GameStateInterface gs) {

    	ArrayList<Node> possibles = new ArrayList<Node>();
        for (Node n : gs.getPacman().current.adj) {
            if (!n.equals(prev)){
            	possibles.add(n);
            }
        }
        if (possibles.size()==1){
    		prev = gs.getPacman().current;
        	return Utilities.getWrappedDirection(gs.getPacman().current, possibles.get(0), gs.getMaze());
        } else {
        	return rand.nextInt(possibles.size());
        }
    }
}
