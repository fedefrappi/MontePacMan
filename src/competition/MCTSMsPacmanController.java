package competition;

import mcts.TreeNode;
import core.GameStateInterface;
import core.Node;
import core.utilities.Utilities;

public class MCTSMsPacmanController implements MsPacManController {
	
	Node prev;
	
	@Override
	public int getAction(GameStateInterface gs) {
		TreeNode root = new TreeNode(gs, prev);
		prev = gs.getPacman().current;
		int n = 20;
        for (int i=0; i<n; i++) {
            root.selectAction();
        }
        
        TreeNode[] children = root.children;
        
        double max = Double.MIN_VALUE;
        int maxInt = 0;
        for (int i=0; i<children.length; i++){
        	if(children[i].totValue>max){
        		max = children[i].totValue;
        		maxInt = i; 
        	}
        }
  //      System.out.println(max);
        
		return Utilities.getWrappedDirection(gs.getPacman().current, children[maxInt].gameState.getPacman().current, gs.getMaze());
	}
}
