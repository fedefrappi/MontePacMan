package competition;

import java.util.ArrayList;

import mcts.TreeNode;
import core.GameStateInterface;
import core.Node;
import core.utilities.Utilities;

public class MCTSMsPacman implements MsPacManController {
	
	private Node prev;
	private static int numberOfSimulations = 1000;
	
	@Override
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
        }
		
		TreeNode root = new TreeNode(gs.copy(), prev);
		prev = gs.getPacman().current;
		long startTime = System.currentTimeMillis();
        for (int i=0; i<numberOfSimulations; i++) {
            root.selectAction();
        }
        System.out.println("Mean simulation time: " + (System.currentTimeMillis() - startTime) + " ms");
        
        TreeNode[] children = root.children;
        
        double max = Double.MIN_VALUE;
        int maxInt = 0;
        for (int i=0; i<children.length; i++){
        	if(children[i].nVisits>max){
        		max = children[i].nVisits;
        		maxInt = i; 
        	}
        }
  //    System.out.println(max);
        
		return Utilities.getWrappedDirection(gs.getPacman().current, children[maxInt].gameState.getPacman().current, gs.getMaze());
	}
}
